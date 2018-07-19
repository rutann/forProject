package com.mystudy.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cnum", nullable = false)
	private Integer id;
	
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "cname", nullable = false)
	private String name;

	@Size(max = 10)
	private String city;
	
	private Double rating;

	
	@Min(1)
	@Column(name = "snum")
	private Integer salePersonId;

	public Customer() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getSalePersonId() {
		return salePersonId;
	}

	public void setSalePersonId(Integer salePersonId) {
		this.salePersonId = salePersonId;
	}

	public String toString() {
		return "[id = " + id + "] [name = " + name + " ] [city" + city + "] [ rating = " + rating + "] [salePersonId="
				+ salePersonId + "]";
	}

}
