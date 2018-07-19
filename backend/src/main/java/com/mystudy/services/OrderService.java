package com.mystudy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystudy.domain.entity.Customer;
import com.mystudy.domain.entity.Order;
import com.mystudy.repositories.CustomerRepository;
import com.mystudy.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	public Integer create(Order order) {
		Customer customer = customerRepository.getOne(order.getCustomerId());
		if(customer.getSalePersonId() != null)
			order.setSalePersonId(customer.getSalePersonId());
		else throw new IllegalArgumentException("Order could not be created for Customer wich doesn't connect to SalePerson.");
		order = orderRepository.save(order);
		return order.getId();
	}
	
	public Order getById(Integer id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.get();
	}
	
	public Integer update(Order order) {
		order = orderRepository.save(order);
		return order.getId();
	}
	
	public void deleteById(Integer id) {
		orderRepository.deleteById(id);
	}
	
	public void delete(Order order) {
		orderRepository.delete(order);
	}
	
	public boolean isExistById(Integer id) {
		return orderRepository.existsById(id);
	}
	
	//OK just for study not for real project
	public Iterable<Order> getAll(){
		return orderRepository.findAll();
	}
	
	public void deleteAll() {
		orderRepository.deleteAll();
	}
	
	public List<Order> getBySalePersonId(Integer salePersonId){
		List<Order> orders = orderRepository.findBySalePersonId(salePersonId);
		return orders;
	}
	
	public List<Order> getByCustomerId(Integer customerId){
		List<Order> orders = orderRepository.findByCustomerId(customerId);
		return orders;
	}
	
	public void deleteByCustomerId(Integer customerId) {
		orderRepository.deleteByCustomerId(customerId);
	}
	
	public void deleteBySalePersonId(Integer salePersonId) {
		orderRepository.deleteBySalePersonId(salePersonId);
	}

}
