package com.backendapi.simpletourpackageapi.service;

import com.backendapi.simpletourpackageapi.dtos.TourRequest;
import com.backendapi.simpletourpackageapi.dtos.TourResponse;

import java.util.List;

public interface TourServiceInterface {

    TourResponse create(TourRequest request);

    List<TourResponse> findAll();


    TourResponse findById(Long id);
}
