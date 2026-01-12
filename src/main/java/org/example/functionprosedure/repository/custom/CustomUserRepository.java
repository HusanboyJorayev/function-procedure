package org.example.functionprosedure.repository.custom;

import lombok.RequiredArgsConstructor;
import org.example.functionprosedure.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<UserDto> getUsersByYearAndMonth(int year, int month) {

        String sql = """
                    SELECT * 
                    FROM get_users_by_year_and_month(?, ?)
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    UserDto user = new UserDto();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFullName(rs.getString("full_name"));
                    user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    return user;
                },
                year, month
        );

    }

    public List<UserDto> getUsersByYearAndMonthByProcedure(int year, int month) {

        return jdbcTemplate.execute((Connection con) -> {
            List<UserDto> users = new ArrayList<>();
            boolean previousAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false); // PostgreSQL cursor uchun majburiy

            try (CallableStatement cs = con.prepareCall("CALL get_users_by_year_and_month_proc(?, ?, ?)")) {
                cs.setInt(1, year);
                cs.setInt(2, month);
                cs.registerOutParameter(3, Types.OTHER); // REF CURSOR

                cs.execute();

                try (ResultSet rs = (ResultSet) cs.getObject(3)) {
                    while (rs.next()) {
                        UserDto user = new UserDto();
                        user.setId(rs.getLong("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setFullName(rs.getString("full_name"));
                        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                        users.add(user);
                    }
                }
            } finally {
                con.setAutoCommit(previousAutoCommit);
            }

            return users;
        });

    }
}
