/**
 * 自訂義 angularJs factory
 * 
 */
var sshConnectionFactory = angular.module('sshConnectionFactory', []);

sshConnectionFactory.factory('httpFactory',['$http',function($http){
	
	return {
		post : function(url,data,success,error){			
			
			if(!data){
				data = {};
			}
			
			$http({
				method : 'POST',
				url : url,
				data : data
			}).success(function (data, status) {			
				
				//data
				if(data && data.result && data.result.data && !data.result.exception){
					success(data.result.data);
				}else{
					error(data.result.exception);
				}		
				
			}).error(function (data, status) {
				console.log("unknow error " + status +" : " + data);
			});
			
			
			
			
		}
    }
	
}]);