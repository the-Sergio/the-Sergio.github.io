
function healthCheck() {
	$.ajax(
			{
				type : "GET",
				url  : "/cs480/ping",
				data : {
				},
				success : function(result) {
					$('#status').text(result);
				},
				error: function (jqXHR, exception) {
					$('#status').text("Failed to get the status");
				}
			});
}


function deleteFood(foodId) {
	$.ajax(
			{
				type : "DELETE",
				url  : "/cs480/user/" + foodId,
				data : {
				},
				success : function(result) {
					location.reload();
				},
				error: function (jqXHR, exception) {
					alert("Failed to delete the food.");
				}
			});
}

function addFood() {

	var foodId = $('#input_id').val();
	var foodPrice = $('#input_price').val();
	var foodDescription = $('#input_description').val();

	if (foodId) {
		$.ajax(
				{
					type : "POST",
					url  : "/cs480/user/" + foodId,
					data : {
						"price" : foodPrice,
						"description" : foodDescription
					},
					success : function(result) {
						location.reload();
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the food. Please check the inputs.");
					}
				});
	} else {
		alert("Invalid food Id");
	}
}

function getFood(foodId) {
	var foodId = $('#query_id').val();
	if (foodId) {
		$.ajax(
				{
					type : "GET",
					url  : "/cs480/user/" + foodId,
					data : {
					},
					success : function(result) {
						alert(result.description);
						$('#result_id').text(result.id);
						$('#result_price').text(result.price);
						$('#result_description').text(result.description);
					},
					error: function (jqXHR, exception) {
						alert("Failed to get the food.");
					}
				});
	} else {
		alert("Invalid food Id");
	}
}

function getPrices(price) {
    var price = $('#query_price').val();
    price = price.replace(".","_");
    if(price){
       $.ajax(
				{
					type : "GET",
					url  : "/cs480/food/" + price,
					data : {
					},
					success : function(result) {
						var test = "<table id = \"ResultTable\" border = \"3\" align=\"center\" width=\"50%\" ><tr><td width=\"20%\">Location</td><td width=\"60%\">Item</td><td width=\"20%\">Price</td></tr>";
						for(i = 0; i < result.length; i++)
						{	
							if(i%2 == 0) 
							{
								test += "<tr bgcolor=\"#ccc\"><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td></tr>";
							}
							else
							{
								test += "<tr><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td></tr>";
							}
							
						}
						test += "</table>";
						$('#Test_label').html(test);
					},
					error: function (jqXHR, exception) {
						alert("Failed to get the price.");
					}
				});
    } else{
        alert("Invalid Price");
    }
}


