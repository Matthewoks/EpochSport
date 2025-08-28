package com.matthewoks.secondStep.repositories;

import com.matthewoks.secondStep.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> { //PagingAndSortingRepository<User, Long> { //

   // Page<User> findByName(String name, Pageable pageable);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    //User findByUsername(String username);
    //User findByUsernameAndLanguagePreference(String username, String lang);

    //@Query("SELECT s FROM User s WHERE username LIKE %:usrn%") //jpa non guarda i nomii delle colonne ma la classe
    //List<User> findByUsernameFragment(@Param("usrn") String username);
    //List<User> findByLanguagePreference(String lang);


}
