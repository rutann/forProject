<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
	<h2>Customer with id: ${customerId} has list of orders. </h2>

	
	<table>
		<tr>
			<th>ID</th>
			<th>Amount</th>
			<th>Order Date</th>
			<th>SalePersonId</th>
			<th>CustomerId</th>
			
		</tr>
		<c:forEach items="${orders}" var="order" varStatus="status">
			<tr>
				<td>${order.id}</td>
				<td>${order.amount}</td>
				<td>${order.orderDate}</td>
				<td>${order.salePersonId}</td>
				<td>${order.customerId}</td>
		</c:forEach>
	</table>
	
	<form >
		<input type="hidden" id="customerId" name="customerId" value=${customerId}>
		<input type="submit" formaction="/customer/fullDelete" value="Delete Customer and All connected Orders">
		<br>

	</form>
	<br>
	<form>
		<input type="submit" value="Cancel" formaction="/customer/customersList" />
	</form>

</body>

</html>
