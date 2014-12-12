$(document).ready(function() {
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/troops";
	var datatoken = localStorage.getItem("token");
	  $.ajax({
			url : URL,
			contentType: "application/json",
			data: 
			{
			   access_token : datatoken
			},
			success: function(response) {
			  var UneTroop = response[0];
			  console.log(response[0]);
				$.each(response, function(troop){
					var UneTroop = response[troop];
					$("#grilleTroops").append("<div class='troops'><img class='imageTroop' src='" +
					UneTroop.imageUrl +
					"' alt='' >" + 
					"<a href='#'>" + 
					UneTroop.name + 
					"</a>" +
					"<div class='detailsTroop'><img class='stats' src='images/attack.png' alt='Attaque'></img><p>" + 
					UneTroop.attack + 
					"</p>" +"<img class='stats' src='images/defense.png' alt='Défense'><p>" + 
					UneTroop.defense +
					"</p>" +
					"<img class='stats' src='images/speed.png' alt='Vitesse'><p>" +
					UneTroop.speed +
					"</p></div>" +
					"</div>");
				});
				
				if(UneTroop == null)
				{
					$("#grilleTroops").append("<p>Vous n'avez pas de troops de capturées</p>");	
				}
				
			    return true;						   
			},
			error: function(response) {			
			   return false;						   
			}
		});     
});