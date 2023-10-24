package com.example.birdReproductionManagement.dto.BirdResponse;

import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4CageDetailDTOResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bird4CageDetailDTOResponse {
    private String birdId;
    private String sex;//
//    private String birdType;//
    private BirdType4CageDetailDTOResponse birdType;//
    private Float mutationRate;//
    private Float weight;//
    private String image;//

    //bird id?

}
