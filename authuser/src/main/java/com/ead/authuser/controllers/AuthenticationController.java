package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;

import com.ead.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
                                                   @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto){
        if (userService.existsByUsername(userDto.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken!");
        }

        if (userService.existsByUserEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto,userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/")
    public String index(){
        logger.trace("TRACE");// Traz as coisas com muito detalhes
        logger.debug("DEBUG");// Dar um detalhe muito maior
        logger.info("INFO");
        logger.warn("WARN");// É um alerta
        logger.error("ERROR"); // É ultilizado em bloco try catch
        return "Loggin Spring Boot...";
    }
}
