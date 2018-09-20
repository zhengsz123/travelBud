package com.travel.core.repository;



import com.travel.core.domain.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface AuthorityRepository extends CrudRepository<Authority,Integer> {

    @Query ("select a from Authority a where a.user.id = ?1")
    List<Authority> findAuthoritiesByUserId(Long usersId);
}
