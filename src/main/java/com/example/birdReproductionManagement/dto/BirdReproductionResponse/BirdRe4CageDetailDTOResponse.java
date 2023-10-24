package com.example.birdReproductionManagement.dto.BirdReproductionResponse;

import com.example.birdReproductionManagement.dto.BirdResponse.Bird4CageDetailDTOResponse;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdRe4CageDetailDTOResponse {
    private String reproductionId;
    private Date eggLaidDate;
    private String reproductionRole;
    private String eggStatus;
    private Date actEggHatchDate;
    private Date expEggHatchDate =eggLaidDate!=null? new Date(eggLaidDate.getYear()-1900, eggLaidDate.getMonth()-1,eggLaidDate.getDay() + 16):new Date();
    private Date expSwingBranch=eggLaidDate!=null? new Date(eggLaidDate.getYear()-1900, eggLaidDate.getMonth()-1,eggLaidDate.getDay() + 16):new Date();
    private Date expAdultBirdDate=eggLaidDate!=null? new Date(eggLaidDate.getYear()-1900, eggLaidDate.getMonth()-1,eggLaidDate.getDay() + 7):new Date();
    private Bird4CageDetailDTOResponse bird;
}
