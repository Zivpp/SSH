/**
 * 自訂義 angularJs factory
 * 
 */
var sshConnectionFactory = angular.module('sshConnectionFactory', []);

sshConnectionFactory.factory('httpFactory',['$http',function($http){
	
	return {
		post : function(url,reqData,success,error){			
			
			var data = null;
			
			if(reqData){
				data = reqData.data;
			}
			
			$http({
				method : 'POST',
				url : url,
				data : data
			}).success(function (data, status) {			
				
				//data
				if(data && data.result && !data.result.exception){
					if(data.result.data){ //sometime no data is return
						success(data.result.data);
					}else{
						success(data.result);
					}	
				}else{
					error(data.result.exception);
				}						
			}).error(function (data, status) {
				console.log("unknow error " + status +" : " + data);
			});
			
			
			
			
		}
    }
	
}]);