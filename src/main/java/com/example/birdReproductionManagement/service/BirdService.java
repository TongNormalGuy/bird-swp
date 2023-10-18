package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDto;

import java.util.List;

public interface BirdService {
    List<BirdDto> findAllBirds();
    BirdDto updateBird(BirdDto birdDto, Long id);

    void deleteBird(Long id);

    BirdDto createBird(BirdDto birdDto);
    List<BirdDto> findByCage(Long id);
}
