var app = angular.module("hallApp", []); 

app.controller("hallCtrl", function($scope,$http,$location) {
	
	$scope.iframUrl = "";
	
	$scope.angularJsList = ['Table','unknow','unknow'];
	
	//iframe url 前往
	$scope.going = function(url){
		
		if(url == 'Table'){
			$scope.iframUrl ="/ssh/pages/configSidebar.html";
		}else{
			$scope.iframUrl = "/ssh/pages/unknow1.html";
		}

		
		
	}
});