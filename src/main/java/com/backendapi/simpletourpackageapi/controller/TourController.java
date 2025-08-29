package com.backendapi.simpletourpackageapi.controller;

import com.backendapi.simpletourpackageapi.dtos.ApiResponse;
import com.backendapi.simpletourpackageapi.dtos.TourRequest;
import com.backendapi.simpletourpackageapi.dtos.TourResponse;
import com.backendapi.simpletourpackageapi.service.Impl.TourServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<TourResponse>>> getAllTour( @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size) {
        Page<TourResponse> list = tourService.findAll(PageRequest.of(page, size));
        ApiResponse<List<TourResponse>> response = new ApiResponse<>(true, "Tours fetched successfully", list.getContent());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TourResponse>> getTourById(@PathVariable Long id) {
        TourResponse res = tourService.findById(id);
        ApiResponse<TourResponse> response = new ApiResponse<>(true, "Tour fetched successfully", res);
        return new ResponseEntity<>(response, HttpStatus.OK);


    }
}
