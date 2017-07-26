var app = angular.module("sysCfgParamApp", []); 

app.controller("sysCfgParamCtrl",function($scope,$http,$location) {
	
	//*Param
	$scope.data; //intial data
	
	$scope.tableHead;

	//*Function
	var initial = function(){
		
		//datas
		$http({
			method : 'POST',
			url : "getSCPInitialData.action"
		}).success(function (data, status) {
			
			console.log('Post OK!!!', data);
			
			if(data && 
					data.result && 
					data.result.data &&
					data.result.data.length > 0){
				
				$scope.data = data.result.data;
				
			}else {
				console.log('No any data');
			}
			
		}).error(function (data, status) {
			console.log('getSCPInitialData Error', data);
		});
		
		//tableHead
		$scope.tableHead = ["ID", "CODECATE", "CATENAME", "CODE", "CODENAME", "CODEVALUE", "CODEDESC","UPDATEDATE", "UPDATEUSER"];
		
	}
	
	
	//*Initialize the load.
	initial();
	
	
});