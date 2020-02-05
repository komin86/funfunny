function elementChange() {
	
	if(arguments[0].innerHTML == "수정"){
		arguments[0].innerHTML = "이전";
		displayToggle(arguments[1],arguments[2]);
	} else {
		arguments[0].innerHTML = "수정";
		displayToggle(arguments[2],arguments[1]);
	}

}

function displayToggle(){

	if(typeof arguments[0] === 'string' ) {
		document.querySelector(arguments[0]).classList.add("hide");
	} else {
		arguments[0].forEach( el => document.querySelector(el).classList.add("hide") );
	}

	if(typeof arguments[1] === 'string' ) {
		document.querySelector(arguments[1]).classList.remove("hide");
	} else {
		arguments[1].forEach( el => document.querySelector(el).classList.remove("hide") );
	}
	
}

console.log(document.forms);
