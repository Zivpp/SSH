var app = angular.module("loginApp", []); 


app.controller("loginCtrl", function($scope,$http,$location) {
	
	/**
	 * param
	 */
	
	
	/**
	 * function
	 */
	$scope.login = function(){
			
		//var json = angular.toJson(data);	
		
		var array = [1, 2, 3, 4, 5];
		$.ajax({
		    method: "POST",
		    url: "loginAction.action",
		    data: { array : array },
		    traditional: true,
		    success: function(data){
		          var a = data;
		        }
		})
			
	}
	
	
});