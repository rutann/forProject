package com.mystudy.services;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mystudy.domain.entity.Customer;
import com.mystudy.domain.entity.Order;
import com.mystudy.domain.entity.SalePerson;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

	public static final String CITY_LONDON = "London";
	public static final String SALE_PERSON_NAME ="Peel";
	public static final double COMMISION = 0.12;
	public static final String CUSTOMER_NAME = "Olan";
	public static final double RATING = 100;
	public static final double AMMOUNT = 5160.45;
	public static final double AMMOUNT2 = 160.45;
	
	@Autowired(required=true)
	OrderService service;
	
	@Autowired(required=true)
	CustomerService customerService;
	
	@Autowired(required=true)
	SalePersonService salePersonService;

	@Before
	public void setUp() throws Exception {
		service.deleteAll();
		customerService.deleteAllAndConnectedOrders();
		salePersonService.deleteAllAndConnectedOrders();
	}

	@After
	public void tearDown() throws Exception {
		service.deleteAll();
		customerService.deleteAllAndConnectedOrders();
		salePersonService.deleteAllAndConnectedOrders();
		
	}
	private Order getSimpleOrder() {
		Order order = new Order();
		order.setAmount(AMMOUNT);
		order.setOrderDate(LocalDate.now());
		return order;
	}
	
	private Order getOrderWithCustomerIdSalePersonId(Integer customerId, Integer salePersonId) {
		Order order = getSimpleOrder();
		order.setCustomerId(customerId);
		order.setSalePersonId(salePersonId);
		return order;
	}
	
	private Customer getSimpleCustomerWithSalePersonId(Integer salePersonId) {
		Customer customer = new Customer();
		customer.setCity(CITY_LONDON);
		customer.setName(CUSTOMER_NAME);
		customer.setRating(RATING);
		customer.setSalePersonId(salePersonId);
		return customer;
	}
	
	private SalePerson getFilledSalePerson() {
		SalePerson salePerson = new SalePerson();
		salePerson.setCity(CITY_LONDON);
		salePerson.setName(SALE_PERSON_NAME);
		salePerson.setCommissions(COMMISION);
		return salePerson;
	}

	@Test
	@Transactional
	public void testCreate() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Integer customerId = customerService.create(getSimpleCustomerWithSalePersonId(salePersonId));
		Order order = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Integer orderId = service.create(order);
		assertNotNull(orderId);
		
	}
	
	@Test
	@Transactional
	public void testGetById() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Integer customerId = customerService.create(getSimpleCustomerWithSalePersonId(salePersonId));
		Order order = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Integer orderId = service.create(order);
		assertNotNull(orderId);
		Order loadedOrder = service.getById(orderId);
		
		System.out.println(loadedOrder.getOrderDate());
		assertNotNull(loadedOrder);
		
	}
	@Transactional
	@Test(expected = NoSuchElementException.class) 
	public void testGetByIdThrowException() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Integer customerId = customerService.create(getSimpleCustomerWithSalePersonId(salePersonId));
		Order order = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Integer orderId = service.create(order);
		assertTrue(service.isExistById(orderId));
		service.deleteById(orderId);
		assertFalse(service.isExistById(orderId));
		service.getById(orderId);
	}
	
	@Transactional
	@Test 
	public void testIsExistsByuId() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Integer customerId = customerService.create(getSimpleCustomerWithSalePersonId(salePersonId));
		Order order = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Integer orderId = service.create(order);
		assertTrue(service.isExistById(orderId));
		service.deleteById(orderId);
		assertFalse(service.isExistById(orderId));
		
	}
	
	@Transactional
	@Test 
	public void testSimpleUpdate() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Integer customerId = customerService.create(getSimpleCustomerWithSalePersonId(salePersonId));
		Order order = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Integer orderId = service.create(order);
		assertNotNull(orderId);
		order = service.getById(orderId);
		assertNotNull(order);
		assertNotEquals(AMMOUNT2, order.getAmount());
		order.setAmount(AMMOUNT2);
		orderId = service.update(order);
		Order updatedOrder = service.getById(orderId);
		assertEquals(AMMOUNT2, updatedOrder.getAmount(),0);
	}
	
	
	@Transactional
	@Test 
	public void testDeleteAll() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Integer customerId = customerService.create(getSimpleCustomerWithSalePersonId(salePersonId));
		Order order1 = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Order order2 = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Order order3 = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Integer  order1Id = service.create(order1);
		Integer  order2Id = service.create(order2);
		Integer  order3Id = service.create(order3);
		assertTrue(service.isExistById(order1Id));
		assertTrue(service.isExistById(order2Id));
		assertTrue(service.isExistById(order3Id));
		service.deleteAll();
		assertFalse(service.isExistById(order1Id));
		assertFalse(service.isExistById(order2Id));
		assertFalse(service.isExistById(order3Id));
	}
	@Transactional
	@Test 
	public void testgetAll() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Integer customerId = customerService.create(getSimpleCustomerWithSalePersonId(salePersonId));
		Order order1 = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Order order2 = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Order order3 = getOrderWithCustomerIdSalePersonId(customerId, salePersonId);
		Integer  order1Id = service.create(order1);
		Integer  order2Id = service.create(order2);
		Integer  order3Id = service.create(order3);
		assertTrue(service.isExistById(order1Id));
		assertTrue(service.isExistById(order2Id));
		assertTrue(service.isExistById(order3Id));
		Iterable<Order> orders  = service.getAll();
		assertEquals(3, orders.spliterator().getExactSizeIfKnown());
	}

}
