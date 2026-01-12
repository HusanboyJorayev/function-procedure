package org.example.functionprosedure.mapper;

import lombok.RequiredArgsConstructor;
import org.example.functionprosedure.dto.UserDto;
import org.example.functionprosedure.entity.User;
import org.example.functionprosedure.utils.CoreUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toEntity(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .fullName(dto.getFullName())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public List<UserDto> dtoList(List<User> users) {
        if (CoreUtils.isPresent(users)) {
            return users.stream().map(this::toDto).toList();
        }
        return new ArrayList<>();
    }
}
