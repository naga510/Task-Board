taskBoardServices.factory('UserService',
['$http',
function($http) {
    return {
        currentUser: function() {
            return $http.get('http://localhost:9080/BoardWeb/user/',
			{headers:{'auth-token': '87ed70cf-f234-47f6-8793-2793ab1ccb40'}});
        },

        saveOptions: function(tasksOrder, showAnimations, showAssignee) {
            return $http.post('api/users/current/options', {
                tasksOrder: tasksOrder,
                showAnimations: showAnimations,
                showAssignee: showAssignee
            });
        },

        logIn: function(username, password) {
            return $http.post('http://localhost:9080/BoardWeb/user/login', {
                userName: username,
                password: password
            });
        },
		
		signUp: function(name, email, password) {
            return $http.post('http://localhost:9080/BoardWeb/user/', {
                name: name,
				email: email,
                password: password
            });
        },

        logOut: function() {
            return $http.get('http://localhost:9080/BoardWeb/user/logout/',
			{headers:{'auth-token': '87ed70cf-f234-47f6-8793-2793ab1ccb40'}});
        },

        validateToken: function(token) {
            return $http.get('http://localhost:9080/BoardWeb/user/validate/',
			{headers:{'auth-token': token}});
        },

        changePassword: function(currentPassword, newPassword) {
            return $http.post('api/updatepassword', {
                currentPass: currentPassword,
                newPass: newPassword
            });
        },

        changeUsername: function(newUsername) {
            return $http.post('api/updateusername', {
                newUsername: newUsername
            });
        },

        changeEmail: function(newEmail) {
            return $http.post('api/updateemail', {
                newEmail: newEmail
            });
        },

        changeDefaultBoard: function(newDefaultBoard) {
            return $http.post('api/updateboard', {
                defaultBoard: newDefaultBoard
            });
        },

        actions: function() {
            return $http.get('api/actions');
        },

        getUsers: function() {
            return $http.get('api/users');
        },

        addUser: function(formData) {
            return $http.post('api/users', {
                username: formData.username,
                password: formData.password,
                email: formData.email,
                defaultBoard: formData.defaultBoard,
                boardAccess: formData.boardAccess,
                isAdmin: formData.isAdmin
            });
        },

        editUser: function(formData) {
            return $http.post('api/users/update', {
                userId: formData.userId,
                newUsername: formData.username,
                password: formData.password,
                email: formData.email,
                defaultBoard: formData.defaultBoard,
                boardAccess: formData.boardAccess,
                isAdmin: formData.isAdmin
            });
        },

        removeUser: function(userId) {
            return $http.post('api/users/remove', {
                userId: userId
            });
        }
    };
}]);
