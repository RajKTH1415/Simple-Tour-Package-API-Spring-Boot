package com.backendapi.simpletourpackageapi.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TourRequest {

    @NotBlank @Size(max = 160)
    private String title;

    @NotBlank @Size(max = 500)
    private String description;

    @NotBlank @Size(max = 512)
    @Pattern(regexp = "^(https?://).+", message = "image must be a valid http/https URL")
    private String image;

    @NotBlank
    @Pattern(regexp = "^(\\d{1,3})Days/(\\d{1,3})Nights$", message = "duration must be like '14Days/13Nights'")
    private String duration;

    @NotBlank
    @Pattern(regexp = "^\\$\\d{1,9}(\\.\\d{1,2})?$", message = "actualPrice must be like '$1200' or '$1200.00'")
    private String actualPrice;

    @NotBlank
    @Pattern(regexp = "^(100|[1-9]?\\d)%$", message = "discountInPercentage must be 0%..100%")
    private String discountInPercentage;
}
