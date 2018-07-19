package com.mystudy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mystudy.domain.entity.Customer;


public interface CustomerRepository extends  JpaRepository<Customer, Integer> {
	
	public List<Customer> findBySalePersonId(Integer salePersonId);
	
	@Modifying
	@Transactional
	@Query("Update Customer c SET c.salePersonId=null where c.salePersonId =?1")
	void disconnectAllFromSalePerson(Integer salePersonId);
	
	@Modifying
	@Transactional
	@Query("Update Customer c SET c.salePersonId=?2 where c.id =?1")
	void changeSalePerson(Integer id, Integer salePersonId);
	
	
	@Query("Select c.id from Customer c where c.salePersonId is not null")
	public Iterable<Integer> findConnectedCustomersIds();

}
