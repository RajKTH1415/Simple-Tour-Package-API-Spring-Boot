package com.backendapi.simpletourpackageapi.controller;

import com.backendapi.simpletourpackageapi.dtos.ApiResponse;
import com.backendapi.simpletourpackageapi.dtos.TourRequest;
import com.backendapi.simpletourpackageapi.dtos.TourResponse;
import com.backendapi.simpletourpackageapi.service.Impl.TourServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    private final TourServiceImpl tourService;

    public TourController(TourServiceImpl tourService) {
        this.tourService = tourService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TourResponse>> createTour(@Valid @RequestBody TourRequest tourRequest) {
        TourResponse res = tourService.create(tourRequest);
        ApiResponse<TourResponse> response = new ApiResponse<>(true, "Tour created successfully", res);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
