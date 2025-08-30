package com.backendapi.simpletourpackageapi.controller;

import com.backendapi.simpletourpackageapi.dtos.ApiResponse;
import com.backendapi.simpletourpackageapi.dtos.TourRequest;
import com.backendapi.simpletourpackageapi.dtos.TourResponse;
import com.backendapi.simpletourpackageapi.service.Impl.TourServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@Tag(name = "Tour Management", description = "Operations related to Tour Packages")
public class TourController {

    private final TourServiceImpl tourService;

    public TourController(TourServiceImpl tourService) {
        this.tourService = tourService;
    }


    @Operation(summary = "Create a new tour", description = "Creates a new tour with given details")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Tour created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request body"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<TourResponse>> createTour(@Valid @RequestBody TourRequest tourRequest) {
        TourResponse res = tourService.create(tourRequest);
        ApiResponse<TourResponse> response = new ApiResponse<>(true, "Tour created successfully", res);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all tours", description = "Fetch all tours with pagination")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Tours fetched successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<TourResponse>>> getAllTour(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Page<TourResponse> list = tourService.findAll(PageRequest.of(page, size));
        ApiResponse<List<TourResponse>> response = new ApiResponse<>(true, "Tours fetched successfully", list.getContent());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Operation(summary = "Get tour by ID", description = "Fetch a tour by its unique identifier")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Tour fetched successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Tour not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TourResponse>> getTourById(@PathVariable Long id) {
        TourResponse res = tourService.findById(id);
        ApiResponse<TourResponse> response = new ApiResponse<>(true, "Tour fetched successfully", res);
        return new ResponseEntity<>(response, HttpStatus.OK);


    }
}
