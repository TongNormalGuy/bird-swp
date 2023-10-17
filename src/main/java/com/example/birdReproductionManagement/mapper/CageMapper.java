package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.CageResponse.Cage4ListDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDto;
import com.example.birdReproductionManagement.entity.Cage;

public class CageMapper {
    public static Cage mapToCage(CageDto cageDto){
        return Cage.builder()
                .id(cageDto.getId())
                .location(cageDto.getLocation())
//                .available(cageDto.getAvailable())
                .quantity(cageDto.getQuantity())
//                .inProcess(cageDto.getInProcess())

//                .birdList(cageDto.getBirdList().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))
//                .birdCageHistories(cageDto.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistory).collect(Collectors.toList()))
//                .reproductionProcesses(cageDto.getReproductionProcesses().stream().map(ReproductionProcessMapper::mapToReproductionProcess).collect(Collectors.toList()))
//                .workDivisions(cageDto.getWorkDivisions().stream().map(WorkDivisionMapper::mapToWorkDivision).collect(Collectors.toList()))
                .build();
    }

    public static CageDto mapToCageDto(Cage cage){
        return CageDto.builder()
                .id(cage.getId())
                .location(cage.getLocation())
//                .available(cage.getAvailable())
                .quantity(cage.getQuantity())

//                .inProcess(cage.getInProcess())
//                .birdList(cage.getBirdList().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
//                .birdCageHistories(cage.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistoryDto).collect(Collectors.toList()))
//                .reproductionProcesses(cage.getReproductionProcesses().stream().map(ReproductionProcessMapper::mapToReproductionProcessDto).collect(Collectors.toList()))
//                .workDivisions(cage.getWorkDivisions().stream().map(WorkDivisionMapper::mapToWorkDivisionDto).collect(Collectors.toList()))
                .build();
    }
    public static Cage4ListDTO map2Cage4ListDTO(Cage cage){
        return Cage4ListDTO.builder()
                .id(cage.getId())
                .location(cage.getLocation())
                .quantity(cage.getQuantity())
                .build();
    }


}
