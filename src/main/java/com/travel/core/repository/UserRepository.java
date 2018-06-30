package com.travel.core.repository;

import com.travel.core.domain.Gas;
import com.travel.core.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,String> {

    @Query ("select u from User u where u.username = ?1 or u.email=?1")
    Optional<User> findByEmailOrUsername(String keyword);
}
