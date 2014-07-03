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
});