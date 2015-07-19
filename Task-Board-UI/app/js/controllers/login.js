taskBoardControllers.controller('LoginCtrl',
['$scope', '$location', '$window', 'UserService', 'AuthenticationService' ,
function ($scope, $location, $window, UserService, AuthenticationService) {
    $scope.formdata = {
        username: '',
        password: ''
    };
	$scope.signupdata = {
        name: '',
		email: '',
        password: ''
    };
    $scope.isSaving = false;

    // Uses jQuery to handle clearing of any open modals.
    $scope.clear = function() {
        $('[name~=Modal]').modal('hide');
        $('.modal-backdrop').hide();
    };

    $scope.logIn = function (formdata) {
        $scope.errors = [];
        $scope.isSaving = true;

        UserService.logIn(formdata.username, formdata.password)
        .success(function(data) {            
            AuthenticationService.isAuthenticated = true;
			$window.localStorage.token = data.token;
			$location.path('/boards');
        }).error(function(data, status) {
            $scope.isSaving = false;
            $scope.errors.push(data.message);
            if (status === 409) {
                $scope.errors[0] = $scope.errors[0] + ' Ensure api directory is writable.';
            }
        });
    };
	
	$scope.signup = function (signupdata) {
        $scope.errors = [];
        UserService.signUp(signupdata.name, signupdata.email, signupdata.password)
        .success(function(data) {            
            AuthenticationService.isAuthenticated = true;
			$location.path('/login');
        }).error(function(data, status) {
            $scope.errors.push(data.message);
            if (status === 409) {
                $scope.errors[0] = $scope.errors[0] + ' Ensure api directory is writable.';
            }
        });
    };
}
]);
