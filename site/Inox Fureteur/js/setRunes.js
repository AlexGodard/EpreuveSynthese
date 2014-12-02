$(document).ready(function() {
var URL = "https://tpsynthese-web-v5458545875-mlarameecstj.c9.io/runes";
var runesBD;
	$("#air").click(function() {
	
		$.ajax({
			url : URL,
			type : 'GET',
			dataType : 'json',
			success: function(response) {
		        runesBD = response;
			    return true;				
			},
			error: function(runes) {			
			   alert("Veuillez verifier votre nom d'utilisateur et votre mot de passe");
			   return false;						   
			}
		});	
		
		alert(runesBD.air);
		
		
		
		
		
     }); 
});