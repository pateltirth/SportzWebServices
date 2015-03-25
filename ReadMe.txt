APIs for giving list of cities in JSON:

//1// 
http://localhost:7001/com.sportzd.tirth/api/v1/state/cities.json
We can list all the cities we have in database
or
we can handle it as a bad http request putting proper error message for users to specify proper state

//2//
List of cities from a state can be provided by either of these two ways.
--> Using query parameters:
http://localhost:7001/com.sportzd.tirth/api/v1/state/cities.json?state=AL
or
--> Using Path Parameters:
http://localhost:7001/com.sportzd.tirth/api/v1/state/cities.json/AL

//3//
Details about specific city of specific state:
http://localhost:7001/com.sportzd.tirth/api/v1/state/cities.json/AL/Tanana

//4//
List all cities in <radius> KM Radius from specifies <state> and <city>:
http://localhost:7001/com.sportzd.tirth/api/v1/state/cities.json/<state>/<city>/<radius>
or
http://localhost:7001/com.sportzd.tirth/api/v1/state/cities.json/radius?city=<city>?state=<state>?radius=<radius>

Note: here more then one cities with same name are possible so it is important to also specify state. If not provided we should handle that as bad http request.


APIs for providing details about user's visits
//5//
http://localhost:7001/com.sportzd.tirth/api/v1/users/visits/2
Here last path parameter "2" signifies the userID of the user. We list all the places he visited.
We handle bad request as
http://localhost:7001/com.sportzd.tirth/api/v1/users/visits/
with proper output message.
like:
Please specify your user ID like .../users/visits/xxx 
This is important, as we can not list details about all users if not provided his user Id.

//6//
post method implementation:
I have created a html page(postUserVisits.html) with a single submit button for data to be posted.
I have also written a java script(POST.js) which redirect to url ".../users/visits/" . Now in the POST method I am inserting the passed data (which is in json) into the database. 


Handling Bad Requests: 
Resons can be- spelling mistake, no method defined for requested url, or trying to hack by intruders.
We can put client side validations with java scripts for getting rid of first type. For wrong parameters passed (Like for wrong city name or state name) that can be done by server side validations (As done by me in methods). We can use encription algorithms for protecting our database from hackers. One of the way can be using the ESAPI 1.4.4 jar file. ESAPI is used to encode data.  

Handling large data set:
We can do that by few methods. For computations, we can develop some stored procedurs in database which computs data you need and provide efficiantly. We can also pass data in chunks if data is too large. 
-- 
Tirth Patel
