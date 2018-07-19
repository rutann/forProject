package com.mystudy.web.controllers;

import java.util.Collections;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.mystudy.domain.entity.Order;
import com.mystudy.domain.entity.SalePerson;

@Controller
@RequestMapping("/salePeople")
public class SalePersonViewController {


	@Value("${connection.url}/order")
	private String orderUrl;
	
	@Value("${connection.url}/salePerson")
	private String salePersonUrl;


	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/salePeopleList")
	public String salePeopleList(Model model) {
		
		SalePerson[] salePeople = restTemplate.getForObject(salePersonUrl + "/all", SalePerson[].class);
		model.addAttribute("salePeople", salePeople);
		return "salePeopleList";
	}

	@RequestMapping("/deleteAllAndConnectedOrders")
	public String deleteAllAndConnectedOrders(Model model) {
		restTemplate.delete(salePersonUrl + "/deleteAllAndConnectedOrders");
		return salePeopleList(model);
	}

	@RequestMapping(value = "/fullDelete")
	public String fullDelete(@RequestParam Integer salePersonId, Model model) {

		restTemplate.delete(salePersonUrl + "/deleteByIdAndConnectedOrders/" + salePersonId);

		return salePeopleList(model);
	}

	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam Integer salePersonId, Model model) {

		Order[] orders = restTemplate.getForObject(orderUrl + "/getBySalePersonId/" + salePersonId, Order[].class);

		if (orders.length == 0) {

			return fullDelete(salePersonId, model);
		}

		else {
			model.addAttribute("orders", orders);
			model.addAttribute("salePersonId", salePersonId);
			return "deleteSalePersonWithOrders";
		}
	}

	@GetMapping("/create")
	public String loadCreateForm(Model model) {
		model.addAttribute("salePerson", new SalePerson());
		return "createNewSalePerson";
	}

	@PostMapping("/createNew")
	public String create(@Valid @ModelAttribute SalePerson salePerson, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "createNewSalePerson";
		}
		String url = salePersonUrl + "/create";

		restTemplate.postForObject(url, salePerson, Integer.class, new HashMap<String, Object>());

		return salePeopleList(model);
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute SalePerson salePerson, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "updateSalePerson";
		}

		restTemplate.put(salePersonUrl + "/update", salePerson, new HashMap<String, Object>());

		return salePeopleList(model);
	}

	@RequestMapping("/updateSalePerson")
	public String updateSalePerson(@RequestParam Integer salePersonId, Model model) {

		String url = salePersonUrl + "/" + salePersonId;
		SalePerson salePerson = restTemplate.getForObject(url, SalePerson.class);
		model.addAttribute("salePerson", salePerson);
		return "updateSalePerson";
	}

	@RequestMapping("/getSalePersonByIdForCustomer")
	public String getSalePersonByIdForCustomer(@RequestParam Integer customerId, Integer salePersonId, Model model) {
		String message = "for Customer Id : " + customerId;
		if (salePersonId != null) {
			String url = salePersonUrl + "/" + salePersonId;
			SalePerson salePerson = restTemplate.getForObject(url, SalePerson.class);
			model.addAttribute("salePeople", Collections.singletonList(salePerson));
		} else {
			model.addAttribute("salePeople", Collections.EMPTY_LIST);
		}
		model.addAttribute("message", message);
		return "salePeopleList";
	}

	@RequestMapping("/getSalePersonByIdForOrder")
	public String getSalePersonByIdForOrder(@RequestParam Integer orderId, @RequestParam Integer salePersonId,
			Model model) {
		String message = "for Order Id : " + orderId;
		String url = salePersonUrl + "/" + salePersonId;
		SalePerson salePerson = restTemplate.getForObject(url, SalePerson.class);

		model.addAttribute("salePeople", Collections.singletonList(salePerson));
		model.addAttribute("message", message);
		return "salePeopleList";
	}
}
