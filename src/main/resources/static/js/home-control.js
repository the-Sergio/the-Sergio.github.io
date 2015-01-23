
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
    if(price){
       $.ajax(
				{
					type : "GET",
					url  : "/cs480/food/" + price,
					data : {
					},
					success : function(result) {
						
						alert(result.length);
						var test = "";
						
						for(i = 0; i < result.length; i++)
						{	
							test += result[i].id + "     " + result[i].price + "     " + result[i].description + "<br />";
						} 
							
						
						$('#Test_label').html(test); 
						
						/*$('#res_id').text(result[0].id);
						$('#res_price').text(result[0].price);
						$('#res_description').text(result[0].description);*/
					},
					error: function (jqXHR, exception) {
						alert("Failed to get the price.");
					}
				});
    } else{
        alert("Invalid Price");
    }
}


