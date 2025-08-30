package com.backendapi.simpletourpackageapi.dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ApiError {
    private boolean success;
    private int status;
    private String message;
    private String path;
    private String timestamp;
    private List<ValidationError> errors;
}
