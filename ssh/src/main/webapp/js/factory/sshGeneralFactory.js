/**
 * angularJs factory : 通用方法
 * 
 */
var sshConnectionFactory = angular.module('sshGeneralFactory', []);

sshConnectionFactory.factory('generalFactory',['$http',function($http){
	
	return {
		clone : function(obj){
			
			if (null == obj || "object" != typeof obj) return obj;
			
			var copy = obj.constructor();
			
			for (var attr in obj) {
			    if (obj.hasOwnProperty(attr)) copy[attr] = obj[attr];
			}
			
			return copy;
		}
		
	}
	
}]);