var app = angular.module("hallApp", []); 

app.controller("hallCtrl", function($scope,$http,$location) {
	
	$scope.iframUrl = ""; // The url of embed pages. Key decide/Only.
	
	$scope.getIframUrl = function(url){ // get url of embed pages.
		
		var baseUrlHead = "/ssh/pages/";
		var baseUrlTail =".html";
		
		if(url && url.length != 0){
			$scope.iframUrl = baseUrlHead + url + baseUrlTail;
		}		
		
	}
	
	//*Test
	$scope.angularJsList = ['Table','unknow','unknow'];
	
	//iframe url 前往
	$scope.going = function(url){
		
		if(url == 'Table'){
			$scope.iframUrl ="/ssh/pages/configSidebar.html";
		}else{
			$scope.iframUrl = "/ssh/pages/unknow1.html";
		}
	}
	//*Test End
	
});