package com.theshop.controller;

import com.theshop.domain.User;
import com.theshop.domain.dto.UserDto;
import com.theshop.exception.NotFoundException;
import com.theshop.mapper.UserMapper;
import com.theshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")

public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @Autowired
    RestTemplate restTemplate;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        log.debug("REST request to add new user: {}", userDto);
        return mapper.mapToUserDto(service.saveUser(mapper.mapToUser(userDto)));
    }
    /*
    @RequestMapping(value = "/postnotewithclient",method = RequestMe, thod.POST)
public String postNote(@RequestBody  Note notes)
{
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<Note> entity = new  HttpEntity<Note>(notes,headers);
    return restTemplate.exchange("http://localhost:8080/api/notes", HttpMethod.POST, entity, String.class).getBody();

application/x-www-form-urlencoded
}
     */
//    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws URISyntaxException {
//        log.debug("REST request to save AppUser : {}", userDto);
//        if (userDto.getId() != null) {
//            return ResponseEntity.badRequest().body("User with given id already exists");
//        }
//        UserDto result = mapper.mapToUserDto(service.saveUser(mapper.mapToUser(userDto)));
//        return ResponseEntity.created(new URI("/api/v1/ecommercee/users/" + result.getId()))
//                .body(result);
//    }

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
