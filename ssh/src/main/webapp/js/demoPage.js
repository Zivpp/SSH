var app = angular.module("demoPageApp", ['sshConnectionFactory','sshGeneralFactory']); 

app.controller("demoPageCtrl",['$scope','$http', '$location','httpFactory','generalFactory', 
	function($scope,$http,$location,httpFactory,generalFactory) {
	
	//*Parameter
	$scope.imageNo;
	
	//*Function
	//S-initial()
	var initial = function(){
		
		$scope.imageNo = 1;
		
	    $( "#s" ).slider({
		      range: "min",
		      value: 1,
		      min: 1,
		      max: 10,
		      slide: function( event, ui ) {
		        $scope.imageNo = ui.value;
		        $scope.$apply();
		      }
	    });
		
	}//E-initial()
	
	
	//*initialize
	initial();
	
}]);

