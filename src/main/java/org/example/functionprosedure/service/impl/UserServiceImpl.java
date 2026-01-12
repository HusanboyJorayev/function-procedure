package org.example.functionprosedure.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.functionprosedure.dto.GenericResponse;
import org.example.functionprosedure.dto.UserDto;
import org.example.functionprosedure.entity.User;
import org.example.functionprosedure.exceptions.CustomException;
import org.example.functionprosedure.exceptions.UserAlreadyExistsException;
import org.example.functionprosedure.mapper.UserMapper;
import org.example.functionprosedure.repository.UserRepository;
import org.example.functionprosedure.repository.custom.CustomUserRepository;
import org.example.functionprosedure.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final CustomUserRepository customUserRepository;

    @Override
    public GenericResponse create(UserDto dto) {
        //if (CoreUtils.isPresent(dto.getUsername())) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("User already exist");
        }
        User entity = userMapper.toEntity(dto);
        userRepository.save(entity);

        return GenericResponse.success(HttpStatus.CREATED);
        //}
        //throw new CustomException("Username cannot be null or empty");
    }

    @Override
    public GenericResponse get(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("user not found"));
        return GenericResponse.success(userMapper.toDto(user));
    }

    @Override
    public GenericResponse getAll() {
        List<User> list = userRepository.findAll();
        return GenericResponse.success(userMapper.dtoList(list));
    }

    @Override
    public GenericResponse getByYearAndMonth(Integer year, Integer month) {
        List<UserDto> users = customUserRepository.getUsersByYearAndMonth(year, month);
        return GenericResponse.success(users);
    }

    @Override
    public GenericResponse getByYearAndMonthByProcedure(Integer year, Integer month) {
        List<UserDto> list = customUserRepository.getUsersByYearAndMonthByProcedure(year, month);
        return GenericResponse.success(list);
    }
}
