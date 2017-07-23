var app = angular.module("sysCfgParamApp", []); 

app.controller("sysCfgParamCtrl",function($scope,$http,$location) {
	
	//*Param
	$scope.datas;

	//*Function
	var initial = function(){
		
		console.log('A');
		
		$http({
			method : 'POST',
			url : "getAllSysCfgParam.action"
		}).success(function (data, status) {
			
			console.log('Post OK!!!', data);
			
			if(data && data.length > 0){
				$scope.datas = data;
			}else {
				console.log('No any data');
			}
			
		}).error(function (data, status) {
			console.log('getsysCfgParam error', data);
			
		});
		
	}
	
	
	//*Initialize the load.
	initial();
});