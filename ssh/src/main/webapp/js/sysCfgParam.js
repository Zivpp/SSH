var app = angular.module("sysCfgParamApp", []); 

app.controller("sysCfgParamCtrl",function($scope,$http,$location) {
	
	//*Parameter
	$scope.tableHeader;
	$scope.tableBody;

	//*Function
	var initial = function(){
		
		//datas
		$http({
			method : 'POST',
			url : "getSCPInitialData.action"
		}).success(function (data, status) {
			
			console.log('Post OK!!!', data);
			
			if(data && data.result && data.result.data){
				
				$scope.tableHeader = data.result.data.TableHeader;
				$scope.tableBody = data.result.data.TableBody
				
			}else {
				console.log('No any Cfg_System_Config data');
			}
			
		}).error(function (data, status) {
			console.log('get Cfg_System_Config initial data rrror', data);
		});
		
	}
	
	
	//*Initialize the load.
	initial();
	
	
	$scope.test = function(item){
		console.log(item);
	}
	
});