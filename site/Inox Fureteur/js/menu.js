$(document).ready(function() {
	/* On construit le menu si l'utilisateur est connecté. */
	if(localStorage.getItem("token") != "")
	{    
	   console.log("in token");
	   $(".menu_block").html(
	   "<nav>" +
	   "<ul class='sf-menu'>" +
	   "<li class='current'><a href='index.html'>Accueil</a></li>" +
	   "<li><a href='troops.html'>Mes Troops</a></li>" +
	   "<li><a href='runes.html'>Mes Runes</a></li>" +
	   "<li id='btnDeConnexion'><a id='btnLogout'>Deconnexion</a></li>" +
	   "</ul></nav><div class='clear'></div>");
	}
	/* On construit le menu si l'utilisateur n'est pas connecté. */
	else
	{      
	   console.log("in not token");
	   $(".menu_block").html(
	   "<nav>" +
	   "<ul class='sf-menu'>" +
	   "<li class='current'><a href='index.html'>Accueil</a></li>" +
	   "<li id='btnConnexion'><a id='btnLogin'>Connexion</a></li>" + 
	   "</ul></nav>" +
	   "<div id='connexion'>" +
	   "<input value='Utilisateur' type='text' id='txtnomutilisateur' required>" +
	   "<input value='Mot de passe' type='password' id='txtmotdepasse' required></div><div class='clear'></div>");
	}

	/* Lorsqu'on clique une première fois sur les champs du nom d'utilisateur et du mot de passe, on efface le contenu. */
	$("#txtnomutilisateur").focus(function(){
		if ($("#txtnomutilisateur").val() == "Utilisateur"){
			$("#txtnomutilisateur").val("").css("color","black");
		}
	});
	
	$("#txtnomutilisateur").focusout(function(){
		if ($("#txtnomutilisateur").val() == ""){
			$("#txtnomutilisateur").val("Utilisateur").css("color","grey");
		}
	});
	
	$("#txtmotdepasse").focus(function(){
		if ($("#txtmotdepasse").val() == "Mot de passe"){
			$("#txtmotdepasse").val("").css("color","black");
		}
	});
	
	$("#txtmotdepasse").focusout(function(){
		if ($("#txtmotdepasse").val() == ""){
			$("#txtmotdepasse").val("Mot de passe").css("color","grey");
		}
	});
	
	
	
	
	
});