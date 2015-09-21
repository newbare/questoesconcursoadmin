function somenteNumero() 
{ 
	if (event.keyCode<48 || event.keyCode>57) 
	{ 
		return false; 
	} 
}

function limitarTextArea(element, limit) { 
	if (element.value.length > limit) { 
		element.value = element.value.substring(0, limit);
	}	
}

function SomenteNumero(e){
	var tecla=(window.event)?event.keyCode:e.which;
	if((tecla>47 && tecla<58)) return true;
	else{
		if (tecla==8 || tecla==0) return true;
		else  return false;
	}
}