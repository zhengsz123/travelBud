package com.travel.core.repository;
import com.travel.core.domain.Media;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface MediaRepository extends CrudRepository<Media,Integer> {
    @Query("select m from Media m where m.id = ?1")
    Optional<Media>findKeyandUrlById(Long imagesId);
}
