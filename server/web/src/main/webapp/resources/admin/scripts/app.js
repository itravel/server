var adminModule = angular.module('admin',[]); 
adminModule.factory('formDataObject', function() {
	  return function(data) {
	    var fd = new FormData();
	    angular.forEach(data, function(value, key) {
	      fd.append(key, value);
	    });
	    return fd;
	  };
	});

adminModule.controller('ActivitiesCtrl',function($scope,$http){
	$http.get('../services/activities').success(function(data) {
	    $scope.activities = data;
	});
	$scope.activity = {};
	$scope.save = function(activity){
		console.log(activity)
		if(activity.id && activity.id>0){
			$http({
				method:'PUT',
				url:'../services/activities/'+activity.id,
				data:$.param(activity),
				headers: {'Content-Type': 'application/x-www-form-urlencoded'}
					
			}).success(function(){
				$('#myModal').modal('hide');
				console.log($scope.myModal);
			});
		}
		else {
			$http({
				method:'POST',
				url:'../services/activities',
				data:$.param(activity),
				headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			
			});
			
		}
	}; 
	
	$scope.go = function(activity){
		$scope.activity = activity;
		$('#myModal').modal();
	}
	
	$scope.submit = function(){
		console.debug("11111");
	}
	
	$("#fileupload_input").fileupload({
	    url:"../services/images/activities",//文件上传地址
	    done:function(e,result){
	    	JSON.stringify(result.result)
	    	$scope.activity.image=result.result.imageNames;
	    	console.log($scope.activity.image);
	    	
	    }
	});
});