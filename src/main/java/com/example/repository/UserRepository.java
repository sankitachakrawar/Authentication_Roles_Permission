package com.example.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.dto.IUserDto;
import com.example.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmailContainingIgnoreCase(String email);

	public Page<IUserDto> findByOrderById(Pageable paging, Class<IUserDto> class1);

	public Page<IUserDto> findByNameContainingIgnoreCaseOrderById(String search, Pageable paging, Class<IUserDto> class1);

	UserEntity findByEmail(String username);

	UserEntity getUserByEmail(String email);

	Optional<UserEntity> findByIdAndIsActiveTrue(Long userId);

	@Query(value="select * from users where id=?",nativeQuery = true)
	UserEntity getUser(Long id);
	
}
