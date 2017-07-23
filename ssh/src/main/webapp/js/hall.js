var app = angular.module("hallApp", []); 

app.controller("hallCtrl", function($scope,$http,$location) {
	
	$scope.iframeUrl = ""; // The url of embed pages. Key decide/Only.
	
	$scope.getIframeUrl = function(url){ // get url of embed pages.
		
		var baseUrlHead = "/ssh/pages/";
		var baseUrlTail =".html";
		
		if(url){
			hallIframe.contentWindow.location.reload(true); //force reload
			$scope.iframeUrl = baseUrlHead + url + baseUrlTail;
		}		
		
	}
	
	//*Test
	$scope.angularJsList = ['Table','unknown','unknown'];
	
	//iframe url 前往
	$scope.going = function(url){
		
		if(url == 'Table'){
			$scope.iframeUrl ="/ssh/pages/configSidebar.html";
		}else{
			$scope.iframeUrl = "/ssh/pages/unknow1.html";
		}
	}
	//*Test End
	
});