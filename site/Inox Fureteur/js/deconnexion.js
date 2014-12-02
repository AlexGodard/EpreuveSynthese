$(document).ready(function() {
    $("#btnLogout").click(function() { 
        localStorage.setItem("token","");
		window.location.reload("index.html");		
		return true;					
	});	
}); 