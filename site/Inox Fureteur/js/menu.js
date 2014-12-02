$(document).ready(function() {
	//On va chercher le nom du fichier pour savoir où on se situe sur le site.
	var fullPath = window.location.pathname;
	var filename = fullPath.replace(/^.*[\\\/]/, '');

	/* On construit le menu si l'utilisateur est connecté. */
	if(localStorage.getItem("token") != "")
	{
		construireMenu();
		
	}
	/* On construit le menu si l'utilisateur n'est pas connecté. */
	else
	{      
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
	
	
	
	function construireMenu(){
		var elementMenu = "<nav>" +
							"<ul class='sf-menu'>";
		
		if(filename == "index.html")
			elementMenu += "<li class='current'><a href='index.html'>Accueil</a></li>";
		else
			elementMenu += "<li><a href='index.html'>Accueil</a></li>";
		if(filename == "troops.html")
			elementMenu += "<li class='current'><a href='troops.html'>Mes Troops</a></li>";
		else
			elementMenu += "<li><a href='troops.html'>Mes Troops</a></li>";
		if(filename == "runes.html")
			elementMenu += "<li class='current'><a href='runes.html'>Mes Runes</a></li>";
		else
			elementMenu += "<li><a href='runes.html'>Mes Runes</a></li>";
			
		elementMenu += "<li id='btnDeConnexion'><a id='btnLogout'>Deconnexion</a></li></ul></nav><div class='clear'></div>"
		
		$(".menu_block").html(elementMenu);
	}
	
});