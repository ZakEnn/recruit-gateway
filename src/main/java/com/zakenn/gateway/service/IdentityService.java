package com.zakenn.gateway.service;

import com.zakenn.gateway.dto.IdentityDto;
import com.zakenn.gateway.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;


@Service
public class IdentityService {

    @Value("${identity.wsip:http://localhost:8086/ws-identity}")
    String restIdentityUri;

    WebClient client = WebClient.create(restIdentityUri);

    public void saveIdentity(IdentityDto identityDto) {
        Mono<IdentityDto> identityDtoMono = Mono.just(identityDto);
        client.post()
                .uri("/identities")
                .accept(MediaType.APPLICATION_JSON)
                .body(identityDtoMono, IdentityDto.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
