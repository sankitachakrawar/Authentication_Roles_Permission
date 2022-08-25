package com.example.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entities.SPRequestEntity;
import io.lettuce.core.dynamic.annotation.Param;

public interface SPRequestRepository extends JpaRepository<SPRequestEntity, Long>{

	@Query(value = "select u.* from sp_request u where u.user_id= :id",nativeQuery = true)
	Optional<SPRequestEntity> getuser(@Param("id") Long id);
}
