package com.jumia.pay.assessment.controller;


import com.jumia.pay.assessment.dto.UserDTO;
import com.jumia.pay.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String createUser(@Validated @RequestBody UserDTO userDTO, HttpServletRequest servletRequest) {
        return userService.createUser(userDTO, servletRequest);
    }

}
