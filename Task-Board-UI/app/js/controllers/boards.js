taskBoardControllers.controller('BoardCtrl',
['$scope', '$routeParams', '$location', '$interval', '$window',
 'UserService', 'BoardService', 'AuthenticationService',
function ($scope, $routeParams, $location, $interval, $window,
          UserService, BoardService, AuthenticationService) {
    // This is here because the BoardCtrl is the default redirect from login.
    // If the user was trying to go somewhere else first, they are redirected now.
    if (AuthenticationService.attemptedRoute) {
        var tmp = AuthenticationService.attemptedRoute,
            path = tmp.originalPath;

        tmp.keys.forEach(function(key) {
            path = path.replace(':' + key.name, tmp.params[key.name]);
        });
        AuthenticationService.attemptedRoute = null;
        $location.path(path);
    }

    $scope.quickAdd = {
        title: []
    };

    $scope.marked = function(text) {
        if (text) {
            return $window.marked(text);
        } else {
            return '';
        }
    };
	
    $scope.boards = [];
    $scope.boardsLoaded = false;
    $scope.boardNames = [];
    $scope.userNames = [];
	
    $scope.loadBoards = function() {
            
            BoardService.getBoards()
            .success(function(data) {
                $scope.boards =data.boards; 
                $scope.boardsLoaded = true;	
				$scope.boards.forEach(function(board) {
					if(board.status=='ACTIVE') {
						$scope.boardNames.push({id: board.boardId, name: board.boardName});
					}
				});
				
            });
        };
    $scope.loadBoards();
    $scope.currentUser = {};
    $scope.userLoaded = false;
    $scope.updateCurrentUser = function() {
        UserService.currentUser()
        .success(function(data) {
            $scope.userLoaded = true;
            $scope.currentUser = data;			
        });
    };
    $scope.updateCurrentUser();
}]);
