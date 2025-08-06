package com.matthewoks.secondStep.repositories;


import com.matthewoks.secondStep.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {

   Equipment findByName(String name);
   /*  Equipment findByNameAndCategory(String name, String cat);

    @Query("SELECT s FROM Equipment s WHERE name LIKE %:cat%") //jpa non guarda i nomii delle colonne ma la classe
    List<Equipment> findByNameFragment(@Param("cat") String username);
    List<Equipment> findByCategory(String cat);*/

}
