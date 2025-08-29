package com.backendapi.simpletourpackageapi.service;

import com.backendapi.simpletourpackageapi.dtos.TourRequest;
import com.backendapi.simpletourpackageapi.dtos.TourResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TourServiceInterface {

    TourResponse create(TourRequest request);

    Page<TourResponse>findAll(Pageable pageable);


    TourResponse findById(Long id);
}
