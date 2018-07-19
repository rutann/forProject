package com.mystudy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystudy.domain.entity.SalePerson;
import com.mystudy.repositories.CustomerRepository;
import com.mystudy.repositories.OrderRepository;
import com.mystudy.repositories.SalePersonRepository;

@Service
public class SalePersonService {

	@Autowired
	private SalePersonRepository salePersonRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	public Integer create(SalePerson salePerson) {
		salePerson = salePersonRepository.save(salePerson);
		return salePerson.getId();
	}

	public SalePerson getById(Integer id) {
		Optional<SalePerson> salePerson = salePersonRepository.findById(id);
		return salePerson.get();
	}

	public Integer update(SalePerson salePerson) {
		salePerson = salePersonRepository.save(salePerson);
		return salePerson.getId();
	}

	public void deleteById(Integer id) {
		salePersonRepository.deleteById(id);
	}

	public void delete(SalePerson salePerson) {
		salePersonRepository.delete(salePerson);
	}

	public boolean isExistById(Integer id) {
		return salePersonRepository.existsById(id);
	}

	// OK just for study not for real project
	public Iterable<SalePerson> getAll() {
		return salePersonRepository.findAll();
	}

	public void deleteByIdAndConnectedOrders(Integer salePersonId) {
		orderRepository.deleteBySalePersonId(salePersonId);
		customerRepository.disconnectAllFromSalePerson(salePersonId);
		salePersonRepository.deleteById(salePersonId);
	}

	public void deleteAllAndConnectedOrders() {
		List<SalePerson> salePeople = salePersonRepository.findAll();
		for (SalePerson salePerson : salePeople) {
			orderRepository.deleteBySalePersonId(salePerson.getId());
			customerRepository.disconnectAllFromSalePerson(salePerson.getId());
		}
		salePersonRepository.deleteAll();
	}
	
	public Iterable<Integer> getAllIds(){
		Iterable<Integer> ids = salePersonRepository.findAllIds();
		return ids;
	}
}
