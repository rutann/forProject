<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
			href="/customer/customersList">Customers list</a> <a href="/order/ordersList">Orders list </a>
	</div>

	<h2>SalePeople list  ${message}</h2>

	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>City</th>
			<th>Commissions</th>
			<th>Update</th>
			<th>Delete</th>
			<th>Customers</th>
			<th>Orders</th>
		</tr>
		<c:forEach items="${salePeople}" var="salePerson" varStatus="status">
			<tr>
				<td>${salePerson.id}</td>
				<td>${salePerson.name}</td>
				<td>${salePerson.city}</td>
				<td>${salePerson.commissions}</td>
				<td>
					<form >
					<input	type="hidden" id="salePersonId" name="salePersonId" value=${salePerson.id}>
					<input	type="submit" value="Update" formaction="/salePeople/updateSalePerson">
					</form>
				</td>
				<td>
					<form >
					<input	type="hidden" id="salePersonId" name="salePersonId" value=${salePerson.id}>
					<input	type="submit" value="Delete" formaction="/salePeople/deleteById">
					</form>
				</td>

				<td>
										
				<form >
					<input	type="hidden" id="salePersonId" name="salePersonId" value=${salePerson.id}>
					<input	type="submit" value="Show customers" formaction="/customer/getCustomersBySalePersonId">
					</form>
					</td>
				<td>
				
				<form >
					<input	type="hidden" id="salePersonId" name="salePersonId" value=${salePerson.id}>
					<input	type="submit" value="Show orders" formaction="/order/getOrdersBySalePersonId">
					</form>
					</td>

			</tr>
		</c:forEach>
	</table>
<br>
	<form>
		<input type="submit" value="Create new"
			formaction="/salePeople/create" />
	</form>
	<br>
		
	<input onclick="myFunction()" type="submit" value="Delete All">

	<script>
		function myFunction() {

			var r = confirm("All SalePeople and connected Orders\nwill be deleted. Are you sure?");
			if (r == true) {
				window.location.href = "/salePeople/deleteAllAndConnectedOrders";
			} else {
				window.location.href = "/salePeople/salePeopleList";
			}
		}
	</script>

	
	
	

</body>

</html>
