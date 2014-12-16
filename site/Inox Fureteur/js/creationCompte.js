/*Ce script sera appellé lorsque un utilisateur désire créer un nouveau compte Inox*/
$(document).ready(function() {
    /*Le lien URL vers le webservice pour créer un compte*/
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/explorateurs";
	var sendValue = {};
    
	$("#create").click(function() {	
	   var erreur = false;
	   
	   $("#erreurs").html("<p></p>");
	   
	   /*On valid les informations de l'utilisateur dans les inputs affiché à l'écran*/
	   /*On affiche un div avec un message d'erreur approprié selon selon l'erreur*/
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
	      /*On saisie les valeurs des inputs (fourni par l'utilisateur) pour l'envoyer au serveur*/
	      sendValue.name = $("#nomComplet").val();
		  sendValue.username = $("#nomExplorateur").val();
		  sendValue.password = $("#mdp").val();
		  
		  /*On envoi la demande de création de compte au serveur via une requête ajax*/
		  $.ajax({
			url : URL,
			type : 'POST',
			dataType : 'json',
			contentType: 'application/json',
			/*On stringify les données qu'on n'a saisie de l'utilisateur*/
			data: JSON.stringify(sendValue),
			success: function(response) {
			    /*On enregistre le token de l'utilisateur localement, il est maintenant authentifié*/
			    localStorage.setItem("token",response.token.token);
				/*On dirige l'utilisateur vers l'écran d'acceuil*/
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