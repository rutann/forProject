package com.mystudy.web.controllers;

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

@Controller
@RequestMapping("/order")
public class OrderViewController {

	
	@Value("${connection.url}/customer")
	private String customerUrl;

	@Value("${connection.url}/order")
	private String orderUrl;
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/ordersList")
	public String ordersList(Model model) {

		Order[] orders = restTemplate.getForObject(orderUrl + "/all", Order[].class);

		model.addAttribute("orders", orders);

		return "ordersList";
	}
	
	@GetMapping("/getOrdersBySalePersonId")
	public String getOrdersBySalePersonId(@RequestParam Integer salePersonId, Model model) {
		String message = "for salePerson Id: " + salePersonId;
		Order[] orders = restTemplate.getForObject(orderUrl + "/getBySalePersonId/" + salePersonId, Order[].class);

		model.addAttribute("orders", orders);
		model.addAttribute("salePersonId", salePersonId);
		model.addAttribute("message", message);
		return "ordersList";
	}

	@GetMapping("/getOrdersByCustomerId")
	public String getOrdersByCustomerId(@RequestParam Integer customerId, Model model) {
		String message = "for Customer Id: " + customerId;
		Order[] orders = restTemplate.getForObject(orderUrl + "/getByCustomerId/" + customerId, Order[].class);

		model.addAttribute("orders", orders);
		model.addAttribute("customerId", customerId);
		model.addAttribute("message", message);
		return "ordersList";
	}

	@RequestMapping("/create")
	public String createNewOrder(Model model) {
		
		model.addAttribute("order", new Order());
		model.addAttribute("customerIds", restTemplate.getForObject(customerUrl + "/getConnectedCustomersIds", Integer[].class));
		
		return "createNewOrder";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute Order order, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			if(result.getFieldErrors("salePersonId").size()!=result.getErrorCount()) {
				model.addAttribute("customerIds", restTemplate.getForObject(customerUrl + "/getConnectedCustomersIds", Integer[].class));
				return "createNewOrder";
			}
			
		}

		restTemplate.postForObject(orderUrl + "/create", order, Integer.class, new HashMap<String, Object>());

		return ordersList(model);
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute Order order, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "updateOrder";
		}

		restTemplate.put(orderUrl + "/update", order, new HashMap<String, Object>());

		return ordersList(model);
	}

	@RequestMapping("/updateOrder")
	public String updateOrder(@RequestParam Integer orderId, Model model) {

		String url = orderUrl + "/" + orderId;
		Order order = restTemplate.getForObject(url, Order.class);
		
		model.addAttribute("order", order);
		return "updateOrder";
	}
	
	
	@RequestMapping("/deleteAll")
	public String deleteAll(Model model) {

		restTemplate.delete(orderUrl + "/deleteAll");

		return ordersList(model);
	}

	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam Integer orderId, Model model) {

		restTemplate.delete(orderUrl + "/delete/" + orderId);

		return ordersList(model);
	}
	
}
