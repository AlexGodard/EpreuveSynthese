$(document).ready(function() {
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/runes";
	var datatoken = localStorage.getItem("token");
	var runesBD;
	   
	  $.ajax({
			url : URL,
			contentType: "application/json",
			data: 
			{
			   access_token : datatoken
			},
			success: function(response) {
				$("#air").html("x " + response.air);
                if(response.air > 15)
                {
				  $("#air").append("<button id='addAir'>Creer une rune de fusion</button>");
				  
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
							runesBD.air = runesBD.air - 15;  
							return true;				
			            },
							error: function(response) {			
							alert("error");
							return false;						   
						}
				  });				  
				  
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
						    alert(datatoken);
							alert("opsss" + response);
							return false;						   
						}
					});
				  });
				}				
				$("#earth").html("x " + response.earth);
                if(response.earth > 15)
                {
				  $("#earth").append("<button id='addEarth'>Creer une rune de fusion</button>");
				}					
				$("#fire").html("x " + response.fire);
                if(response.fire > 15)
                {
				  $("#fire").append("<button id='addFire'>Creer une rune de fusion</button>");
				}					
				$("#life").html("x " + response.life);
                if(response.life > 15)
                {
				  $("#life").append("<button id='addLife'>Creer une rune de fusion</button>");
				}					
				$("#logic").html("x " + response.logic);
                if(response.logic > 15)
                {
				  $("#logic").append("<button id='addLogic'>Creer une rune de fusion</button>");
				}					
				$("#water").html("x " + response.water);
				if(response.water > 15)
                {
				  $("#water").append("<button id='addWater'>Creer une rune de fusion</button>");
				}	
				$("#fusion").html("x " + response.fusion);
			    return true;						   
			},
			error: function(response) {			
			   return false;						   
			}
		});
		
		
		
});    

