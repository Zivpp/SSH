var app = angular.module("loginApp", []); 


app.controller("loginCtrl", function($scope,$http,$location) {
	
	/**
	 * param
	 */
	$scope.act = null; //account
	$scope.paw = null; //password
	
	var testStringData = {
			name : 'Ziv',
			sex : 'Man'
	}
	
	console.log(JSON.stringify(testStringData));
	console.log(testStringData.constructor.name);
	
	/**
	 * function
	 */
	$scope.login = function(){
		
		
		$.ajax({
		    method: "POST",
		    url: "loginAction.action",
		    data: { 
				d_String : $scope.act,
				d_Int : 1,
				d_Long : 2,
				d_Array : [1,2],
				d_List : [3,4],
				'testStringData.name':'sex',
				'testStringData.sex':'sex'
		    },
		    traditional: true,
		    success: function(data){
		    	var a = data;
		    },
		    error: function(data){
		    	var b = data;
		    }
		});
			
	}
	
	
});