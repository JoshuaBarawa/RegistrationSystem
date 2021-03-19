package com.springboot.app.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.model.AppUser;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<AppUser, Long>{
	
 AppUser findByEmail(String email);
 
 boolean findByUserName(String userName);

 
}
