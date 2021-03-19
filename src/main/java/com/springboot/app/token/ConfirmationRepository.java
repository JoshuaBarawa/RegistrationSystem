package com.springboot.app.token;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationRepository extends CrudRepository<ConfirmationToken, Long>{

	ConfirmationToken findByToken(String token);
	
	
}
