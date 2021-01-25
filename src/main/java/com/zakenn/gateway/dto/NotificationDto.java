package com.zakenn.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private List<String> receivers;
    private String object;
    private String message;
}
