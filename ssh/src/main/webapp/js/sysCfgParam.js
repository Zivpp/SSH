var app = angular.module("sysCfgParamApp", []); 

app.controller("sysCfgParamCtrl",function($scope,$http,$location) {
	
	//*Parameter
	$scope.tableHeader;
	$scope.tableBody;
	$scope.addCfgSysConBean = [];
	
	//*Function
	var initial = function(){
		
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
	            			key : attr,
	            			value : null,
	            			show : data.result.data.addCfgSysConBean[attr]
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
		
		var passCount = 0;
		
		//note.1 空白提醒
		if($scope.addCfgSysConBean){
			for(var index in $scope.addCfgSysConBean){
				if($scope.addCfgSysConBean[index] &&
						$scope.addCfgSysConBean[index].value &&
						$scope.addCfgSysConBean[index].value != null &&
						$scope.addCfgSysConBean[index].value.length != 0){
					
					var tmpId = $scope.addCfgSysConBean[index].key;
					$("#"+tmpId).removeClass("errorInput");

					passCount = passCount + 1;
				}else{
					var tmpId = $scope.addCfgSysConBean[index].key;
					$("#"+tmpId).addClass("errorInput");
				}
			}
		}
		
		//note.2 型態提醒
		
		//Do it
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
			
			}).error(function (data, status) {
				
			});
		}
	};
	
	
	//*Initialize the load.
	initial();
	
	
	$scope.test = function(item){
		console.log(item);
	}
	
});