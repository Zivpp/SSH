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
	$scope.currentEditObj;
	$scope.isEditActive;
	$scope.editObjList;
	$scope.rootEditColumn;
	$scope.trunkEditColumn;
	$scope.branchEditColumn;
	
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
		$scope.currentEditObj = [];
		$scope.isEditActive = false;
		$scope.editObjList = [];
		$scope.rootEditColumn = ['id','code','codeDesc'];
		$scope.trunkEditColumn = ['id','code','codeDesc','parentId'];
		$scope.branchEditColumn = ['id','code','codeDesc','parentId','codeValue'];
		
		httpFactory.post(
			"hallPageInitial.action", //url
			{data : {}}, //data
			function(data){

				for(var i in data.root){
					var tmpR = generalFactory.clone(data.root[i]);
					tmpR.editType = 'r';
					$scope.root.push(tmpR);
				}
				$scope.rootCount = data.root.length;
				
				for(var i in  data.trunk){
					var tmpT = generalFactory.clone(data.trunk[i]);
					tmpT.isShow = false;
					tmpT.editType = 't';
					$scope.trunk.push(tmpT);
				}
				
				for(var i in  data.branch){
					var tmpB = generalFactory.clone(data.branch[i]);
					tmpB.isShow = false;
					tmpB.editType = 'b';
					$scope.branch.push(tmpB);
				}				
				
			},function(data){ //error
				console.log(data);
			}
		);
	}//E-initial()

	//S-activeRoot()
	$scope.activeRoot = function(r) {
		
		edit(r);
		
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
		
		edit(t);
		
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
	
	//S-activeBranch()
	$scope.activeBranch = function(b) {
		
		edit(b);
		
		for(var i in $scope.branch){
			$("#"+$scope.branch[i].id).removeClass("btn btn-default").removeClass("btn btn-warning");
			if(b.id != $scope.branch[i].id){
				$("#"+$scope.branch[i].id).addClass("btn btn-default");
			}else{
				$("#"+b.id).addClass("btn btn-warning");
			}
		}
		
	}//E-activeBranch()
	
	var edit = function(o) {

		$scope.isEditActive = true;
		var obj = generalFactory.clone(o);
		
		//current edit and control show attr
		var type = obj.editType;
		var tmpColumn = [];
		$("#editType").removeClass();
		var addClassStr = "";
		if(type == 'r'){
			addClassStr = "btn btn-primary btn-lg";
			tmpColumn = $scope.rootEditColumn;
		}else if(type == 't') {
			addClassStr = "btn btn-success btn-lg";
			tmpColumn = $scope.trunkEditColumn;
		}else if(type == 'b') {
			addClassStr = "btn btn-warning btn-lg";
			tmpColumn = $scope.branchEditColumn;
		}
		$("#editType").addClass(addClassStr);
		
		for(var attr in obj){
			if(tmpColumn.indexOf(attr) < 0){
				delete obj[attr];
			}
		}
		
		//push be edit obj to editObjList
		if($scope.editObjList.length < 1){
			$scope.editObjList.push(obj);
		}else{
			var isNotExist = true;
			for(var i in $scope.editObjList){
				if($scope.editObjList[i].id == obj.id){
					isNotExist = false;
					break;
				}
			}
			if(isNotExist){
				$scope.editObjList.push(obj);
			}
		};
		
		//Call by address for ng-model
		$scope.currentEditObj = [];
		if($scope.editObjList.length > 0){
			for(var i in $scope.editObjList){
				if($scope.editObjList[i].id == obj.id){
					$scope.currentEditObj = $scope.editObjList[i];
					break;
				}
			}
		}
	}
	
	console.log($scope.editObjList);
	
	//*Initialize
	initial();
	
	//---
	

}]);