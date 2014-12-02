var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/runes";
	$("#btnLogin").click(function() { 
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