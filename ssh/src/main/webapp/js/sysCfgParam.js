var app = angular.module("sysCfgParamApp", []); 

app.controller("sysCfgParamCtrl",function($scope,$http,$location) {
	
	//*Parameter
	$scope.tableHeader;
	$scope.tableBody;
	$scope.addCfgSysConBean;
	
	//*Function
	var initial = function(){
		
		$scope.tableHeader = [];
		$scope.tableBody = [];
		$scope.addCfgSysConBean = [];
		
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
	$scope.save = function(){
		
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
				
				initial();
				var modal = document.getElementById('addCfgSysModal');
				angular.element(modal).modal('hide');
				
			}).error(function (data, status) {
				
			});
		}
	};
	
	
	//*Initialize the load.
	initial();
	
	
	$scope.test = function(item){
		var ttt = item;
		console.log(item);
		ttt[0] = 'yyyyyyyyyyy';
	}
	
});