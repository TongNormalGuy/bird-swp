package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.Cage;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReproductionProcessRepository extends JpaRepository<ReproductionProcess, Long> {
    Optional<ReproductionProcess> findByIsDoneFalseAndCage_Id(Long cage_id);
    ReproductionProcess findByIsDoneFalseAndCage(Cage cage);
    List<ReproductionProcess> findAllByIsDoneFalse();
    Integer countAllByIsDoneFalse();
    @Query("SELECT DISTINCT p FROM ReproductionProcess p " +
            "LEFT JOIN p.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE br.bird = :bird " +
            "AND br.reproductionRole != :reproductionRole")
    List<ReproductionProcess> findByBirdAndReproductionRoleNot(@Param("bird")Bird bird, @Param("reproductionRole")ReproductionRole reproductionRole);

}
