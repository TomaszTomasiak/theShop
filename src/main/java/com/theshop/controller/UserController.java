package com.theshop.controller;

import com.theshop.domain.dto.UserDto;
import com.theshop.exception.NotFoundException;
import com.theshop.mapper.UserMapper;
import com.theshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.debug("REST request to get all users");
        return mapper.mapToUserDtoList(service.getUsers());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get user with id: {}", id);
        return mapper.mapToUserDto(service.getUser(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        log.debug("REST request to add new user: {}", userDto);
        service.saveUser(mapper.mapToUser(userDto));
        return userDto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUserById(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        log.debug("REST request to update user with id: {}", id);
        return mapper.mapToUserDto(service.saveUser(mapper.mapToUser(userDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        log.debug("REST request to delete user with id: {}", id);
        service.deleteUser(id);
    }
}
