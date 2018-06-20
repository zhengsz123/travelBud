package com.travel.core.repository;

import com.travel.core.domain.Gas;
import com.travel.core.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,String> {
    List<User> findAll();

}
