package org.example.functionprosedure.service;

import org.example.functionprosedure.dto.GenericResponse;
import org.example.functionprosedure.dto.UserDto;

public interface UserService {

    GenericResponse create(UserDto dto);

    GenericResponse get(Long id);

    GenericResponse getAll();

    GenericResponse getByYearAndMonth(Integer year, Integer month);

    GenericResponse getByYearAndMonthByProcedure(Integer year, Integer month);
}
