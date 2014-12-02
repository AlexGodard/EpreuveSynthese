$(document).ready(function() {
	$("#txtnomutilisateur").focus(function(){
		if ($("#txtnomutilisateur").val() == "Pseudonym"){
			$("#txtnomutilisateur").val("").css("color","black");
		}
	});
	
	$("#txtnomutilisateur").focusout(function(){
		if ($("#txtnomutilisateur").val() == ""){
			$("#txtnomutilisateur").val("Pseudonym").css("color","grey");
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
	
	
	
	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/explorateurs";
	$("#btnLogin").click(function() { 
		URL += "?username=" + $("#txtnomutilisateur").val() 
							+ "&password=" + $("#txtmotdepasse").val();	
		$.ajax({
			url : URL,
			type : 'GET',
			dataType : 'json',
			success: function(response) {
			    localStorage.setItem("token",response.token.token);
			    window.location.reload("index.html");		
			    return true;						   
			},
			error: function(response) {			
			   alert("Veuillez verifier votre nom d'utilisateur et votre mot de passe");
			   return false;						   
			}
		});	
	});          
});