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
	arguments[0].forEach( el => document.querySelector(el).classList.add("hide") );
	arguments[1].forEach( el => document.querySelector(el).classList.remove("hide") );
}

function summernoteSendFile(file , target){
	var data = new FormData();
	data.append("image",file);
	$.ajax({
		url: "/freeBoardImage",
		data: data,
		cache: false,
		contentType: false,
		processData: false,
		type: "post",
		success: function(data){
			console.log(data);
			console.log(target);
			if(data.status == "FNF"){
				alert("이미지 저장 실패 (파일누락)");
			} else if(data.status == "FSF"){
				alert("이미지 저장 실패 (서버복사)");
			} else {
				$(target).summernote("insertImage", data.url+data.name , data.name);
			}
		},
		error: function(err){
			console.log(err);
		}
	});
};
