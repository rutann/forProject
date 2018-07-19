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

import com.mystudy.domain.entity.Customer;
import com.mystudy.domain.entity.Order;

@Controller
@RequestMapping("/customer")
public class CustomerViewController {
	
	@Value("${connection.url}/customer")
	private String customerUrl;

	@Value("${connection.url}/order")
	private String orderUrl;
	
	@Value("${connection.url}/salePerson")
	private String salePersonUrl;

	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/customersList")
	public String customersList(Model model) {
		Customer[] customers = restTemplate.getForObject(customerUrl + "/all", Customer[].class);
		model.addAttribute("customers", customers);
		return "customersList";
	}
	
	@GetMapping("/getCustomersBySalePersonId")
	public String getCustomersBySalePersonId(@RequestParam Integer salePersonId, Model model) {
		Customer[] customers = restTemplate.getForObject(customerUrl + "/getBySalePersonId/" + salePersonId,
				Customer[].class);

		model.addAttribute("customers", customers);
		model.addAttribute("message", "for SalePerson Id : " + salePersonId);
		return "customersList";
	}

	@GetMapping("/getCustomerByIdForOrder")
	public String getCustomerByIdForOrder(@RequestParam Integer orderId, @RequestParam Integer customerId,
			Model model) {
		Customer customer = restTemplate.getForObject(customerUrl + "/" + customerId, Customer.class);
		model.addAttribute("customers", Collections.singletonList(customer));
		model.addAttribute("message", "for Order Id : " + orderId);
		return "customersList";
	}
	
	@GetMapping("/create")
	public String loadCreateForm(Model model) {
		Integer[] ids = restTemplate.getForObject(salePersonUrl + "/allIds", Integer[].class);
		
		model.addAttribute("customer", new Customer());
		model.addAttribute("salePersonIds", ids);
		return "createNewCustomer";
	}
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("salePersonIds", restTemplate.getForObject(salePersonUrl + "/allIds", Integer[].class));
			return "createNewCustomer";
			
		}
		String url = customerUrl + "/create";
		
		restTemplate.postForObject(url, customer, Integer.class, new HashMap<String, Object>());

		return customersList(model);
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "updateCustomer";
		}
		
		restTemplate.put(customerUrl + "/update", customer, new HashMap<String, Object>());
		
		return customersList(model);
	}

	@RequestMapping("/updateCustomer")
	public String updateCustomer(@RequestParam Integer customerId, Model model) {
		Customer customer = restTemplate.getForObject(customerUrl + "/" + customerId, Customer.class);
		model.addAttribute("customer", customer);
		return "updateCustomer";
	}

	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam Integer customerId, Model model) {
		Order[] orders = restTemplate.getForObject(orderUrl + "/getByCustomerId/" + customerId, Order[].class);
		if (orders.length == 0) {
			return fullDelete(customerId, model);
		}
		else {
			model.addAttribute("orders", orders);
			model.addAttribute("customerId", customerId);
			return "deleteCustomerWithOrders";
		}
	}

	@RequestMapping(value = "/fullDelete")
	public String fullDelete(@RequestParam Integer customerId, Model model) {
		restTemplate.delete(customerUrl + "/deleteByIdAndConnectedOrders/" + customerId);
		return customersList( model);
	}
	
	@RequestMapping("/deleteAllAndConnectedOrders")
	public String deleteAll(Model model) {
		restTemplate.delete(customerUrl + "/deleteAllAndConnectedOrders");
		return customersList(model);
	}

	
	@RequestMapping("/changeSalePersonForm")
	public String changeSalePersonForm(@ModelAttribute Customer customer, Model model) {
		model.addAttribute("salePersonIds", restTemplate.getForObject(salePersonUrl + "/allIds", Integer[].class));
		model.addAttribute("customer", customer);

		return "changeSalePersonForm";

	}
	
	@RequestMapping("/changeSalePerson")
	public String changeSalePerson(@RequestParam (value = "customerId")Integer customerId, @RequestParam(value = "salePersonId", required=false) Integer salePersonId, Model model) {
		
		String url = customerUrl + "/changeSalePerson?customerId=" + customerId + "&salePersonId=";
		if(salePersonId != null) {
			url = url + salePersonId;
		}
		restTemplate.put(url, null, new HashMap<String, Object>());

		return customersList(model);

	}

}
