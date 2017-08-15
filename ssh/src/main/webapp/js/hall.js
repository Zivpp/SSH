var app = angular.module("hallApp", ['sshConnectionFactory','sshGeneralFactory']); 

app.controller("hallCtrl",['$scope','$http', '$location','httpFactory','generalFactory', 
	function($scope,$http,$location,httpFactory,generalFactory) {
	
	//*Parameter
	$scope.tree;
	
	//*Function
	//S-initial()
	var initial = function(){
		
		$scope.tree = {
			root : [],
			trunk : [],
			Branch : []
		};
		
//		httpFactory.post(
//				"hallPageInitial.action", {//url
//					data : {} 
//				},function(data) { //success
//					
//					tree.root = data.root;
//					tree.trunk = data.trunk;
//					tree.Branch = data.Branch;
//					
//				},function(data) { //error
//					confirm('add cfg_System_Config data error : ' + data);
//				}
//		);	
		
	}//E-initial()
	
	
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
	
}]);