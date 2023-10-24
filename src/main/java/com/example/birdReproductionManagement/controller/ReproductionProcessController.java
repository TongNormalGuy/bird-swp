package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessDTOResponse;
import com.example.birdReproductionManagement.dto.PairDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.ProcessForViewAllResponseDTO;
import com.example.birdReproductionManagement.service.BirdTypeService;
import com.example.birdReproductionManagement.service.ReproductionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reproductionprocess")
public class ReproductionProcessController {
    @Autowired
    private ReproductionProcessService reproductionProcessService;
    @Autowired
    private BirdTypeService birdTypeService;

    @GetMapping("/view")
    public ResponseEntity<List<ProcessForViewAllResponseDTO>> getListOfReproductionProcess(){
        return new ResponseEntity<>(reproductionProcessService.findAllReproductionProcess(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ReproductionProcessDTO> addReproductionProcess(@RequestBody PairDTO pairDTO){
        return new ResponseEntity<>(reproductionProcessService.addReproductionProcess(pairDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReproductionProcess(@PathVariable("id")Long id){
        reproductionProcessService.deleteReproductionProcess(id);
        return new ResponseEntity<>("Reproduction process is deleted.", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReproductionProcessDTO> updateReproductionProcess(@PathVariable("id")Long id,
                                                                            @RequestBody ReproductionProcessDTO reproductionProcessDto){
        return new ResponseEntity<>(reproductionProcessService.updateReproductionProcess(id, reproductionProcessDto), HttpStatus.OK);
    }
    @GetMapping("/init")
    public ResponseEntity<List<BirdType4ProcessDTOResponse>> getType4Process(){
        return new  ResponseEntity<List<BirdType4ProcessDTOResponse>>(birdTypeService.getType4Process(), HttpStatus.OK);
    }
//    @GetMapping("/findfather/{id}")
//    public ResponseEntity<BirdReproductionDto> findFather(@PathVariable("id")Long id){
//        return new ResponseEntity<>(reproductionProcessService.findFather(id), HttpStatus.OK);
//    }
}
