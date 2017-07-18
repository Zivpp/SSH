var app = angular.module("sysCfgParamApp", []); 

app.controller("sysCfgParamCtrl",['$scope','$http','$location','eventBusService',function($scope,$http,$location,eventBusService) {
	
	//*Param
	$scope.datas;

	//*Function
	var initial = function(){
		
		$http({
			method : 'POST',
			url : "getsysCfgParam.action"
		}).success(function (data, status) {
			
			console.log('Post OK!!!', data);
			
			if(data && data.length !=0){
				$scope.datas = data;
			}
			
		}).error(function (data, status) {
			eventBusService.publish("ERROR", "getsysCfgParam", {body : block});
			console.log('getsysCfgParam error', data);
			
		});
		
	}
	
	
	//*Initialize the load.
	initial();
}]);