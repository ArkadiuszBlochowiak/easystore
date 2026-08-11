package com.easybytes.easystore.controller;

import com.easybytes.easystore.dto.UserDto;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/dummy")
@Validated
public class DummyController {
    // request body
    @PostMapping("/create-user")
    public String createUser(@RequestBody UserDto userDto) {
        IO.println(userDto);
        return "User created successfully";
    }

    // request params
    @GetMapping("/search")
    public String searchUser(@Size(min = 5, max = 30) @RequestParam(required = false, defaultValue = "Guest", name = "name") String userName) {
        return "Searching for user: " + userName;
    }

    @GetMapping("/multiple-search")
    public String multipleSearch(@RequestParam String firstName, @RequestParam String lastName) {
        return "Searching for user: " + firstName + " " + lastName;
    }

    @GetMapping("/multiple-search-map")
    public String multipleSearch(@RequestParam Map<String, String> params) {
        String firstName = params.get("firstName");
        String lastName = params.get("lastName");
        return "Searching for user: " + firstName + " " + lastName;
    }

    // request path variable
    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable(name = "userId") String id) {
        return "Searching for user: " + id;
    }

    @GetMapping("/user/{userId}/posts/{postId}")
    public String getUserPost(@PathVariable(name = "userId") String id, @PathVariable String postId) {
        return "Searching for user: " + id + " and post: " + postId;
    }

//    Two previous could be connected with one definition / pathVariables can be also defined as map
//    @GetMapping({"/user/{userId}/posts/{postId}", "/user/{userId}"})
//    public String getUserPostOptional(@PathVariable(name = "userId") String id, @PathVariable(required = false) String postId) {
//        return "Searching for user: " + id + " and optional post: " + postId;
//    }

    // request headers
    @GetMapping("/headers")
    public String readHeaders(@RequestHeader(name = "User-Agent") String userAgent, @RequestHeader(name = "User-Location", required = false) String userLocation) {
        return "Received headers with value: " + userAgent + ", " + userLocation;
    }

    @GetMapping("/specific-headers")
    public String readSpecificHeaders(@RequestHeader HttpHeaders headers) {
        List<String> location = headers.get("User-Location");
        return "Received headers with value: " + headers.toString();
    }

    // request entity
    @PostMapping("/request-entity")
    public String createUserWithEntity(RequestEntity<UserDto> requestEntity, @RequestParam Map<String, String> params) {
        HttpHeaders headers = requestEntity.getHeaders();
        UserDto userDto = requestEntity.getBody();
        String name = params.get("name");
        return "User created successfully";
    }
}
