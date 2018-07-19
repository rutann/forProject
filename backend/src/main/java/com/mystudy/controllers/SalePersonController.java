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

import com.mystudy.domain.entity.SalePerson;
import com.mystudy.services.SalePersonService;

@RestController
@RequestMapping("/salePerson")
public class SalePersonController {

	@Autowired
	private SalePersonService salePersonService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Integer create(@RequestBody SalePerson salePerson) {
		Integer salePersonId = salePersonService.create(salePerson);
		return salePersonId;
	}

	@RequestMapping("/{salePersonId}")
	public SalePerson getById(@PathVariable("salePersonId") Integer salePersonId) {
		SalePerson salePerson = salePersonService.getById(salePersonId);
		return salePerson;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestBody SalePerson salePerson) {
		salePersonService.update(salePerson);
	}

	@DeleteMapping("/delete/{salePersonId}")
	public void deleteById(@PathVariable("salePersonId") Integer salePersonId) {
		salePersonService.deleteById(salePersonId);
	}

	@DeleteMapping("/delete")
	public void deleteById(SalePerson salePerson) {
		salePersonService.delete(salePerson);
	}

	@RequestMapping("/isExsist/{salePersonId}")
	public boolean isExistById(@PathVariable("salePersonId") Integer salePersonId) {
		return salePersonService.isExistById(salePersonId);
	}

	@DeleteMapping("/deleteByIdAndConnectedOrders/{salePersonId}")
	public void deleteByIdAndConnectedOrders(@PathVariable("salePersonId") Integer salePersonId) {
		salePersonService.deleteByIdAndConnectedOrders(salePersonId);
	}
	
	@DeleteMapping("/deleteAllAndConnectedOrders")
	public void deleteAllAndConnectedOrders() {
		salePersonService.deleteAllAndConnectedOrders();
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<SalePerson> getAllSalePeople() {
		return salePersonService.getAll();
	}
	
	@GetMapping(path = "/allIds")
	public @ResponseBody Iterable<Integer> getAllSalePeopleIds() {
		return salePersonService.getAllIds();
	}
	
}
