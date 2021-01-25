package com.zakenn.gateway.service;

import com.zakenn.gateway.dto.NotificationDto;
import com.zakenn.gateway.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {
    @Value("${notification.wsip:http://localhost:8084/ws-notification}")
    String restNotificationUri;

    WebClient client = WebClient.create(restNotificationUri);

    public void sendMail(String email, String pwd) {
        Mono<NotificationDto> notificationDtoMono = Mono.just(getPasswordNotificationDto(email, pwd));

        client.post()
                .uri("/send-notification")
                .accept(MediaType.APPLICATION_JSON)
                .body(notificationDtoMono, NotificationDto.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

    private NotificationDto getPasswordNotificationDto(String email, String pwd) {
         NotificationDto notificationDto = new NotificationDto();
         notificationDto.setReceivers(Arrays.asList(email));
         notificationDto.setObject("Password Notification");
         notificationDto.setMessage("Here is your RecruitApp password : " + pwd);

         return notificationDto;
    }
}
