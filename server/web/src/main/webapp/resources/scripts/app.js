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
		console.debug("---");
		$http({
			method:'POST',
			url:'../services/activities',
			data:$.param(activity),
			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				
		});
	}; 
});