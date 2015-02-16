var mPrice = -1.0;
var UID = "";

function setMaxPrice(price) {
	var price = $('#max_price').val();
	mPrice = parseFloat(price);
	$.ajax({
		type: "GET",
		url: "/cs480/lunchbox/getUID",
		data: {},
		success: function(result) {
			UID = result;
			$('#dynContent').html("<center><p></p><label>Lunchbox Builder</label><div id=\"Lunchbox_Foods\" style=\"display: inline-block; vertical-align: top; width: 33%\"></div><div id=\"Foods_Under\" style=\"display: inline-block; width: 33%\"></div></center>");
            getUserLunchbox();
            getPriceTableUnder();
		},
		error: function(jqXHR, exception) {
			alert("Failed to get UID!");
		}
	});
}

function addFoodToLunchbox(price) {
	var tempPrice = price.toString().replace(".","_");
	$.ajax({
		type: "POST",
		url: "/cs480/lunchbox/" + price,
		data: {
			"UID" : UID
		},
		success: function(result) {
			mPrice -= price;
			getUserLunchbox();
			getPriceTableUnder();
		},
		error: function(jqXHR, exception) {
			alert("Failed to add food.");
		}
	});
}

function getPriceTableUnder() {
	if(mPrice){
		$.ajax(
			{
				type : "GET",
				url  : "/cs480/food/" + mPrice,
				data : {
			},
			success : function(result) {
				var test = "<table border = \"3\"><tr><td width=\"20%\">Location</td><td width=\"55%\">Item</td><td width=\"15%\">Price</td><td width=\"10%\"></td></tr>";
				for(i = 0; i < result.length; i++)
					{
						if(i%2 == 0)
						{
							test += "<tr bgcolor=\"#ccc\"><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td><td><button onClick=\"addFoodToLunchbox(" + result[i].price + ")\">Add</button></td></tr>";
						}
						else
						{
							test += "<tr><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td><td><button onClick=\"addFoodToLunchbox(" + result[i].price + ")\">Add</button></td></tr>";
						}
					}
				test += "</table>";
				$('#Foods_Under').html(test);
			},
			error: function (jqXHR, exception) {
				alert("Failed to get the price.");
			}
		});
	} else {
		alert("Invalid Price!");
	}
}

function getUserLunchbox() {
	$.ajax({
    	type: "GET",
    	url: "/cs480/lunchbox/" + UID,
    	data: {},
    	success: function(result) {
			var pageContent = "<table border = \"3\"><tr><td width=\"20%\">Location</td><td width=\"60%\">Item</td><td width=\"20%\">Price</td></tr>";
			for(i = 0; i < result.length; i++) {
				if(i%2 == 0) {
					pageContent += "<tr bgcolor=\"#ccc\"><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td></tr>";
				} else {
					pageContent += "<tr><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td></tr>";;
				}
			}
			pageContent += "</table>";
			$('#Lunchbox_Foods').html(pageContent);
		},
		error: function(jqXHR, exception) {
			alert("Failed to get user lunchbox!");
		}
    });
}