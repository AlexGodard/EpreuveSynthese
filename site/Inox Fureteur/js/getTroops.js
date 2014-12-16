/*Ce script reçoit tous les troops d'un utilisateur connecté*/
$(document).ready(function() {
    /*lien vers le webservice*/
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/troops";
	var datatoken = localStorage.getItem("token");
	  $.ajax({
			url : URL,
			contentType: "application/json",
			data: 
			{
			   /*On envoie le token avec notre requête ajax pour prouver que l'utilisateur est authentifié*/
			   access_token : datatoken
			},
			success: function(response) {			
			  var UneTroop = response[0];
			  /*Pour chaque troop associé à l'utilisateur en BD, on créer un nouveau div avec les informations detaillés sur la troop en question*/
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
				
				/*Si l'utilisateur possède aucun troop, on lui indique*/
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