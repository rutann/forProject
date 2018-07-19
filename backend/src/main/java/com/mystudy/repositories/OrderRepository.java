package com.mystudy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mystudy.domain.entity.Order;

public interface OrderRepository extends  JpaRepository<Order, Integer> {
	
	public List<Order> findBySalePersonId(Integer salePersonId);
	
	public List<Order> findByCustomerId(Integer customerId);
	
	@Transactional
	public void deleteByCustomerId(Integer customerId);
	
	@Transactional
	public void deleteBySalePersonId(Integer salePersonId);
	

	@Transactional
	public void deleteByCustomerIdAndSalePersonId(Integer customerId, Integer salePersonId);
	
	@Modifying
	@Transactional
	@Query("Update Order o SET o.salePersonId=?2 where o.customerId =?1")
	void redirectOrdersByCustomerId(Integer customerId, Integer salePersonId);
	
}
