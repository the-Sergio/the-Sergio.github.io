var mPrice = -1.0;
var totalPrice = -1.0;
var UID = "";

function setMaxPrice(price) {
	var price = $('#max_price').val();
	mPrice = parseFloat(price);
	totalPrice = 0.0;
	$.ajax({
		type: "GET",
		url: "/cs480/lunchbox/getUID",
		data: {},
		success: function(result) {
			UID = result;
			var htm = "<center>";
			htm += "<p></p><label>Lunchbox Builder</label>";
			htm += "<div id=\"MaxPrice\" style=\"display: inline\"></div>";
			htm += "<div id=\"BoxTotal\" style=\"display: inline-block\"></div><div id=\"empty\"></div>";
			htm += "<div id=\"Lunchbox_Foods\" style=\"display: inline-block; vertical-align: top; width: 33%\"></div>";
			htm += "<div id=\"Foods_Under\" style=\"display: inline-block; vertical-align: top; width: 33%\"></div>";
			htm += "</center>";
			$('#dynContent').html(htm);
            updateUI();
		},
		error: function(jqXHR, exception) {
			alert("Failed to get UID!");
		}
	});
}

function updateUI() {
    $('#MaxPrice').html("Current funds: $" + Math.abs(mPrice).toFixed(2));
    $('#BoxTotal').html("&nbsp;Lunchbox total: $" + Math.abs(totalPrice).toFixed(2));
    getUserLunchbox();
    getPriceTableUnder();
}

function addFoodToLunchbox(description, id, price) {
	$.ajax({
		type: "POST",
		url: "/cs480/lunchbox/add",
		data: {
			"UID" : UID,
			"Description" : description.replace('~', '\''),
			"ID" : id.replace('~', '\''),
			"Price" : price
		},
		success: function(result) {
			mPrice -= parseFloat(result);
			totalPrice += parseFloat(result);
			updateUI();
		},
		error: function(jqXHR, exception) {
			alert("Failed to add food.");
		}
	});
}

function remFoodFromLunchbox(description, id, price) {
	$.ajax({
		type: "POST",
		url: "/cs480/lunchbox/rem",
		data: {
			"UID" : UID,
			"Description" : description.replace('~', '\''),
			"ID" : id.replace('~', '\''),
			"Price" : price
		},
		success: function(result) {
			mPrice += parseFloat(result);
			totalPrice -= parseFloat(result);
			updateUI();
		},
		error: function(jqXHR, exception) {
			alert("Failed to add food.");
		}
	});
}

function getPriceTableUnder() {
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
                        test += "<tr bgcolor=\"#ccc\"><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td><td><button onClick=\"addFoodToLunchbox(\'" + result[i].description.replace('\'', '~') + "\',\'" + result[i].id.replace('\'', '~') + "\',\'" + result[i].price + "\')\">Add</button></td></tr>";
                    }
                    else
                    {
                        test += "<tr><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td><td><button onClick=\"addFoodToLunchbox(\'" + result[i].description.replace('\'', '~') + "\',\'" + result[i].id.replace('\'', '~') + "\',\'" + result[i].price + "\')\">Add</button></td></tr>";
                    }
                }
            test += "</table>";
            $('#Foods_Under').html(test);
        },
        error: function (jqXHR, exception) {
            alert("Failed to get the price.");
        }
    });
}

function getUserLunchbox() {
	$.ajax({
    	type: "GET",
    	url: "/cs480/lunchbox/" + UID,
    	data: {},
    	success: function(result) {
			var pageContent = "<table border = \"3\"><tr><td width=\"20%\">Location</td><td width=\"60%\">Item</td><td width=\"20%\">Price</td><td></td></tr>";
			for(i = 0; i < result.length; i++) {
				if(i%2 == 0) {
					pageContent += "<tr bgcolor=\"#ccc\"><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td><td><button onClick=\"remFoodFromLunchbox(\'" + result[i].description.replace('\'', '~') + "\',\'" + result[i].id.replace('\'', '~') + "\',\'" + result[i].price + "\')\">Remove</button></td></tr>";
				} else {
					pageContent += "<tr><td>" + result[i].description + "</td><td>" + result[i].id + "</td><td>$" + result[i].price + "</td><td><button onClick=\"remFoodFromLunchbox(\'" + result[i].description.replace('\'', '~') + "\',\'" + result[i].id.replace('\'', '~') + "\',\'" + result[i].price + "\')\">Remove</button></td></tr>";
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

window.onpagehide = function () {
    $.ajax({
        type: "DELETE",
        url: "/cs480/lunchbox/" + UID,
        data: {}
    });
}