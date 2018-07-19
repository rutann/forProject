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
	<h2>Orders list ${message}</h2>

	<table>
		<tr>
			<th>ID</th>
			<th>Amount</th>
			<th>Order Date</th>
			<th>SalePersonId</th>
			<th>CustomerId</th>
			<th>Update</th>
			<th>Delete</th>
			<th>SalePerson</th>
			<th>Customer</th>
		</tr>
		<c:forEach items="${orders}" var="order" varStatus="status">
			<tr>
				<td>${order.id}</td>
				<td>${order.amount}</td>
				<td>${order.orderDate}</td>
				<td>${order.salePersonId}</td>
				<td>${order.customerId}</td>

				<td>
					<form>
						<input type="hidden" id="orderId" name="orderId" value=${order.id}>
						<input type="submit" value="Update"
							formaction="/order/updateOrder">
					</form>
				</td>
				<td>
					<form>
						<input type="hidden" id="orderId" name="orderId" value=${order.id}>
						<input type="submit" value="Delete" formaction="/order/deleteById">
					</form>
				</td>

				<td>
					<form>
						<input type="hidden" id="salePersonId" name="salePersonId" value=${order.salePersonId}>
						<input type="hidden" id="orderId" name="orderId" value=${order.id}>
						<input type="submit" value="Show SalePerson" formaction="/salePeople/getSalePersonByIdForOrder">
					</form>

					
				</td>
				<td>
					<form>
						<input type="hidden" id="customerId" name="customerId" value=${order.customerId}>
						<input type="hidden" id="orderId" name="orderId" value=${order.id}>
						<input type="submit" value="Show Customer" formaction="/customer/getCustomerByIdForOrder">
					</form>
			</tr>
		</c:forEach>
	</table>
<br>
	<form>
		<input type="submit" value="Create new"
			formaction="/order/create" />
	</form>
	<br>
    <input onclick="myFunction()" type="submit" value="Delete All">

	<script>
		function myFunction() {

			var r = confirm("All Orders\nwill be deleted. Are you sure?");
			if (r == true) {
				window.location.href = "/order/deleteAll";
			} else {
				window.location.href = "/order/ordersList";
			}
		}
	</script>



	
</body>

</html>
