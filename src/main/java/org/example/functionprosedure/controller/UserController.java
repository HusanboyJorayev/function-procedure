package org.example.functionprosedure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.functionprosedure.dto.GenericResponse;
import org.example.functionprosedure.dto.UserDto;
import org.example.functionprosedure.service.BaseUrl;
import org.example.functionprosedure.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseUrl.V1 + BaseUrl.USER)
public class UserController {

    private final UserService userService;

    @PostMapping(BaseUrl.CREATE)
    public GenericResponse create(@Valid @RequestBody UserDto dto) {
        return userService.create(dto);
    }

    @GetMapping(BaseUrl.GET)
    public GenericResponse get(@RequestParam("id") Long id) {
        return userService.get(id);
    }

    @GetMapping(BaseUrl.GET + BaseUrl.ALL)
    public GenericResponse getAll() {
        return userService.getAll();
    }

    @GetMapping(BaseUrl.GET + "/year-and-month")
    public GenericResponse getByYearAndMonth(@RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        return userService.getByYearAndMonth(year, month);
    }

    @GetMapping(BaseUrl.GET + "/year-and-month-by-procedure")
    public GenericResponse getByYearAndMonthByProcedure(@RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        return userService.getByYearAndMonthByProcedure(year, month);
    }
}
