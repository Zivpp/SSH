var app = angular.module("editMenuBarApp", ['sshConnectionFactory','sshGeneralFactory']); 

app.controller("editMenuBarCtrl",['$scope','$http','$location','httpFactory','generalFactory','$timeout',
	function($scope,$http,$location,httpFactory,generalFactory,$timeout) {
	
	//*Parameter
	$scope.root;
	$scope.trunk;
	$scope.branch;
	$scope.rootCount;
	$scope.trunkCount;
	$scope.branchCount;
	$scope.isActive_Trunk;
	$scope.isActive_Branch;
	
	//*Function
	//S-initial()
	var initial = function(){
		
		$scope.root = [];
		$scope.trunk = [];
		$scope.branch = [];
		$scope.rootCount = 0;
		$scope.trunkCount = 0;
		$scope.branchCount = 0;
		$scope.isActive_Trunk = false;
		$scope.isActive_Branch = false;
		

		httpFactory.post(
			"hallPageInitial.action", //url
			{data : {}}, //data
			function(data){
				
				$scope.root = data.root;
				$scope.rootCount = data.root.length;
				
				for(var i in  data.trunk){
					var tmpT = generalFactory.clone(data.trunk[i]);
					tmpT.isShow = false;
					$scope.trunk.push(tmpT);
				}
				
				for(var j in  data.branch){
					var tmpB = generalFactory.clone(data.branch[j]);
					tmpB.isShow = false;
					$scope.branch.push(tmpB);
				}				
				
			},function(data){ //error
				console.log(data);
			}
		);
	}//E-initial()

	//S-activeRoot()
	$scope.activeRoot = function(r) {
		
		for(var i in $scope.root){
			$("#"+$scope.root[i].id).removeClass();
			if(r.id != $scope.root[i].id){
				$("#"+$scope.root[i].id).addClass("btn btn-default");
			}else{
				$("#"+r.id).addClass("btn btn-primary");
			}
		}
		
		$scope.trunkCount = 0;
		for(var j in $scope.trunk){
			if($scope.trunk[j].parentId == r.id){
				$scope.trunk[j].isShow = true;
				$scope.trunkCount++;
			}else{
				$scope.trunk[j].isShow = false;
			}
		}
		
		
		$scope.isActive_Trunk = true;
		$scope.isActive_Branch = false;
		
	}//E-activeRoot()
	
	//S-activeTrunk()
	$scope.activeTrunk = function(t) {
		
		for(var i in $scope.trunk){
			$("#"+$scope.trunk[i].id).removeClass("btn btn-default").removeClass("btn btn-success");
			if(t.id != $scope.trunk[i].id){
				$("#"+$scope.trunk[i].id).addClass("btn btn-default");
			}else{
				$("#"+t.id).addClass("btn btn-success");
			}
		}
		
		$scope.branchCount = 0;
		for(var j in $scope.branch){
			if($scope.branch[j].parentId == t.id){
				$scope.branch[j].isShow = true;
				$scope.branchCount++;
			}else{
				$scope.branch[j].isShow = false;
			}
		}
		
		$scope.isActive_Branch = true;
	}//E-activeTrunk()
	
	//*Initialize
	initial();
	
	//---
	

}]);