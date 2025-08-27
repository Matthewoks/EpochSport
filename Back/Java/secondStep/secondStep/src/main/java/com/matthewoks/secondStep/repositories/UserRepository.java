package com.matthewoks.secondStep.repositories;

import com.matthewoks.secondStep.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends PagingAndSortingRepository<User, Long> { //JpaRepository<User,Long> {

    Page<User> findByName(String name, Pageable pageable);
    Optional<User> findByUsername(String name);

    //User findByUsername(String username);
    //User findByUsernameAndLanguagePreference(String username, String lang);

    //@Query("SELECT s FROM User s WHERE username LIKE %:usrn%") //jpa non guarda i nomii delle colonne ma la classe
    //List<User> findByUsernameFragment(@Param("usrn") String username);
    //List<User> findByLanguagePreference(String lang);


}
