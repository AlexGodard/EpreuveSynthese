/*Ce srcipt est utilisé lors du login de l'utilisateur*/
$(document).ready(function() {
    /*Le webservice appellé*/
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/explorateurs";

	$("#btnLogin").click(function() { 
	   /*On saisie le nom d'utilisateur et son mot de passe des inputs affiché à l'écran*/
		URL += "?username=" + $("#txtnomutilisateur").val() 
							+ "&password=" + $("#txtmotdepasse").val();	
		/*Envoi de la demande au webservice*/					
		$.ajax({
			url : URL,
			type : 'GET',
			dataType : 'json',
			success: function(response) {
			    /*Si le nom d'utilisateur et le mot de passe sont valids, on connect l'utilisateur en enregistrant sont token côté client*/
			    localStorage.setItem("token","");
			    localStorage.setItem("token",response.token.token);
				/*On recharge la page pour changer le menu*/
			    window.location.reload("index.html");		
			    return true;						   
			},
			error: function(response) {	
               /*Si l'authentification n'est pas exacte, on affiche un message d'erreur*/			
			   alert("Veuillez verifier votre nom d'utilisateur et votre mot de passe");
			   return false;						   
			}
		});	
	});          
});