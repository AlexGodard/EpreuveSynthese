/*Ce script est appelé lorsque l'utilisateur se déconnecte*/
$(document).ready(function() {
    $("#btnLogout").click(function() { 
	   /*On vide le contenu de la variable local contenant le token*/
        localStorage.setItem("token","");
	   /*On retourne à la page d'acceuil*/
		window.location.reload("index.html");		
		return true;					
	});	
}); 