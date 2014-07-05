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
	$scope.activity.images = [];
	$scope.save = function(activity){
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
	console.log("------");
	$("#fileupload_input").fileupload({
	    url:"../services/imageUpload/activities",//文件上传地址
	    done:function(e,result){
	        //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
	        //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
	        //返回的数据在result.result中，假设我们服务器返回了一个json对象
	        console.log(JSON.stringify(result.result));
	    }
	})
});