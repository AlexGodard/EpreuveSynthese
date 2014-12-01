$(document).ready(function() {
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/runes";
	var datatoken = localStorage.getItem("token");
	$(document).ready(function() {
	  $.ajax({
			url : URL,
			contentType: "application/json",
			data: 
			{
			   access_token : datatoken
			},
			success: function(response) {
				$("#air").html("x " + response.air);		
				$("#earth").html("x " + response.earth);		
				$("#fire").html("x " + response.fire);		
				$("#life").html("x " + response.life);		
				$("#logic").html("x " + response.logic);		
				$("#water").html("x " + response.water);
				$("#fusion").html("x " + response.fusion);
			    return true;						   
			},
			error: function(response) {			
			   alert("in fail " + response);
			   return false;						   
			}
		});
	});      
});