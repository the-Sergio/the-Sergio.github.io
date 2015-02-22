<html>

<head>
    <title>CS480 Demo Web Service</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
    <link rel="stylesheet" type="text/css" href="\mystyle.css"\>
</head>





<body>    
    
    <div>
        This is a simple page to demonstrate the web UI development. 
        The key tools and techniques used include:
        <ul>
            <li>HTML - Obviously</li>
            <li><a href="http://freemarker.org/">FreeMarker</a></li>
            <li><a href="http://jquery.com/">JQuery</a></li>
            <li><a href="http://api.jquery.com/jquery.ajax/">JQuery - AJAX</a></li>
        </ul>
    </div>

    <hr>

    <div>
        <div>
            <label>Food List</label>
            <table border="1">            
                <tr>
                    <td>Food ID</td>
                    <td>Price</td> 
                    <td>Description</td> 
                    <td>Delete</td>
                </tr>
                <#list foods as food> 
                        <tr>
                            <td>${food.id}</td>
                            <td>${food.price}</td>
                            <td>${food.description}</td>
                            <td><button onclick="deleteFood('${food.id}')">Delete</button></td>
                        </tr>
                </#list>
             
            </table>
        </div>
        
        <hr>
        
        <div>
            <label>Add Food</label>
            <table border="1">
                <tr>
                    <td>Food ID</td>
                    <td>Price</td> 
                    <td>Description</td>                     
                    <td>Add</td>
                </tr>                
                <tr>
                    <td><input type="text" id="input_id"></td>
                    <td><input type="text" id="input_price"></td>
                    <td><input type="text" id="input_description"></td>                    
                    <td><button onclick="addFood()">Add</button></td>
                </tr>
            </table>
        </div>

        <hr>

        <div>
            <label>Query Food</label>
            <input type="text" id="query_id"><button onclick="getFood()">Get</button>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>Price</td>
                    <td>Description</td>
                </tr> 
                <tr>
                    <td><label id="result_id"></td>
                    <td><label id="result_price"></td>
                    <td><label id="result_description"></td>
                </tr>
            </table>
        </div>
    </div>
 
    <hr>
    
    <div><center>
        <label>Grubbin Price</label>
        <input type="text" id="query_price" ><button onClick="getPrices()">Get</button>
        
        
        <label id="Test_label"></center>
        
        
    
    
    </div>
    </hr>
</body>

</html>