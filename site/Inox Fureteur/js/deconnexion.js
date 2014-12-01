$("#btnLogin").click(function() { 
        localStorage.removeItem("token");
		window.location.replace("index.html");		
		return true;					
	});	
}); 