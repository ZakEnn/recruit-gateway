package com.zakenn.gateway.service;

import com.zakenn.gateway.dto.UserDto;
import lombok.extern.apachecommons.CommonsLog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@CommonsLog
public class AuthenticationService {

    @Value("${authentication.wsip:http://localhost:8088/ws-uaa}")
    String restAuthenticationUri;
    
    WebClient client = WebClient.create(restAuthenticationUri);

    public Mono<Void> addUser(UserDto userDto, String role) {
        Mono<Void> result =  client.post()
                .uri("/users/role/{role}", role)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(Void.class);
        result.subscribe();
        return result;
    }

    public void login(UserDto userDto) {
        Mono<Void> result =  client.post()
                .uri("/login")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(Void.class);
        result.subscribe();
       // return result;
    }

    public Mono<Void> changePassword(UserDto userDto) {
        Mono<Void> result =  client.put()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(Void.class);
        result.subscribe();
        return result;
    }
}
