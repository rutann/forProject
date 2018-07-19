package com.mystudy.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mystudy.domain.entity.SalePerson;


public interface SalePersonRepository extends   JpaRepository<SalePerson, Integer> {
	
	@Query("Select s.id from SalePerson s")
	public Iterable<Integer> findAllIds();

}
