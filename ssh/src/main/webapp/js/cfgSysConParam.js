var app = angular.module("sysCfgParamApp", ['sshConnectionFactory','sshGeneralFactory']); 

app.controller("sysCfgParamCtrl",['$scope','$http','$location','httpFactory','generalFactory',function($scope,$http,$location,httpFactory,generalFactory) {
	
	//*Parameter
	$scope.tableHeader;
	$scope.tableBody;
	$scope.addCfgSysConBean;
	$scope.editCfgSysConBean;
	$scope.editResponseStr;
	$scope.oldEditId;
	$scope.isBatchDeleteModel;
	$scope.isAllChecked;
	$scope.recordsPerPage;
	$scope.dataShowRangeMenu;
	$scope.pagBtnCountMenu;
	$scope.nowPagCount;
	$scope.nowRPP;
	
	//*Function
	//S-initial()
	var initial = function(){
		
		$scope.tableHeader = [];
		$scope.tableBody = [];
		$scope.addCfgSysConBean = [];
		$scope.editCfgSysConBean = [];
		$scope.oldEditId = null;
		$scope.isBatchDeleteModel = false;
		$scope.isAllChecked = false;
		$scope.recordsPerPage = []; //for ng-repeat
		$scope.dataShowRangeMenu = [];
		$scope.pagBtnCountMenu = [];
		$scope.nowPagCount = []; //for ng-repeat
		$scope.nowRPP = null;
		
		//datas
		httpFactory.post(
			"getSCPInitialData.action", //url
			{data : {}}, //data
			function(data){ //success
				
				//tableHeader
				for(var i in data.tableHeader){
					$scope.tableHeader.push({
						header : data.tableHeader[i],
						sort : true
					});
				}
				
				//tableBody
				//新增 index, isShow for pagination
				for(var i in data.tableBody){
					$scope.tableBody.push({
						data : data.tableBody[i],
						isShow : false,
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
				
				//recordsPerPage && dataShowRangeMenu && pagBtnCountMenu
				$scope.recordsPerPage = data.recordsPerPage;
				$scope.dataShowRangeMenu = data.dataShowRangeMenu;
				$scope.pagBtnCountMenu = data.pagBtnCountMenu;
				initPagBtnCount(data.rppDefault);
				
			},function(data){ //error
				confirm('get cfg_System_Config data error : ' + data);
				console.log('get cfg_System_Config data error : ' + data);
			}
		);
	}//E-initial()
	
	//S-initPagBtnCount()
	var initPagBtnCount = function(rppDefault) {
		$scope.nowRPP = rppDefault;
		for(var attr in $scope.pagBtnCountMenu){
			if($scope.nowRPP == attr && attr != 'All'){
				$scope.nowPagCount = new Array($scope.pagBtnCountMenu[attr]);
				break;
			}
			if($scope.nowRPP == 'All'){
				$scope.nowPagCount = new Array($scope.pagBtnCountMenu['All']);
				break;
			}
		}
		chagneDataShowRange(1);
	}//E-initPagBtnCount()
	
	//S-reSetPagCount()
	window.reSetPagCount = function() {
		
		if($('#rppSelect').val()){
			$scope.nowRPP = $('#rppSelect').val();
		}
		
		for(var attr in $scope.pagBtnCountMenu){
			if($scope.nowRPP == attr && attr != 'All'){
				$scope.nowPagCount = new Array($scope.pagBtnCountMenu[attr]);
				break;
			}
		}
		
		if($scope.nowRPP == attr && attr == 'All'){
			$scope.nowPagCount = new Array(1);
		}
		
		chagneDataShowRange(1);
		$scope.$apply(); //**刷新 $scope, ng-repeat 為靜態
		
	}//E-reSetPagCount()
	
	//S-goPaination
	$scope.goPagination = function(pIndex) {

		//active : 標註藍色好認
		for(var i =1;i <=  $scope.nowPagCount.length;i++){
			$("#pIndex"+i).removeClass("active");
		}
		$("#pIndex"+pIndex).addClass("active");
		
		chagneDataShowRange(pIndex);
		
	}//E-goPaination
	
	//S-chagneDataShowRange()
	var chagneDataShowRange = function(pIndex) {
		
		var tmpRpp = $scope.nowRPP;
		var start = null;
		var end = null;
		
		for(var attr in $scope.dataShowRangeMenu){
			if(tmpRpp == attr){
				var menu = $scope.dataShowRangeMenu[attr];
				for(var i in menu){
					if(pIndex == menu[i].pIndex){
						start = menu[i].start;
						end = menu[i].end;
						break;
					}
				}
			}
		}

		if(start != null && end != null){
			//all re set
			for(var j in $scope.tableBody){
				if($scope.tableBody[j].isShow = true){
					$scope.tableBody[j].isShow = false;
				}
			}
			//new data show
			for(var i=start;i<=end;i++){
				if($scope.tableBody[i]){
					$scope.tableBody[i].isShow = true;
				}
			}
		}	
	}//E-chagneDataShowRange()
	
	//S-add()
	$scope.add = function() {
		
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
	};//E-add()
	
	//S-edit()
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
		
	}//E-edit()
	
	//S-save()
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
		
	}//E-save()
	
	//S-remove()
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

	}//E-remove()
	
	//S-copyForAdd()
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
		
	}//E-copyForAdd()
	
	//S-batchDeleteModelSwitch()
	$scope.batchDeleteModelSwitch = function() {
		
		$scope.isBatchDeleteModel = !$scope.isBatchDeleteModel;
		//all checked = false
		for(var i in $scope.tableBody){
			$scope.tableBody[i].isChecked = false;
		}
	}//E-batchDeleteModelSwitch()
	
	//S-batchDelete()
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
					initial();
				},function(data){ //error
					confirm('delete cfg_System_Config data(batch) error : ' + data);
				}
			);
		}
	}//E-batchDelete()	
	
	//S-initAdd()
	$scope.initAdd = function() {
		for(var i in $scope.addCfgSysConBean){
			$scope.addCfgSysConBean[i].value = null;
		}
	}//E-initAdd()
	
	//S-sortByHeader()
	$scope.sortByHeader = function(data){
		
		var sortIdList = [];
		var backupData = [];
		
		data.sort = !data.sort;
		
		for(var i in $scope.tableBody){
			if($scope.tableBody[i].isShow == true){
				var tmpId = generalFactory.clone($scope.tableBody[i].data[0]);
				var tmpData = generalFactory.clone($scope.tableBody[i].data);
				sortIdList.push(tmpId);
				backupData.push({
					id : tmpId,
					data : tmpData,
					index : i
				});
			}
		}
		
		httpFactory.post(
				"cfgSysConSortByHeader.action", {//url
					data : {
						header : data.header,
						sortBy : data.sort,
						sortIdList : sortIdList
					} 
				},function(data){ //success
					
					var sortResultData = [];
					//先排出新的資料排序
					for(var j in data){
						for(var i in backupData){
							if(data[j] == backupData[i].id){
								sortResultData.push($scope.tableBody[backupData[i].index].data);
								break;
							}
						}
					}
					//直接將原來資料全部覆蓋
					for(var i in backupData){
						$scope.tableBody[backupData[i].index].data = generalFactory.clone(sortResultData[i]);
					}
					
				},function(data){ //error
					confirm('sort by {' + data.header + '} error :' + data);
				}
			);
	}//E-sortByHeader()
	
	//*Model
	$scope.openModel = function(id) {
		$('#'+id).modal('show');
	}
	
	$scope.closeModel = function(id) {
		$('#'+id).modal('hide');
	}
	
	//*Watch
	$scope.$watch('isAllChecked', function(newValue, oldValue) {
		 if(newValue){
			for(var i in $scope.tableBody){
				if($scope.tableBody[i].isShow == true){
					$scope.tableBody[i].isChecked = true;
				}
			} 
		 }else{
			for(var i in $scope.tableBody){
				if($scope.tableBody[i].isShow == true){
					$scope.tableBody[i].isChecked = false;
				}
			} 
		 }	    
	},true);	
	
	//*Do Initialize
	initial();
	
	//---
	

}]);