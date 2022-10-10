package com.fmc.userservice.Repository;

import com.fmc.userservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<User> findAll();
}
