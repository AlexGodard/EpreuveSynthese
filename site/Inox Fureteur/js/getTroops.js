$(document).ready(function() {
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/troops";
	console.log("in get troops");
	var datatoken = localStorage.getItem("token");
	  $.ajax({
			url : URL,
			contentType: "application/json",
			data: 
			{
			   access_token : datatoken
			},
			success: function(response) {
			    alert("in good");
				$.each(response, function(troop){
				var UneTroop = response[troop];
				
				var a = "<div class='box blue pb1'><img src='" +
				UneTroop.imageUrl +
				"' alt='' class='img_inner fleft'><div class='extra_wrapper'>" + 
				"<div class='text1'><a href='#'>" + 
				UneTroop.name + 
				"</a></div>" +
				"<p>Attack : " + 
				UneTroop.attack + 
				"</p>" +"<p>Defense : " + 
				UneTroop.defense +
				"</p>" +
				"<p>Speed : " +
				UneTroop.speed +
				"</p>" +
				"<div class='clear'></div>" +
				"</div>";
				
				console.log(a);
                $("#Troops").html("<div class='box blue pb1'><img src='" +
				UneTroop.imageUrl +
				"' alt='' class='img_inner fleft'><div class='extra_wrapper'>" + 
				"<div class='text1'><a href='#'>" + 
				UneTroop.name + 
				"</a></div>" +
				"<p>Attack : " + 
				UneTroop.attack + 
				"</p>" +"<p>Defense : " + 
				UneTroop.defense +
				"</p>" +
				"<p>Speed : " +
				UneTroop.speed +
				"</p>" +
				"<div class='clear'></div>" +
				"</div>");
				});
			    return true;						   
			},
			error: function(response) {			
			   alert("in fail " + response);
			   return false;						   
			}
		});     
});