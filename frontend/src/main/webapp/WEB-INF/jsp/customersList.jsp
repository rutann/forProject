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
			href="/customer/customersList">Customers list</a> <a
			href="/order/ordersList">Orders list </a>
	</div>
	<h2>Customers list ${message}</h2>

	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>City</th>
			<th>Rating</th>
			<th>SalePersonId</th>
			<th>Update</th>
			<th>Delete</th>
			<th>SalePerson</th>
			<th>Orders</th>
		</tr>
		<c:forEach items="${customers}" var="customer" varStatus="status">
			<tr>
				<td>${customer.id}</td>
				<td>${customer.name}</td>
				<td>${customer.city}</td>
				<td>${customer.rating}</td>
				<td>${customer.salePersonId}</td>

				<td>
					<form>
						<input type="hidden" id="customerId" name="customerId"
							value=${customer.id}> <input type="submit" value="Update"
							formaction="/customer/updateCustomer">
					</form>
				</td>
				<td>
					<form>
						<input type="hidden" id="customerId" name="customerId"
							value=${customer.id}> <input type="submit" value="Delete"
							formaction="/customer/deleteById">
					</form>
				</td>

				<td>
					<form>
						<input type="hidden" id="customerId" name="customerId"
							value=${customer.id}> <input type="hidden"
							id="salePersonId" name="salePersonId"
							value=${customer.salePersonId}>
							 <input type="submit"
							value="Show SalePerson"
							formaction="/salePeople/getSalePersonByIdForCustomer">
					</form>

				</td>
				<td>

					<form>
						<input type="hidden" id="customerId" name="customerId"
							value=${customer.id}> <input type="submit"
							value="Show orders" formaction="/order/getOrdersByCustomerId">
					</form>
				</td>

			</tr>

		</c:forEach>
	</table>
	<br>
	<form>
		<input type="submit" value="Create new"
			formaction="/customer/create" />
	</form>
	<br>
	<input onclick="myFunction()" type="submit" value="Delete All">

	<script>
		function myFunction() {

			var r = confirm("All Customers and connected Orders\nwill be deleted. Are you sure?");
			if (r == true) {
				window.location.href = "/customer/deleteAllAndConnectedOrders";
			} else {
				window.location.href = "/customer/customersList";
			}
		}
	</script>



</body>

</html>
