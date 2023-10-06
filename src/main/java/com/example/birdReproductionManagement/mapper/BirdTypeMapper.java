package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdTypeDto;
import com.example.birdReproductionManagement.model.BirdType;

import java.util.stream.Collectors;

public class BirdTypeMapper {
    public static BirdType mapToBirdType(BirdTypeDto birdTypeDto){
        return BirdType.builder()
                .id(birdTypeDto.getId())
                .name(birdTypeDto.getName())
                .incubate(birdTypeDto.getIncubate())
                .chick(birdTypeDto.getChick())
                .swingBranch(birdTypeDto.getSwingBranch())
                .description(birdTypeDto.getDescription())
                .birdList(birdTypeDto.getBirdList().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))
                .meals(birdTypeDto.getMeals().stream().map(MealMapper::mapToMeal).collect(Collectors.toList()))
                .build();
    }

    public static BirdTypeDto mapToBirdTypeDto(BirdType birdType){
        return BirdTypeDto.builder()
                .id(birdType.getId())
                .name(birdType.getName())
                .incubate(birdType.getIncubate())
                .chick(birdType.getChick())
                .swingBranch(birdType.getSwingBranch())
                .description(birdType.getDescription())
                .birdList(birdType.getBirdList().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
                .meals(birdType.getMeals().stream().map(MealMapper::mapToMealDto).collect(Collectors.toList()))
                .build();
    }
}
