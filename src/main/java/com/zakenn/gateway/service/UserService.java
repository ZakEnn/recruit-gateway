package com.zakenn.gateway.service;

import com.zakenn.gateway.dto.IdentityDto;
import com.zakenn.gateway.dto.UserDto;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@CommonsLog
public class UserService {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    IdentityService identityService;

    public void signUp(IdentityDto identityDto) {
        String pwd = UUID.randomUUID().toString().substring(0, 6);
        UserDto userDto = new UserDto(identityDto.getEmail(), pwd);
        log.info("user : " + identityDto.getEmail() + "pwd: " + pwd);
        authenticationService.addUser(userDto, identityDto.getType());
        identityService.saveIdentity(identityDto);
        notificationService.sendMail(userDto.getEmail(), pwd);
    }


    public void signIn(UserDto userDto) {
         authenticationService.login(userDto);
    }

    public void changePassword(Map<String, String> userData){
        if (! userData.get("newPassword").equals( userData.get("confirmedPassword")))
            throw new RuntimeException("Please confirm your password");

        UserDto userDto = new UserDto();
        userDto.setEmail(userData.get("email"));
        userDto.setPassword(userData.get("newPassword"));

        authenticationService.changePassword(userDto);
    }
}
