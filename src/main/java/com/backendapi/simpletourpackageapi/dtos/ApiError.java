package com.backendapi.simpletourpackageapi.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ApiError {
    private int status;
    private String error;
    private String message;
    private String path;
    private String timestamp;
}
