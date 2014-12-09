$(document).ready(function() {

	var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/explorateurs";
	$("#btnLogin").click(function() { 
		URL += "?username=" + $("#txtnomutilisateur").val() 
							+ "&password=" + $("#txtmotdepasse").val();	
		$.ajax({
			url : URL,
			type : 'GET',
			dataType : 'json',
			success: function(response) {
			    localStorage.setItem("token","");
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