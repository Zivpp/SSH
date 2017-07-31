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
		
		if($scope.addCfgSysConBean){
			for(var attr in $scope.addCfgSysConBean){
				if($scope.addCfgSysConBean[attr] &&
						$scope.addCfgSysConBean[attr].value &&
						$scope.addCfgSysConBean[attr].value != null &&
						$scope.addCfgSysConBean[attr].value.replace(/(^s*)|(s*$)/g, "").length != 0){
					
					var tmpId = $scope.addCfgSysConBean[attr].key;
					$("#"+tmpId).removeClass("errorInput");

					passCount = passCount + 1;
				}else{
					var tmpId = $scope.addCfgSysConBean[attr].key;
					$("#"+tmpId).addClass("errorInput");
				}
			}
		}
		
		if($scope.addCfgSysConBean.length == passCount){
			alert("OK");
		}
	};
	
	
	//*Initialize the load.
	initial();
	
	
	$scope.test = function(item){
		console.log(item);
	}
	
});