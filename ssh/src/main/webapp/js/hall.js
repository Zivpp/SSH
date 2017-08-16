var app = angular.module("hallApp", ['sshConnectionFactory','sshGeneralFactory']); 

app.controller("hallCtrl",['$scope','$http', '$location','httpFactory','generalFactory', 
	function($scope,$http,$location,httpFactory,generalFactory) {
	
	//*Parameter
	$scope.tree;
	$scope.iframeUrl;
	
	//*Function
	//S-initial()
	var initial = function(){
		
		$scope.tree = [];
		$scope.iframeUrl = null;
		
		httpFactory.post(
				"hallPageInitial.action", {//url
					data : {} 
				},function(data) { //success
					
//					$scope.tree.root = data.root;
//					$scope.tree.trunk = data.trunk;
//					$scope.tree.branch = data.branch;
					
					for(var i in data.root) {
						//get root
						$scope.tree.push({
							id : data.root[i].id,
							name : data.root[i].code,
							href : data.root[i].codeValue,
							isShow : false,
							trunk : []
						});
						
						//get trunk
						for(var j in data.trunk) {
							if(data.trunk[j].parentId == $scope.tree[i].id){
								
								//get branch
								var tmpTrunkId = generalFactory.clone(data.trunk[j].id);
								var tmpBranch = [];
								for(var k in data.branch){
									if(tmpTrunkId == data.branch[k].parentId){
										tmpBranch.push({
											id : data.branch[k].id,
											name : data.branch[k].code,
											url : data.branch[k].codeValue
										});
									};
								};
								
								//set trunk
								$scope.tree[i].trunk.push({
									id : data.trunk[j].id,
									name : data.trunk[j].codeValue,
									isShow : false,
									branch : tmpBranch
								});
							};
						};
					};

				},function(data) { //error
					confirm('add cfg_System_Config data error : ' + data);
				}
		);	
		
	}//E-initial()
	
	//S-rootToggle()
	$scope.rootToggle = function(obj) {
		for(var i in $scope.tree){
			if($scope.tree[i].id == obj.id){
				obj.isShow = !obj.isShow;
			}else{
				$scope.tree[i].isShow = false;
			}
		}
	}//E-rootToggle()
	
	//S-trunkToggle()
	$scope.trunkToggle = function(obj) {
		obj.isShow = !obj.isShow;
	}//E-trunkToggle()
	
	$scope.getPage = function(url){
		
		var baseUrlTail =".html";
		
		if(url){
			hallIframe.contentWindow.location.reload(true); //force reload
			$scope.iframeUrl = url + baseUrlTail;
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
	
	
	//*Initial
	initial();
	
}]);