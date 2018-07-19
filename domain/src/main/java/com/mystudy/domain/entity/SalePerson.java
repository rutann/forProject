package com.mystudy.domain.entity;

import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Salespeople")
public class SalePerson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "snum", nullable = false)
	private Integer id;
	
	
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name="sname", nullable = false)
	private String name;
	
	
	@Size(max = 10)
	private String city;
	
	@Column(name="comm")
	private Double commissions;
	
	public SalePerson() {
		
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

	public Double getCommissions() {
		return commissions;
	}

	public void setCommissions(Double commissions) {
		this.commissions = commissions;
	}
	
	public String toString() {
		return  "[id = "+ id + "] [name = " + name + " ] [city" + city + "] [ commissions = " + commissions + "]";
		
	}

}

