package com.theymeleaf.Repositary;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theymeleaf.Entity.StudentEntity;
@Repository
public interface UserRepository extends JpaRepository<StudentEntity, Long>{
	
}
