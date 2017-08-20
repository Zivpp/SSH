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
	$scope.eOptions;
	$scope.editResponseStr;
	$scope.newItem;
	$scope.currentHierarchy;
	
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
		$scope.rootEditColumn = ['id','codeCate','code','codeDesc','isNew'];
		$scope.trunkEditColumn = ['id','codeCate','code','codeDesc','parentId','isNew'];
		$scope.branchEditColumn = ['id','codeCate','code','codeDesc','parentId','codeValue','isNew'];
		$scope.eOptions = [];
		$scope.newItem = [];
		$scope.currentHierarchy = [{r:null,t:null,b:null}];
		
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
		activeBtn(r);
		computeCount(r,'t');
		$scope.isActive_Trunk = true;
		$scope.isActive_Branch = false;
		
	}//E-activeRoot()
	
	//S-activeTrunk()
	$scope.activeTrunk = function(t) {
		edit(t);
		activeBtn(t);
		computeCount(t,'b')
		$scope.isActive_Branch = true;
	}//E-activeTrunk()
	
	//S-activeBranch()
	$scope.activeBranch = function(b) {
		edit(b);
		activeBtn(b);
	}//E-activeBranch()
	
	//S-computeCount()
	var computeCount = function(obj,type,ch){

		if(type != 'r'){
			//current hierarchy for add item
			if(ch){
				if(type == 't'){
					for(var i in $scope.root){
						if($scope.root[i].id == ch['r']){
							obj = $scope.root[i];
						}
					}
				}else{
					for(var i in $scope.trunk){
						if($scope.trunk[i].id == ch['t']){
							obj = $scope.trunk[i];
						}
					}
				}
			}
			
			var target = [];
			if(type == 't') {
				$scope.trunkCount = 0;
				target = $scope.trunk;
					
			}else {			
				$scope.branchCount = 0;
				target = $scope.branch;
			}
			
			for(var j in target){
				if(target[j].parentId == obj.id){
					target[j].isShow = true;
					if(type=='t'){
						$scope.trunkCount++;
					}else{
						$scope.branchCount++;
					}
				}else{
					target[j].isShow = false;
				}
			}
		}
		
	}//E-computeCount()
	
	//S-activeBtn()
	var activeBtn = function(o) {
		
		var obj = generalFactory.clone(o);
		var type = obj.editType;
		var target = [];
		var btnDefault = "btn btn-default";
		var btnCss = null;
		
		switch(type) {
		    case 'r':
		    	target = $scope.root;
		    	btnCss = "btn btn-primary";
		        break;
		    case 't':
		    	target = $scope.trunk;
		    	btnCss = "btn btn-success";
		        break;
		    case 'b':
		    	target = $scope.branch;
		    	btnCss = "btn btn-warning";
		        break;
		};
		for(var i in target){
			$("#"+target[i].id).removeClass(btnDefault).removeClass(btnCss);
			if(obj.id != target[i].id){
				$("#"+target[i].id).addClass(btnDefault);
			}else{
				$("#"+obj.id).addClass(btnCss);
			}
		}
		
	}//E-activeBtn()
	
	//S-edit()
	var edit = function(o) {

		$scope.isEditActive = true;
		var obj = generalFactory.clone(o);
		var type = obj.editType;
		$scope.currentHierarchy[type] = obj.id;
		
		//current edit and control show attr
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
		
		//parent options
		$scope.eOptions = [];
		$scope.eOptions = getOptions(o);
		
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
		};
	}//E-edit()
	
	//S-getOptions()
	var getOptions = function(o) {
		
		var type = o.editType;
		var result = [];
		var target = [];
		
		if(type == 't'){
			target = generalFactory.clone($scope.root);
		}else if(type == 'b'){
			//找出所屬的 trunk group
			var bpId = o.parentId; //branch parentId
			var rId = null;
			for(var i in $scope.trunk){
				if($scope.trunk[i].id == bpId){
					rId = $scope.trunk[i].parentId;
				}
			}
			var tmptarget = generalFactory.clone($scope.trunk);
			for(var i in tmptarget){
				if(tmptarget[i].parentId == rId){
					target.push(tmptarget[i]);
				}
			}
		}
		//set to result
		for(var i in target){
			result.push({
				id : target[i].id,
				name : target[i].code
			});
		}
		
		return result;
	}//E-getOptions()
	
	//S-save()
	$scope.save = function() {
		httpFactory.post(
				"tvEditSave.action", //url
				{data : {
					tvEditDataList : $scope.editObjList
				}},
				function(data){
					
					if(data){
						$scope.editResponseStr ="SUCCESS"
					}else{
						$scope.editResponseStr ="FAIL"
					}
		
					openModel('editResponseModel');
					
					initial();

				},function(data){ //error
					console.log(data);
				}
			);
	}//E-save()
	
	//S-remove()
	$scope.remove = function() {
		
		if(!$scope.currentEditObj.isNew){
			
			if(confirm("這是已存在的節點, 確定刪除 ?")){
				httpFactory.post(
						"tvEditRemove.action", //url
						{data : {
							tvRemoveId : $scope.currentEditObj.id
						}},
						function(data){
							
							if(data.massage){
								$scope.editResponseStr ="FAIL : " +  data.massage;
							}else{
								$scope.editResponseStr ="SUCCESS";
							}
							openModel('editResponseModel');
							
							initial();

						},function(data){ //error
							console.log(data);
						}
					);
			}else{
				//TODO
			}
		}else{
			if(confirm("確定刪除待新增節點?")){
				
				var tmpId = $scope.currentEditObj.id;
				for(var i in $scope.editObjList){
					if($scope.editObjList[i].id = tmpId){
						$scope.editObjList.splice(i,1);
					}
				}
				
				for(var i in $scope.root){
					if($scope.root[i].id == tmpId){
						if(isNochildren($scope.root[i])){
							$scope.root.splice(i),1;
							$scope.isActive_Trunk = false;
							$scope.isActive_Branch = false;
						}else{
							$scope.editResponseStr ="FAIL : Unable to remove. Child(ren) node(s) existed in target node.";
							openModel('editResponseModel');
						}
					}
				}
				for(var i in $scope.trunk){
					if($scope.trunk[i].id == tmpId){
						if(isNochildren($scope.trunk[i])){
							$scope.trunk.splice(i),1;
							$scope.isActive_Trunk = true;
							$scope.isActive_Branch = false;
						}else{
							$scope.editResponseStr ="FAIL : Unable to remove. Child(ren) node(s) existed in target node.";
							openModel('editResponseModel');
						}
					}
				}
				for(var i in $scope.branch){
					if($scope.branch[i].id == tmpId){
						$scope.branch.splice(i),1;
					}
				}
			}else{
				//TODO
			}
		}
	}//E-remove()
	
	var isNochildren = function(obj) {
		
		var tmpId = obj.id;
		
		for(var i in $scope.branch){
			if(tmpId == $scope.branch[i].parentId){
				return false;
			}
		}
		for(var i in $scope.trunk){
			if(tmpId == $scope.trunk[i].parentId){
				return false;
			}
		}
		
		return true;
	}
	
	//S-addRoot()
	$scope.addNote = function(type) {
		
		//get id
		var target = [];
		var pId = null;
		switch(type) {
		    case 'r' :
		    	target = $scope.root;
		    	pId = 0;
		        break;
		    case 't' :
		    	target = $scope.trunk;
		    	pId = $scope.currentHierarchy['r'];
		        break;
		    case 'b' :
		    	target = $scope.branch;
		    	pId = $scope.currentHierarchy['t'];
		        break;
		}
		
		var newObj = generalFactory.clone(target[0]);
		for(var i in newObj){
			if(i != "editType"){
				newObj[i] = null;
			}
		}
		var newCode = null;
		if($scope.newItem.length < 1){
			newCode = 1;
		}else{
			newCode = $scope.newItem.length +1;
		}
		newObj.code = "newItem"+newCode;
		newObj.id = getNewId(target);
		newObj.parentId = pId;
		newObj.isNew = true;
		$scope.newItem.push(newObj);
		target.push(newObj);
		computeCount(null,type,$scope.currentHierarchy);

	}//E-addRoot(0
	
	//S-getNewId()
	var getNewId = function(obj) {
		var result = 0;
		for(var i in obj){
			if(result <= obj[i].id){
				result = obj[i].id + 1 ;
			}
		}
		return result;
	}//E-getNewId()
	
	//*Model
	var openModel = function(id) {
		$('#'+id).modal('show');
	}
	
	var closeModel = function(id) {
		$('#'+id).modal('hide');
	}
	
	
	//*Initialize
	initial();
	
	//---
	

}]);