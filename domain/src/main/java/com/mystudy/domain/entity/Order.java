package com.mystudy.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "onum", nullable = false)
	private Integer id;

	@Column(name = "amt")
	private Double amount;

	@Min(1)
	@NotNull
	@Column(name = "cnum", nullable = false)
	private Integer customerId;

	@Min(1)
	@NotNull
	@Column(name = "snum", nullable = false)
	private Integer salePersonId;
	
	@NotNull
	@Column(name = "odate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;

	public Order() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getSalePersonId() {
		return salePersonId;
	}

	public void setSalePersonId(Integer salePersonId) {
		this.salePersonId = salePersonId;
	}

	@Override
	public String toString() {
		return "[id = " + id + "] [amount = " + amount + " ][salePersonId=" + salePersonId + "] [customerId"
				+ customerId + "] [ orderDate = " + orderDate.toString() + "]";

	}
	
	
	
}
