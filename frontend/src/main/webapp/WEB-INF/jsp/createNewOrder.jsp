<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
<head>
<link href="<c:url value="/css/main.css" />" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<h1>Business connections</h1>
	<div class="topnav">
		<a href="/index.html" class="active">Home</a> <a
			href="/salePeople/salePeopleList">Sale's people list</a> <a
			href="/customer/customersList">Customers list</a> <a
			href="/order/ordersList">Orders list </a>
	</div>


	<div class="container">
		<h2>Fill form to create new Order</h2>
		<div class="alert">
			<span class="closebtn"
				onclick="this.parentElement.style.display='none';">&times;</span> <strong>Warning!</strong>
			You can create new Order just for Customers which connected with SalePeople.
		</div>
<br>
		<form:form action="/order/createNewOrder" method="post"
			modelAttribute="order">

			<label for="amount"> Order amount</label>
			<br>
			<form:input type="number" step="0,01" name="amount" path="amount" />
			<form:errors path="amount" cssClass="error" />
			<br>


			<label for="orderDate">Order date</label>
			<br>
			<form:input type="date" name="orderDate" path="orderDate" />
			<form:errors path="orderDate" cssClass="error" />
			<br>
			<br>

			<label for="customerId">Customer id</label>
			<br>


			<select name="customerId">
				<option value="${order.customerId}">${order.customerId}</option>

				<option value=""></option>

				<c:forEach items="${customerIds}" var="customerId"
					varStatus="status">
					<option value="${customerId}">${customerId}</option>
				</c:forEach>

			</select>
			<form:errors path="customerId" cssClass="error" />
			<br>









			<!-- label for="salePersonId">Sale Person id</label><br>
   <form:input type="number" name="salePersonId" path="salePersonId"/>
   <form:errors path="salePersonId" cssClass="error"/><br>
  -->
			<input type="submit" formaction="/order/create" value="Create">
			<br>

		</form:form>
	</div>
</body>

</html>
