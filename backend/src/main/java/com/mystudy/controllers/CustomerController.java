package com.mystudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mystudy.domain.entity.Customer;
import com.mystudy.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Integer create(@RequestBody Customer customer) {
		Integer customerId = customerService.create(customer);
		return customerId;
	}

	@RequestMapping("/{customerId}")
	public Customer getById(@PathVariable("customerId") Integer customerId) {
		Customer customer = customerService.getById(customerId);
		return customer;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestBody Customer customer) {
		customerService.update(customer);
	}

	@DeleteMapping("/delete/{customerId}")
	public void deleteById(@PathVariable("customerId") Integer customerId) {
		customerService.deleteById(customerId);
	}

	@DeleteMapping("/delete")
	public void deleteById(Customer customer) {
		customerService.delete(customer);
	}

	@RequestMapping("/isExist/{customerId}")
	public boolean isExistById(@PathVariable("customerId") Integer customerId) {
		return customerService.isExistById(customerId);
	}

	@DeleteMapping("/deleteAllAndConnectedOrders")
	public void deleteAllAndConnectedOrders() {
		customerService.deleteAllAndConnectedOrders();
	}
	
	@DeleteMapping("/deleteByIdAndConnectedOrders/{customerId}")
	public void deleteByIdAndConnectedOrders(@PathVariable("customerId") Integer customerId) {
		customerService.deleteByIdAndConnectedOrders(customerId);
	}
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Customer> getAllCustomers() {
		return customerService.getAll();
	}
	
	@GetMapping(path = "/getConnectedCustomersIds")
	public @ResponseBody Iterable<Integer> getConnectedCustomersIds() {
		return customerService.getConnectedCustomersIds();
	}

	@GetMapping(path = "/getBySalePersonId/{salePersonId}")
	public @ResponseBody Iterable<Customer> getCustomersBySalePersonId(
			@PathVariable("salePersonId") Integer salePersonId) {
		return customerService.getCustomersBySalePersonId(salePersonId);

	}
	
	@RequestMapping("/disconnectAllFromSalePerson/{salePersonId}")
	public void disconnectAllFromSalePerson(@PathVariable("salePersonId") Integer salePersonId) {
		customerService.disconnectAllFromSalePerson(salePersonId);
	}
	
	@RequestMapping(value = "/changeSalePerson", method = RequestMethod.PUT)
	public void changeSalePerson(@RequestParam(value = "customerId") Integer customerId,
			@RequestParam(value = "salePersonId", required=false) Integer salePersonId) {
		customerService.changeSalePerson(customerId, salePersonId);
	}

}
