var app = angular.module("sysCfgParamApp", []); 

app.controller("sysCfgParamCtrl",function($scope,$http,$location) {
	
	//*Parameter
	$scope.tableHeader;
	$scope.tableBody;
	$scope.addCfgSysConBean;
	$scope.editCfgSysConBean;
	$scope.editResponseStr;
	$scope.oldEditId;
	
	//*Function
	var initial = function(){
		
		$scope.tableHeader = [];
		$scope.tableBody = [];
		$scope.addCfgSysConBean = [];
		$scope.editCfgSysConBean = [];
		$scope.oldEditId = null;
		
		//datas
		$http({
			method : 'POST',
			url : "getSCPInitialData.action"
		}).success(function (data, status) {
			
			console.log('Post OK!!!', data);
			
			if(data && data.result && data.result.data){
				
				$scope.tableHeader = data.result.data.tableHeader;
				$scope.tableBody = data.result.data.tableBody;
				if(data.result.data.addCfgSysConBean){
					for(var attr in data.result.data.addCfgSysConBean){
						
	            		$scope.addCfgSysConBean.push({
	            			key : data.result.data.addCfgSysConBean[attr].name,
	            			value : null,
	            			type : data.result.data.addCfgSysConBean[attr].dataType
	            		});
	            		
	            		$scope.editCfgSysConBean.push({
	            			key : data.result.data.addCfgSysConBean[attr].name,
	            			value : null,
	            		});
	        		};
				}
				
			}else {
				console.log('No any cfg_System_Config data');
			}
			
		}).error(function (data, status) {
			console.log('get Cfg_System_Config initial data rrror', data);
		});
	}
	
	//*function
	//Start add()
	$scope.add = function(){
		
		var passCount = 0; //($scope.addCfgSysConBean must be all pass
		
		if($scope.addCfgSysConBean){
			for(var index in $scope.addCfgSysConBean){
				
				var error = false;
				var name = $scope.addCfgSysConBean[index].key;
				var type = $scope.addCfgSysConBean[index].type; //type = number or string
				var value = $scope.addCfgSysConBean[index].value;
				
				//1. 空白檢查
				if($scope.addCfgSysConBean[index] &&
						$scope.addCfgSysConBean[index].value &&
						$scope.addCfgSysConBean[index].value != null &&
						$scope.addCfgSysConBean[index].value.length != 0){					
					error = false;	
				}else{
					error = true;					
				}
				
				//2. 型態提醒
				if(type == 'number' && isNaN(value)){
					error = true;
					if(value == null){
						$scope.addCfgSysConBean[index].value = " *請填數字";
					}else{
						$scope.addCfgSysConBean[index].value = $scope.addCfgSysConBean[index].value + " *請填數字";
					}
						
				}
				
				//note
				if(error){
					var tmpId = $scope.addCfgSysConBean[index].key;
					$("#"+name).addClass("errorInput");
				}else{
					$("#"+name).removeClass("errorInput");
					passCount = passCount + 1;
				}
			}
		}		
		
		//Pass
		if($scope.addCfgSysConBean.length == passCount){
			
			var addData = {};
			for(var index in $scope.addCfgSysConBean){
				var tmpKey = $scope.addCfgSysConBean[index].key;
				var tmpValue = $scope.addCfgSysConBean[index].value;
				addData[tmpKey] = tmpValue;
			}
			
			var data = {
					addData : addData
			}
			
			$http({
				method : 'POST',
				url : "addCfgSystemConfig.action",
				data : data
			}).success(function (data, status) {
				
				$scope.closeModel('addModel');
				
				initial();
				
			}).error(function (data, status) {
				
			});
		}
	};//END add()
	
	//Start edit()
	$scope.edit = function(item){
		
		$scope.oldEditId = item[0];
		
		var data = {
				cfgSysId : item[0]
			};
		
		$http({
			method : 'POST',
			url : "searchCfgSysConById.action",
			data : data
		}).success(function (data, status) {
			
			//editCfgSysConBean init() 已經設定好欄位, 將 Value 放入即可
			if(data && data.result && data.result.data){
				
				var tmpValueData = data.result.data;				
				
				for(var i in $scope.editCfgSysConBean){
					
					var tmpKey = $scope.editCfgSysConBean[i].key;
					
					for(var key in tmpValueData){
						
						if(tmpKey == key){
							$scope.editCfgSysConBean[i].value = tmpValueData[key];
						}
					}
				}
			}
			
		}).error(function (data, status) {
			console.log(data);
		});
		
		//Open model
		$scope.openModel('editModel');
		
	}//END edit()
	
	//START save()
	$scope.save = function(){	
		
		var editData = {};
		for(var index in $scope.editCfgSysConBean){
			var tmpKey = $scope.editCfgSysConBean[index].key;
			var tmpValue = $scope.editCfgSysConBean[index].value;
			editData[tmpKey] = tmpValue;
		}
		
		var data = {
				editData : editData,
				oldEditId : $scope.oldEditId
		}
		
		$http({
			method : 'POST',
			url : "editCfgSysCon.action",
			data : data
		}).success(function (data, status) {
			
			if(data && data.result && data.result.data){
				
				$scope.closeModel('editModel');
				
				if(data && data.result && data.result.data){
					
					for(var attr in data.result.data){
						var rsponse = attr;
						var massage = data.result.data[attr];
					}
					
					if(rsponse == 'true'){
						$scope.editResponseStr ="SUCCESS" + massage;
					}else{
						$scope.editResponseStr ="FAIL : " + massage;
					}
					
					$scope.openModel('editResponseModel');
				}
				
				initial();
			}
			
		}).error(function (data, status) {
			console.log(data);
		});
		
	}//END save()
	
	//START remove()
	$scope.remove = function(){
		
		var cfm = confirm("Are you sure you want to DELETE?");
		
		if(cfm == true){
			var removeData = {};
			for(var index in $scope.editCfgSysConBean){
				var tmpKey = $scope.editCfgSysConBean[index].key;
				var tmpValue = $scope.editCfgSysConBean[index].value;
				removeData[tmpKey] = tmpValue;
			}
			
			var data = {
					removeData : removeData
			}
			
			$http({
				method : 'POST',
				url : "removeCfgSysCon.action",
				data : data
			}).success(function (data, status) {
				
				if(data){
					$scope.closeModel('editModel');
					initial();
				}
				
			}).error(function (data, status) {
				console.log(data);
			});
		}else{
			
		}

	}//END remove()
	
	//START copyForAdd()
	$scope.copyForAdd = function(){
		
		$scope.closeModel('editModel');
		
		for(var i in $scope.addCfgSysConBean){
			for(var j in $scope.editCfgSysConBean){
				if($scope.addCfgSysConBean[i].key == $scope.editCfgSysConBean[j].key){
					$scope.addCfgSysConBean[i].value  = $scope.editCfgSysConBean[j].value;
					break;
				}
			}
		}		
		
		$scope.openModel('addModel')	
		
	}//END copyForAdd()
	
	$scope.openModel = function(id) {
		$('#'+id).modal('show');
	}
	
	$scope.closeModel = function(id) {
		$('#'+id).modal('hide');
	}
	
	//Initialize
	initial();
	
	$scope.sortByHeader = function(orderKey){
		
		var data = {
				orderKey : orderKey
		}
		
		$http({
			method : 'POST',
			url : "getCfgSysyeDataBySort.action",
			data : data
		}).success(function (data, status) {
			
			if(data){

			}
			
		}).error(function (data, status) {
			console.log(data);
		});
		
	}
	
});