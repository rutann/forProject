package com.mystudy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mystudy.domain.entity.Customer;
import com.mystudy.repositories.CustomerRepository;
import com.mystudy.repositories.OrderRepository;
import com.mystudy.repositories.SalePersonRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	SalePersonRepository salePersonRepository;
	
	public Integer create(Customer customer) {
		customer = customerRepository.save(customer);
		return customer.getId();
	}
	
	public Customer getById(Integer id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.get();
	}
	
	public Integer update(Customer customer) {
		customer = customerRepository.save(customer);
		return customer.getId();
	}
	
	public void deleteById(Integer id) {
		customerRepository.deleteById(id);
	}
	
	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}
	
	public boolean isExistById(Integer id) {
		return customerRepository.existsById(id);
	}
	
	//OK just for study not for real project
	public Iterable<Customer> getAll(){
		return customerRepository.findAll();
	}
	
	public Iterable<Integer> getConnectedCustomersIds(){
			return customerRepository.findConnectedCustomersIds();
	}
		
	
	@Transactional
	public void deleteAllAndConnectedOrders() {
		List<Customer> customers = customerRepository.findAll();
		for(Customer customer : customers) {
			orderRepository.deleteByCustomerId(customer.getId());
		}
		customerRepository.deleteAll();
	}
	
	@Transactional
	public void deleteByIdAndConnectedOrders(Integer customerId) {
		orderRepository.deleteByCustomerId(customerId);
		customerRepository.deleteById(customerId);
	}
	
	public List<Customer> getCustomersBySalePersonId(Integer salePersonId){
		return customerRepository.findBySalePersonId(salePersonId);
	}
	
	public void disconnectAllFromSalePerson(Integer salePersonId) {
		customerRepository.disconnectAllFromSalePerson(salePersonId);
		
	}
	public void changeSalePerson(Integer customerId, Integer salePersonId) {
		Customer customer = customerRepository.getOne(customerId);
		orderRepository.deleteByCustomerIdAndSalePersonId(customerId, customer.getSalePersonId());
		if(salePersonId == null || salePersonRepository.existsById(salePersonId)) {
			customerRepository.changeSalePerson(customerId, salePersonId);
		} else throw new IllegalArgumentException("Customer with id:" + salePersonId + " does not exist.");
	}
}
