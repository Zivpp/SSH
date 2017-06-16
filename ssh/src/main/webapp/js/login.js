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
		})
		.success(function (data, status, headers, config) {
			console.log('Post OK!!!', data);
			if(data.result.datas == 'SUCCESS'){
				document.location.href="pages/hall.html"
			}
		})
		.error(function (data, status, headers, config) {
			console.log('POST error', data);
		});
		
		
		//Jqurey ajax sample : 無法直接使用  object 轉換
//		$.ajax({
//		    method: "POST",
//		    url: "loginAction.action",
//		    data: { 
//				d_String : $scope.act,
//				d_Int : 1,
//				d_Long : 2,
//				d_Array : [1,2],
//				d_List : [3,4],
//				'testStringData.name':'sex',
//				'testStringData.sex':'sex'
//		    },
//		    traditional: true,
//		    success: function(data){
//		    	var a = data;
//		    },
//		    error: function(data){
//		    	var b = data;
//		    }
//		});
			
	}
	
	
});