var app = angular.module("sysCfgParamApp", ['sshConnectionFactory']); 

app.controller("sysCfgParamCtrl",['$scope','$http','$location','httpFactory',function($scope,$http,$location,httpFactory) {
	
	//*Parameter
	$scope.tableHeader;
	$scope.tableBody;
	$scope.addCfgSysConBean;
	$scope.editCfgSysConBean;
	$scope.editResponseStr;
	$scope.oldEditId;
	$scope.isBatchDeleteModel;
	$scope.isAllChecked;
	
	//*Function
	var initial = function(){
		
		$scope.tableHeader = [];
		$scope.tableBody = [];
		$scope.addCfgSysConBean = [];
		$scope.editCfgSysConBean = [];
		$scope.oldEditId = null;
		$scope.isBatchDeleteModel = false;
		$scope.isAllChecked = false;
		
		//datas
		httpFactory.post(
			"getSCPInitialData.action", //url
			{data : {}}, //data
			function(data){ //success
				
				//tableHeader
				$scope.tableHeader = data.tableHeader;
				
				//tableBody
				//新增 index, isShow for pagination
				for(var i in data.tableBody){
					$scope.tableBody .push({
						data : data.tableBody[i],
						index : i,
						isShow : true,
						isChecked : false
					});
				}
				
				//addCfgSysConBean && editCfgSysConBean
				if(data.addCfgSysConBean){
					for(var attr in data.addCfgSysConBean){
						
	            		$scope.addCfgSysConBean.push({
	            			key : data.addCfgSysConBean[attr].name,
	            			value : null,
	            			type : data.addCfgSysConBean[attr].dataType
	            		});
	            		
	            		$scope.editCfgSysConBean.push({
	            			key : data.addCfgSysConBean[attr].name,
	            			value : null,
	            		});
	        		};
				}
			},function(data){ //error
				confirm('get cfg_System_Config data error : ' + data);
				console.log('get cfg_System_Config data error : ' + data);
			}
		);
	}
	
	//*function
	//Start add()
	$scope.add = function(){
		
		var passCount = 0; //($scope.addCfgSysConBean must be all pass
		
		if($scope.addCfgSysConBean){
			for(var index in $scope.addCfgSysConBean){
				
				var error = false;
				var name = $scope.addCfgSysConBean[index].key;
				var type = $scope.addCfgSysConBean[index].type; //type = number or string
				var value = $scope.addCfgSysConBean[index].value;
				
				//1. 空白檢查
				if($scope.addCfgSysConBean[index] &&
						$scope.addCfgSysConBean[index].value &&
						$scope.addCfgSysConBean[index].value != null &&
						$scope.addCfgSysConBean[index].value.length != 0){					
					error = false;	
				}else{
					error = true;					
				}
				
				//2. 型態提醒
				if(type == 'number' && isNaN(value)){
					error = true;
					if(value == null){
						$scope.addCfgSysConBean[index].value = " *請填數字";
					}else{
						$scope.addCfgSysConBean[index].value = $scope.addCfgSysConBean[index].value + " *請填數字";
					}
						
				}
				
				//no input note
				if(error){
					var tmpId = $scope.addCfgSysConBean[index].key;
					$("#"+name).addClass("errorInput");
				}else{
					$("#"+name).removeClass("errorInput");
					passCount = passCount + 1;
				}
			}
		}		
		
		//Pass
		if($scope.addCfgSysConBean.length == passCount){
			
			var addData = {};
			for(var index in $scope.addCfgSysConBean){
				var tmpKey = $scope.addCfgSysConBean[index].key;
				var tmpValue = $scope.addCfgSysConBean[index].value;
				addData[tmpKey] = tmpValue;
			}
			
			httpFactory.post(
					"addCfgSystemConfig.action", {//url
						data : {
							addData : addData
						} 
					},function(data){ //success	
						$scope.closeModel('addModel');
						initial();
					},function(data){ //error
						confirm('add cfg_System_Config data error : ' + data);
					}
			);					

		}
	};//END add()
	
	//Start edit()
	$scope.edit = function(item){
		
		if(!$scope.isBatchDeleteModel){
			$scope.oldEditId = item[0];
			
			var data = {
					cfgSysId : item[0]
				};	
			
			httpFactory.post(
					"searchCfgSysConById.action", {//url
						data : {
							cfgSysId : item[0]
						} 
					},function(data){ //success	
						
						var tmpValueData = data;
						for(var i in $scope.editCfgSysConBean){
							var tmpKey = $scope.editCfgSysConBean[i].key;
							for(var key in tmpValueData){
								if(tmpKey == key){
									$scope.editCfgSysConBean[i].value = tmpValueData[key];
								}
							}
						}
						
					},function(data){ //error
						confirm('search cfg_System_Config data error : ' + data);
					}
			);	
			//Open model
			$scope.openModel('editModel');
		}
		
	}//END edit()
	
	//START save()
	$scope.save = function(){	
		
		var editData = {};
		for(var index in $scope.editCfgSysConBean){
			var tmpKey = $scope.editCfgSysConBean[index].key;
			var tmpValue = $scope.editCfgSysConBean[index].value;
			editData[tmpKey] = tmpValue;
		}
		
		var data = {
				editData : editData,
				oldEditId : $scope.oldEditId
		}	
		
		httpFactory.post(
				"editCfgSysCon.action", {//url
					data : {
						editData : editData,
						oldEditId : $scope.oldEditId
					} 
				},function(data){ //success	
					
					if(data){
						
						$scope.closeModel('editModel');

						for(var attr in data){
							var rsponse = attr;
							var massage = data[attr];
						}
						
						if(rsponse == 'true'){
							$scope.editResponseStr ="SUCCESS" + massage;
						}else{
							$scope.editResponseStr ="FAIL : " + massage;
						}
						
						$scope.openModel('editResponseModel');
						
						initial();
					}				
					
				},function(data){ //error
					confirm('save cfg_System_Config data error : ' + data);
				}
		);			
		
	}//END save()
	
	//START remove()
	$scope.remove = function(){
		
		var cfm = confirm("Are you sure you want to DELETE?");
		
		if(cfm == true){
			var removeData = {};
			for(var index in $scope.editCfgSysConBean){
				var tmpKey = $scope.editCfgSysConBean[index].key;
				var tmpValue = $scope.editCfgSysConBean[index].value;
				removeData[tmpKey] = tmpValue;
			}
			
			httpFactory.post(
					"removeCfgSysCon.action", {//url
						data : {
							removeData : removeData
						} 
					},function(data){ //success					
						if(data){
							$scope.closeModel('editModel');
							initial();
						}
					},function(data){ //error
						confirm('delete cfg_System_Config data error : ' + data);
					}
			);
			
		}else{
			//not do something
		}

	}//END remove()
	
	//START copyForAdd()
	$scope.copyForAdd = function(){
		
		$scope.closeModel('editModel');
		
		for(var i in $scope.addCfgSysConBean){
			for(var j in $scope.editCfgSysConBean){
				if($scope.addCfgSysConBean[i].key == $scope.editCfgSysConBean[j].key){
					$scope.addCfgSysConBean[i].value  = $scope.editCfgSysConBean[j].value;
					break;
				}
			}
		}		
		
		$scope.openModel('addModel')	
		
	}//END copyForAdd()
	
	$scope.openModel = function(id) {
		$('#'+id).modal('show');
	}
	
	$scope.closeModel = function(id) {
		$('#'+id).modal('hide');
	}
	
	//Initialize
	initial();
	
	$scope.sortByHeader = function(orderKey){
		
		var data = {
				orderKey : orderKey
		}
		
		$http({
			method : 'POST',
			url : "getCfgSysyeDataBySort.action",
			data : data
		}).success(function (data, status) {
			
			if(data){

			}
			
		}).error(function (data, status) {
			console.log(data);
		});
		
	}
	//---
	$scope.batchDeleteModelSwitch = function() {
		
		$scope.isBatchDeleteModel = !$scope.isBatchDeleteModel;
		//all checked = false
		for(var i in $scope.tableBody){
			$scope.tableBody[i].isChecked = false;
		}
	}
	
	$scope.batchDelete = function() {
		
		if(confirm("Are you sure you want to DELETE(Batch)")){
			
			var deleteCfgSysIdList = [];
			
			for(var i in $scope.tableBody){
				if($scope.tableBody[i].isChecked){
					deleteCfgSysIdList.push($scope.tableBody[i].data[0]);
				}
			}
			
			httpFactory.post(
				"removeCfgSysByBatch.action", {//url
					data : {
						deleteCfgSysIdList : deleteCfgSysIdList
					} 
				},function(data){ //success					
					$scope.isBatchDeleteModel = false;
				},function(data){ //error
					confirm('delete cfg_System_Config data(batch) error : ' + data);
				}
			);
			
		}

	}	
	
	$scope.$watch('isAllChecked', function(newValue, oldValue) {
		 if(newValue){
			for(var i in $scope.tableBody){
				if($scope.tableBody[i].isShow = true){
					$scope.tableBody[i].isChecked = true;
				}
			} 
		 }else{
			for(var i in $scope.tableBody){
				if($scope.tableBody[i].isShow = true){
					$scope.tableBody[i].isChecked = false;
				}
			} 
		 }	    
	},true);

}]);