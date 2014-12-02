$(document).ready(function() {

  if(localStorage.getItem("token") != "")
  {    
       console.log("in token");
       $(".menu_block").html(
	   "<nav>" +
	   "<ul class='sf-menu'>" +
	   "<li class='current'><a href='index.html'>Accueil</a></li>" +
	   "<li><a href='troops.html'>Mes Troops</a></li>" +
	   "<li><a href='runes.html'>Mes Runes</a></li>" +
       "<li id='btnDeConnexion'><button id='btnLogout'>Deconnexion</button></li>" +
	   "</ul></nav><div class='clear'></div>");
  }
  else
  {      
       console.log("in not token");
       $(".menu_block").html(
       "<nav>" +
	   "<ul class='sf-menu'>" +
	   "<li class='current'><a href='index.html'>Accueil</a></li>" +
	   "<li id='btnConnexion'><button id='btnLogin'>Connexion</button></li>" + 
	   "</ul></nav>" +
       "<div id='connexion'>" +
	   "<input value='Pseudonym' type='text' id='txtnomutilisateur' required>" +
	   "<input value='Mot de passe' type='password' id='txtmotdepasse' required></div><div class='clear'></div>");
  }
	
});