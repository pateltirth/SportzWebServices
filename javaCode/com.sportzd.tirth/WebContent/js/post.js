/**
* js file for post.html
* Please use modern web browser as this file will not attempt to be
* compatible with older browsers. Use Chrome and open javascript console
* or Firefox with developer console.
*
* jquery is required
*/
$(document).ready(function() {
//console.log("ready");
var $post_example = $('#post_example');
/**
* This is for the Submit button
* It will trigger a ajax POST call to: api/v2/inventory
* This will submit a item entry to our inventory database
*/
$('#submit_it').click(function(e) {
//console.log("submit button has been clicked");
e.preventDefault(); //cancel form submit
var jsObj = $post_example.serializeObject()
, ajaxObj = {};
//console.log(jsObj);
ajaxObj = {
type: "POST",
url: "http://localhost:7001/com.sportzd.tirth/api/v1/users/visits",
data: JSON.stringify(jsObj),
contentType:"application/json",
error: function(jqXHR, textStatus, errorThrown) {
console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
},
success: function(data) {
//console.log(data);
if(data[0].HTTP_CODE == 200) {
$('#div_ajaxResponse').text( data[0].MSG );
}
},
complete: function(XMLHttpRequest) {
//console.log( XMLHttpRequest.getAllResponseHeaders() );
},
dataType: "json" //request JSON
};
$.ajax(ajaxObj);
});

});