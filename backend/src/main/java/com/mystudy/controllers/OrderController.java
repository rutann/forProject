package com.mystudy.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mystudy.domain.entity.Order;
import com.mystudy.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Integer create(@RequestBody Order order) {
		Integer orderId = orderService.create(order);
		return orderId;
	}

	@RequestMapping("/{orderId}")
	public Order getById(@PathVariable("orderId") Integer orderId) {
		Order order = orderService.getById(orderId);
		return order;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestBody Order order) {
		orderService.update(order);
	}

	@DeleteMapping("/delete/{customerId}")
	public void deleteById(@PathVariable("customerId") Integer customerId) {
		orderService.deleteById(customerId);
	}

	@DeleteMapping("/delete")
	public void deleteById(Order order) {
		orderService.delete(order);
	}

	@RequestMapping("/isExist/{orderId}")
	public boolean isExistById(@PathVariable("orderId") Integer orderId) {
		return orderService.isExistById(orderId);
	}

	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		orderService.deleteAll();
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Order> getAllOrders() {
		return orderService.getAll();
	}

	@GetMapping(path = "/getBySalePersonId/{salePersonId}")
	public @ResponseBody Iterable<Order> getOrdersBySalePersonId(@PathVariable("salePersonId") Integer salePersonId) {
		return orderService.getBySalePersonId(salePersonId);

	}

	@GetMapping(path = "/getByCustomerId/{customerId}")
	public @ResponseBody Iterable<Order> getOrdersByCustomerId(@PathVariable("customerId") Integer customerId) {
		return orderService.getByCustomerId(customerId);

	}
	
	@DeleteMapping("/deleteByCustomerId/{customerId}")
	public void deleteByCustomerId(@PathVariable("customerId") Integer customerId) {
		orderService.deleteByCustomerId(customerId);
		
	}
	@DeleteMapping("/deleteBySalePersonId/{salePersonId}")
	public void deleteBySalePersonId(@PathVariable("salePersonId") Integer salePersonId) {
		orderService.deleteBySalePersonId(salePersonId);
		
	}

}
