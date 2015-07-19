var taskBoardServices = angular.module('TaskBoardServices', []);
var taskBoardControllers = angular.module('TaskBoardControllers', []);
var taskBoardDirectives = angular.module('TaskBoardDirectives', []);
var taskBoard = angular.module('TaskBoard',
                               ['ngRoute', 'ngSanitize',
                                'ng-context-menu',
                                'TaskBoardServices',
                                'TaskBoardControllers',
								'TaskBoardDirectives']);

taskBoard.config(['$routeProvider', '$httpProvider',
function($routeProvider, $httpProvider) {
    $routeProvider.when('/', {
        controller: 'LoginCtrl',
        templateUrl: 'partials/login.html'
    }).when('/signup', {
        controller: 'LoginCtrl',
        templateUrl: 'partials/signup.html',
        authRequired: true
    }).when('/boards', {
        controller: 'BoardCtrl',
        templateUrl: 'partials/boardSelect.html',
        authRequired: true
    }).when('/boards/:boardId', {
        controller: 'BoardCtrl',
        templateUrl: 'partials/board.html',
        authRequired: true,
        resolve: {
            validation: ['$q', '$route', function($q, $route) {
                var deferred = $q.defer(),
                    id = parseInt($route.current.params.boardId);
                if (isNaN(id)) {
                    deferred.reject('INVALID BOARD ID');
                } else {
                    deferred.resolve();
                }
                return deferred.promise;
            }]
        }
    }).when('/settings', {
        controller: 'SettingsCtrl',
        templateUrl: 'partials/settings.html',
        authRequired: true
    }).when('/files/:fileId', {
        controller: 'FilesCtrl',
        templateUrl: 'partials/files.html',
        authRequired: true
    }).otherwise({
        redirectTo: '/'
    });

    // Inject the auth token with each API call.
}]);
