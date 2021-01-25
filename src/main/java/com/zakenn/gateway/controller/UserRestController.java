package com.zakenn.gateway.controller;

import com.zakenn.gateway.dto.IdentityDto;
import com.zakenn.gateway.dto.UserDto;
import com.zakenn.gateway.service.UserService;
import com.zakenn.gateway.utils.LoggingUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@CommonsLog
public class UserRestController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> signUp(@RequestBody IdentityDto identityDto) {
        log.info(LoggingUtils.getStartMessage(identityDto));
        userService.signUp(identityDto);
        log.info(LoggingUtils.getEndMessage());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> signIn(@RequestBody UserDto userDto) {
        log.info(LoggingUtils.getStartMessage(userDto));
        userService.signIn(userDto);
        log.info(LoggingUtils.getEndMessage());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String,String> passwordData) {
        log.info(LoggingUtils.getStartMessage(passwordData));
        userService.changePassword(passwordData);
        log.info(LoggingUtils.getEndMessage());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
