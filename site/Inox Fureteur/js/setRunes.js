$(document).ready(function() {
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/runes";
	var datatoken = localStorage.getItem("token");
	var runesBD;
	
	$("#addAir").click(function() {	
		$.ajax({
			url : URL,
			type : 'GET',
			dataType : 'json',
			data: 
			{
			   access_token : datatoken
			},
			success: function(response) {
		        runesBD = response;
			    return true;				
			},
			error: function(response) {			
			   alert("error");
			   return false;						   
			}
		});	
				
		runesBD.air = runesBD.air - 15;
				
		$.ajax({
			url : URL,
			type : 'PUT',
			dataType : 'json',
			data: 
			{
			   access_token : datatoken,
			   runes : runesBD
			},
			success: function(response) {
		        window.location.reload("index.html");	
			    return true;				
			},
			error: function(response) {			
			   alert(reponse);
			   return false;						   
			}
		});	
		
     }); 
});