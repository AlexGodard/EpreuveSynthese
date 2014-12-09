$(document).ready(function() {
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/explorateurs";
	var datatoken = localStorage.getItem("token");
	var sendValue = {};
	var erreur = false;
    
	$("#create").click(function() {	
	
	   $("#erreurs").html("<p></p>");
	
	   if($("#nomComplet").val() == "" || $("#nomExplorateur").val() == "" || $("#mdp").val() == "")
	   {
	       $("#erreurs").append("<p>Veuillez svp remplir tous les champs</p>");
		   erreur = true;
	   }
	   
	   if($("#nomComplet").val().length > 75)
	   {
	       $("#erreurs").append("<p>Votre nom complet ne peut pas etre plus de 75 caracteres</p>");
		   erreur = true;
	   }
	   
	   if( $("#nomExplorateur").val().length > 35 || $("#nomExplorateur").val().length < 8)
	   {
	       $("#erreurs").append("<p>Votre nom d'explorateur doit contenir entre 8 et 35 caracteres</p>");
		   erreur = true;
	   }
	   
	   if($("#mdp").val().length > 20 || $("#mdp").val().length < 8)
	   {
	       $("#erreurs").append("<p>Votre mot de passe doit contenir entre 8 et 20 caracteres</p>");
		   erreur = true;
	   }
	      
	   if(erreur == true)
	   {
	   	   return;
	   }
       else
	   {
	      sendValue.name = $("#nomComplet").val();
		  sendValue.username = $("#nomExplorateur").val();
		  sendValue.password = $("#mdp").val();
		  
		  $.ajax({
			url : URL,
			type : 'POST',
			dataType : 'json',
			contentType: 'application/json',
			data: JSON.stringify(sendValue),
			success: function(response) {
			localStorage.setItem("token",response.token.token);
			    window.location.replace("index.html");	
			    return true;				
		    },
			error: function(response) {
				return false;						   
		    }			
		});
	   }
	});
});