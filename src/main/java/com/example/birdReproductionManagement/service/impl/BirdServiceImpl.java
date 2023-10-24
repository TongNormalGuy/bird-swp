package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.entity.Sex;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.BirdType;
import com.example.birdReproductionManagement.entity.Cage;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdTypeRepository;
import com.example.birdReproductionManagement.repository.CageRepository;
import com.example.birdReproductionManagement.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BirdServiceImpl implements BirdService {
    private BirdRepository birdRepository;
    private BirdTypeRepository birdTypeRepository;
    private CageRepository cageRepository;
    @Autowired
    public BirdServiceImpl(BirdRepository birdRepository, BirdTypeRepository birdTypeRepository, CageRepository cageRepository) {
        this.birdRepository = birdRepository;
        this.birdTypeRepository = birdTypeRepository;
        this.cageRepository = cageRepository;
    }

//    @Autowired

    @Override
    public List<BirdDTO> findAllBirds() {
        List<Bird> birds = birdRepository.findAll();
        return birds.stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
    }



    @Override
    public BirdDTO updateBird(Long id, BirdDTO birdDto) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be updated."));

        Bird updatedBird = BirdMapper.mapToBird(birdDto);
        updatedBird.setId(bird.getId());
        updatedBird.setBirdType(bird.getBirdType());
        Cage cage;
        if (!Long.valueOf(birdDto.getCageId()).equals(bird.getCage().getId())){
            cage =  cageRepository.findById(Long.valueOf(birdDto.getCageId())).orElseThrow(()
                    -> new CageNotFoundException("Bird could not be updated."));
            Cage oldCage = cageRepository.findById(bird.getCage().getId()).orElseThrow(()
                    -> new CageNotFoundException("Bird could not be updated."));
            oldCage.setQuantity(oldCage.getQuantity() - 1);
            cage.setQuantity(cage.getQuantity() + 1);
            cageRepository.save(oldCage);
            cageRepository.save(cage);
        }else {
            cage = cageRepository.findById(bird.getCage().getId()).orElseThrow(()
                    -> new CageNotFoundException("Bird could not be updated."));
        }
        updatedBird.setCage(cage);
        return BirdMapper.mapToBirdDto(birdRepository.save(updatedBird));
    }

    @Override
    public void deleteBird(Long id) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be deleted."));
        birdRepository.delete(bird);
    }

    @Override
    public BirdDTO createBird(BirdDTO birdDto) {
        BirdType birdType = birdTypeRepository.findByName(birdDto.getBirdTypeName());
        Cage cage = cageRepository.findById(Long.valueOf(birdDto.getCageId())).orElseThrow(()
            -> new CageNotFoundException("Bird could not be created."));
        Bird bird = BirdMapper.mapToBird(birdDto);
        bird.setBirdType(birdType);
        bird.setCage(cage);
        cage.setQuantity(cage.getQuantity() + 1);
        cageRepository.save(cage);
        return BirdMapper.mapToBirdDto(birdRepository.save(bird));
    }

    @Override
    public List<BirdDTO> findByCage(Long id) {
        return birdRepository.findByCage_Id(id).stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
    }

    @Override
    public BirdDTO updateBirdByFields(Long id, BirdDTO birdDto) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be updated."));
        Bird finalBird = bird;
        ReflectionUtils.doWithFields(birdDto.getClass(), field -> {
            field.setAccessible(true); // Đảm bảo rằng chúng ta có thể truy cập các trường private
            Object newValue = field.get(birdDto);
            if (newValue != null) { // lấy các giá trị ko null
                String fieldName = field.getName();
                // Kiểm tra nếu trường đang xem xét không phải là userPermission
                if (!fieldName.equals("cageId") && !fieldName.equals("sex") && !fieldName.equals("birdTypeName")) {
                    Field existingField = ReflectionUtils.findField(finalBird.getClass(), fieldName);
                    if (existingField != null) {
                        existingField.setAccessible(true);
                        ReflectionUtils.setField(existingField, finalBird, newValue);
                    }
                }
            }
        });
//        Cage cage = null;
        if(birdDto.getCageId() != null){
            Cage cage = cageRepository.findById(Long.valueOf(birdDto.getCageId())).orElseThrow(
                    () -> new CageNotFoundException("Cage could not be found in updateBirdByFields with" + birdDto.getCageId()));
            Cage oldCage = cageRepository.findById(bird.getCage().getId()).orElseThrow(
                    () -> new CageNotFoundException("Cage could not be found in updateBirdByFields with" + birdDto.getCageId()));
            oldCage.setQuantity(oldCage.getQuantity() - 1);
            cage.setQuantity(cage.getQuantity() + 1);
            cageRepository.save(oldCage);
            cageRepository.save(cage);
            finalBird.setCage(cage);
        }
        if(birdDto.getBirdTypeName() != null){
            BirdType birdType = birdTypeRepository.findByName(birdDto.getBirdTypeName());
            finalBird.setBirdType(birdType);
        }
        if(birdDto.getSex() != null){
            finalBird.setSex(Sex.valueOf(birdDto.getSex()));
        }
//        else{
//            cage = cageRepository.findById(bird.getCage().getId()).orElseThrow(
//                    () -> new CageNotFoundException("Cage could not be found in updateBirdByFields with" + birdDto.getCageId()));
//        }
        bird = finalBird;
//        bird.setCage(cage);
        return BirdMapper.mapToBirdDto(birdRepository.save(bird));
    }

    @Override
    public List<BirdDTO> findBySex(String sex) {
        return birdRepository.findBySexIs(Sex.valueOf(sex)).stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
    }


}
