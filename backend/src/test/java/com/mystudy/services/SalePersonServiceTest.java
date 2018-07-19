package com.mystudy.services;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystudy.domain.entity.SalePerson;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SalePersonServiceTest {
	
	public static final String CITY_LONDON = "London";
	public static final String CITY_KYIV = "KYIV";
	public static final String SALE_PERSON_NAME ="Peel";
	public static final double COMMISION = 0.12;
	public static final String CUSTOMER_NAME = "Olan";
	public static final double RATING = 100;
	public static final double AMMOUNT = 5160.45;
	public static final double AMMOUNT2 = 160.45;
	
	@Autowired(required=true)
	SalePersonService service;
	
	@Before
	public void setUp() throws Exception {
		service.deleteAllAndConnectedOrders();
	}

	@After
	public void tearDown() throws Exception {
		service.deleteAllAndConnectedOrders();
	}
	
	private SalePerson getFilledSalePerson() {
		SalePerson salePerson = new SalePerson();
		salePerson.setCity(CITY_LONDON);
		salePerson.setName(SALE_PERSON_NAME);
		salePerson.setCommissions(COMMISION);
		return salePerson;
	}
	
	@Test
	public void createSimpleSalePerson() {
		SalePerson salePerson = getFilledSalePerson();
		Integer id  = service.create(salePerson);
		assertNotNull("Id should be not null", id);
		System.out.println("RESULT1: " + id);
	}
	
	@Test
	public void loadSalePersonById() {
		SalePerson salePerson = getFilledSalePerson();
		Integer id  = service.create(salePerson);
		assertNotNull("Id should be not null", id);
		SalePerson salePersonLoaded = service.getById(id);
		assertNotNull(salePersonLoaded);
		assertEquals(salePersonLoaded.getId(), id);
	}
	
	
	@Test
	public void deleteSalePerson() {
		SalePerson salePerson = getFilledSalePerson();
		Integer id  = service.create(salePerson);
		assertNotNull("Id should be not null", id);
		service.delete(salePerson);
		assertFalse(service.isExistById(id));
		
	}
	
	@Test
	public void isExistByIdTrue() {
		SalePerson salePerson = getFilledSalePerson();
		Integer id  = service.create(salePerson);
		assertNotNull("Id should be not null", id);
		assertTrue(service.isExistById(id));
	}
	
	@Test
	public void isExistByIdFalse() {
		SalePerson salePerson = getFilledSalePerson();
		Integer id  = service.create(salePerson);
		assertNotNull("Id should be not null", id);
		assertTrue(service.isExistById(id));
		service.deleteById(id);
		assertFalse(service.isExistById(id));
	}
	
	@Test
	public void deleteSalePersonById() {
		SalePerson salePerson = getFilledSalePerson();
		Integer id  = service.create(salePerson);
		assertNotNull("Id should be not null", id);
		service.deleteById(salePerson.getId());
		assertFalse(service.isExistById(id));
		
	}
	@Test
	public void updateSimpleSalePerson() {
		SalePerson salePerson = getFilledSalePerson();
		Integer id  = service.create(salePerson);
		assertNotNull("Id should be not null", id);
		SalePerson salePersonForUpdate = service.getById(id);
		salePersonForUpdate.setCity(CITY_KYIV);
		Integer updatedSalePersonId = service.update(salePersonForUpdate);
		assertEquals(salePersonForUpdate.getId(), updatedSalePersonId );
		SalePerson loadedSalePerson = service.getById(updatedSalePersonId);
		assertEquals(salePersonForUpdate.getCommissions(), loadedSalePerson.getCommissions() , 0);
		assertEquals(salePersonForUpdate.getName(), loadedSalePerson.getName());
		assertEquals(CITY_KYIV, loadedSalePerson.getCity());
	}
	
	@Test
	public void getAllTestNull() {
		SalePerson salePerson1 = getFilledSalePerson();
		SalePerson salePerson2 = getFilledSalePerson();
		Integer id1 = service.create(salePerson1);
		Integer id2 = service.create(salePerson2);
		assertTrue(service.isExistById(id1));
		assertTrue(service.isExistById(id2));
		Iterable<SalePerson> salePersons = service.getAll();
		assertEquals(2, salePersons.spliterator().getExactSizeIfKnown());
	}

	@Test (expected = NoSuchElementException.class)
	public void testGetByIdThrowException() {
		Integer salePersonId = service.create(getFilledSalePerson());
		assertTrue(service.isExistById(salePersonId));
		service.deleteById(salePersonId);
		assertFalse(service.isExistById(salePersonId));
		service.getById(salePersonId);	
		
	}
	
}
