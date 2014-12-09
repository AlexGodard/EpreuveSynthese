$(document).ready(function() {

	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/runes";
	
	var datatoken = localStorage.getItem("token");
	
	var sendValue = 
	{
	  'air' : 0,
	  'earth' : 0,
	  'fire' : 0,
	  'life' : 0,
	  'logic' : 0,
	  'water' : 0
	};
		   
	$.ajax({
			url : URL,
			contentType: "application/json",
			data: 
			{
			   access_token : datatoken
			},
			success: function(response) {
			
			    sendValue.air = response.air;
				sendValue.earth = response.earth;
				sendValue.fire = response.fire;
				sendValue.life = response.life;
				sendValue.logic = response.logic;
				sendValue.water = response.water;
				
				$("#air").html("x " + response.air);
				
                if(response.air > 15)
                {
				  $("#air").append("<button id='addAir'>Creer une rune de fusion</button>");
				   $("#addAir").click(function() {	
				  
				   sendValue.air = sendValue.air - 15;	
				   sendValue.access_token = datatoken;			   
				 			   
				   $.ajax({
						url : URL,
						type : 'PUT',
						dataType : 'json',
						contentType: 'application/json',
						data: JSON.stringify(sendValue),
						success: function(response) {
							window.location.reload("runes.html");	
							return true;				
						},
						error: function(response) {
							return false;						   
						}
						
					});
				  });
				}	

				
				$("#earth").html("x " + response.earth);
				
                if(response.earth > 15)
                {
				  $("#earth").append("<button id='addEarth'>Creer une rune de fusion</button>");
				  $("#addEarth").click(function() {	
				  
				   sendValue.earth = sendValue.earth - 15;	
				   sendValue.access_token = datatoken;
				   
		   
				   $.ajax({
						url : URL,
						type : 'PUT',
						dataType : 'json',
						contentType: 'application/json',
						data: JSON.stringify(sendValue),
						success: function(response) {
							window.location.reload("runes.html");	
							return true;				
						},
						error: function(response) {
							return false;						   
						}
						
					});
				  });
				}	
				
				$("#fire").html("x " + response.fire);
				
                if(response.fire > 15)
                {
				  $("#fire").append("<button id='addFire'>Creer une rune de fusion</button>");
				   $("#addFire").click(function() {	
				  
				   sendValue.fire = sendValue.fire - 15;	
				   sendValue.access_token = datatoken;
				   	   
				   $.ajax({
						url : URL,
						type : 'PUT',
						dataType : 'json',
						contentType: 'application/json',
						data: JSON.stringify(sendValue),
						success: function(response) {
							window.location.reload("runes.html");	
							return true;				
						},
						error: function(response) {
							return false;						   
						}
						
					});
				  });
				}
				
				$("#life").html("x " + response.life);
				
                if(response.life > 15)
                {
				  $("#life").append("<button id='addLife'>Creer une rune de fusion</button>");
				   $("#addLife").click(function() {	
				  
				   sendValue.life = sendValue.life - 15;	
				   sendValue.access_token = datatoken;
				   			   
				   $.ajax({
						url : URL,
						type : 'PUT',
						dataType : 'json',
						contentType: 'application/json',
						data: JSON.stringify(sendValue),
						success: function(response) {
							window.location.reload("runes.html");	
							return true;				
						},
						error: function(response) {
							return false;						   
						}
						
					});
				  });
				}	
				
				$("#logic").html("x " + response.logic);
				
                if(response.logic > 15)
                {
				  $("#logic").append("<button id='addLogic'>Creer une rune de fusion</button>");
				   $("#addLogic").click(function() {	
				  
				   sendValue.logic = sendValue.logic - 15;	
				   sendValue.access_token = datatoken;
				      
				   $.ajax({
						url : URL,
						type : 'PUT',
						dataType : 'json',
						contentType: 'application/json',
						data: JSON.stringify(sendValue),
						success: function(response) {
							window.location.reload("runes.html");	
							return true;				
						},
						error: function(response) {
							return false;						   
						}
						
					});
				  });
				}				
				
				$("#water").html("x " + response.water);
				
				if(response.water > 15)
                {			
				  $("#water").append("<button id='addWater'>Creer une rune de fusion</button>");
				   $("#addWater").click(function() {	
				  
				   sendValue.water = sendValue.water - 15;	
				   sendValue.access_token = datatoken;
				   		   
				   $.ajax({
						url : URL,
						type : 'PUT',
						dataType : 'json',
						contentType: 'application/json',
						data: JSON.stringify(sendValue),
						success: function(response) {
							window.location.reload("runes.html");	
							return true;				
						},
						error: function(response) {
							return false;						   
						}
						
					});
				  });
				}
				  
				
				$("#fusion").html("x " + response.fusion);
			    return true;		
				
			},
			error: function(response) {			
			   return false;						   
			}
		});
		
		
		
		
});    

