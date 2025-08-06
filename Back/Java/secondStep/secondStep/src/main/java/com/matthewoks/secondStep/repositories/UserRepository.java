package com.matthewoks.secondStep.repositories;

import com.matthewoks.secondStep.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findByUsernameAndLanguagePreference(String username, String lang);

    @Query("SELECT s FROM User s WHERE username LIKE %:usrn%") //jpa non guarda i nomii delle colonne ma la classe
    List<User> findByUsernameFragment(@Param("usrn") String username);
    List<User> findByLanguagePreference(String lang);


}
