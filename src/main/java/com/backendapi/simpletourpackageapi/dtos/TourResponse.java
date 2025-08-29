package com.backendapi.simpletourpackageapi.dtos;


import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TourResponse {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String duration;
    private String actualPrice;
    private String discountInPercentage;
    private String discountedPrice;
    private String currency;
    private String createdAt;
}
