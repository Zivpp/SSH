var app = angular.module("loginApp", []); 

app.controller("loginCtrl", function($scope,$http,$location) {
	
	/**
	 * param
	 */
	$scope.act = null; //account
	$scope.paw = null; //password
		
	/**
	 * function
	 */
	$scope.login = function(){
		
		var data = { 
			d_String : $scope.act,
			d_Int : 1,
			d_Long : 2,
			d_Array : [1,2],
			d_List : [3,4],
			testStringData : testStringData,
			account : $scope.act,
			password : $scope.paw
	    }

		$http({
			method : 'POST',
			url : "loginAction.action",
			data : data
		}).success(function (data, status, headers, config) {
			console.log('Post OK!!!', data);
			if(data.result.data == 'SUCCESS'){
				document.location.href="pages/hall.html"
			}
		}).error(function (data, status, headers, config) {
			console.log('POST error', data);
		});
			
	}
	
	
});