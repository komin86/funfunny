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

function summernoteSendFile(file){
	var data = new FormData();
	data.append("image",file);
	$.ajax({
		url: "/freeBoardImage",
		data: data,
		cache: false,
		contentType: false,
		processData: false,
		type: "post",
		success: function(url){
			console.log(url);
			if(url == "FNF"){
				alert("이미지 저장 실패 (파일누락)");
			} else if(url == "FSF"){
				alert("이미지 저장 실패 (서버복사)");
			} else {
				$('#summernote').summernote("insertImage", url , "테스트111");
			}
		},
		error: function(err){
			console.log(err);
		}
	});
};
