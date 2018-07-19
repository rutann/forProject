package com.mystudy.services;

import static org.junit.Assert.*;

import java.util.List;
import java.util.NoSuchElementException;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystudy.domain.entity.Customer;
import com.mystudy.domain.entity.SalePerson;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	public static final String CITY_LONDON = "London";
	public static final String CITY_KYIV = "KYIV";
	public static final String SALE_PERSON_NAME ="Peel";
	public static final double COMMISION = 0.12;
	public static final String CUSTOMER_NAME = "Olan";
	public static final double RATING = 100;
	
	@Autowired(required=true)
	CustomerService service;
	
	@Autowired(required=true)
	SalePersonService salePersonService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		service.deleteAllAndConnectedOrders();
		salePersonService.deleteAllAndConnectedOrders();
	}

	@After
	public void tearDown() throws Exception {
		service.deleteAllAndConnectedOrders();
		salePersonService.deleteAllAndConnectedOrders();
	}
	
	private Customer getSimpleCustomer() {
		Customer customer = new Customer();
		customer.setCity(CITY_LONDON);
		customer.setName(CUSTOMER_NAME);
		customer.setRating(RATING);
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
	public void testCreateCustomer() {
		Integer id = service.create(getSimpleCustomer());
		assertNotNull(id);
		Customer customer = service.getById(id);
		assertNotNull(customer);
	}
	
	@Test
	public void testCreateCustomerWithSalePerson() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Customer customer = getSimpleCustomer();
		customer.setSalePersonId(salePersonId);
		Integer id = service.create(customer);
		assertNotNull(id);
	}
	
	@Test
	public void testDeleteCustomer() {
		Integer id = service.create(getSimpleCustomer());
		assertNotNull(id);
		assertTrue(service.isExistById(id));
		service.deleteById(id);
		assertFalse(service.isExistById(id));
	}
	
	@Test
	public void testUpdateCustomer() {
		SalePerson person1 = getFilledSalePerson();
		SalePerson person2 = getFilledSalePerson();
		person2.setCity(CITY_KYIV);
		Integer person1Id = salePersonService.create(person1);
		Integer person2Id = salePersonService.create(person2);
		Customer customer = getSimpleCustomer();
		customer.setSalePersonId(person1Id);
		Integer customerId = service.create(customer);
		assertNotNull(customerId);
		customer = service.getById(customerId);
		assertNotEquals(person2Id, customer.getSalePersonId());
		customer.setSalePersonId(person2Id);
		service.update(customer);
		customer = service.getById(customerId);
		assertEquals(person2Id, customer.getSalePersonId());
	}
	
	@Test 
	public void testDeleteAll() {
		Integer customerId1= service.create(getSimpleCustomer());
		Integer customerId2= service.create(getSimpleCustomer());
		Integer customerId3= service.create(getSimpleCustomer());
		assertTrue(service.isExistById(customerId1));
		assertTrue(service.isExistById(customerId2));
		assertTrue(service.isExistById(customerId3));
		service.deleteAllAndConnectedOrders();
		assertFalse(service.isExistById(customerId1));
		assertFalse(service.isExistById(customerId2));
		assertFalse(service.isExistById(customerId3));
	}
	
	@Test
	public void testGetAll() {
		Integer customerId1= service.create(getSimpleCustomer());
		Integer customerId2= service.create(getSimpleCustomer());
		Integer customerId3= service.create(getSimpleCustomer());
		assertTrue(service.isExistById(customerId1));
		assertTrue(service.isExistById(customerId2));
		assertTrue(service.isExistById(customerId3));
		Iterable<Customer> customers = service.getAll();
		assertEquals(3, customers.spliterator().getExactSizeIfKnown());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testGetByIdThrowException() {
		Integer customerId = service.create(getSimpleCustomer());
		assertTrue(service.isExistById(customerId));
		service.deleteById(customerId);
		assertFalse(service.isExistById(customerId));
		service.getById(customerId);	
		
	}
	
	@Test
	public void testGetCustomersBySalePersonId() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Customer customer = getSimpleCustomer();
		customer.setSalePersonId(salePersonId);
		Customer customer1 = getSimpleCustomer();
		customer1.setSalePersonId(salePersonId);
		Customer customer2 = getSimpleCustomer();
		customer2.setSalePersonId(salePersonId);
		Customer customer3 = getSimpleCustomer();
		customer3.setSalePersonId(salePersonId);
		service.create(customer);
		service.create(customer1);
		service.create(customer2);
		service.create(customer3);
		
		List<Customer> customers = service.getCustomersBySalePersonId(salePersonId);
		assertEquals(4, customers.size());
	}
	
	@Test
	public void testGetCustomersBySalePersonIdNull() {
		Integer salePersonId = salePersonService.create(getFilledSalePerson());
		Customer customer = getSimpleCustomer();
		
		Customer customer1 = getSimpleCustomer();
		
		Customer customer2 = getSimpleCustomer();
		customer2.setSalePersonId(salePersonId);
		Customer customer3 = getSimpleCustomer();
		customer3.setSalePersonId(salePersonId);
		service.create(customer);
		service.create(customer1);
		service.create(customer2);
		service.create(customer3);
		
		List<Customer> customers = service.getCustomersBySalePersonId(salePersonId);
		assertEquals(2, customers.size());
		List<Customer> customers2 = service.getCustomersBySalePersonId(null);
		assertEquals(2, customers2.size());
		
	}
	
	
	

}
