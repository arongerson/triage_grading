/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('account', ['ui.router']);
app.controller('mainController', function ($scope, $rootScope, $http, $location) {
    $http({
        url: 'get_id.php',
        method: "GET"
    }).then(function (response) {
        var res = response.data;
        $rootScope.sessionId = res.session;
    }, function (response) {
        //
    });
    $scope.logout = function () {
        $http({
            url: 'http://localhost:8080/logout?sessionid=' + $rootScope.sessionId,
            method: "GET",
            headers: {'Content-type': 'application/x-www-form-urlencoded'}
        }).then(function (response) {
        }, function (response) {
            // the feedback returned is not in json format so an exception is thrown thats why it is caught here
            window.location = "index.php";
        });
    };
    $scope.getClass = function (path) {
        return ($location.path().substr(0, path.length) === path) ? 'active' : '';
    };

    $scope.subject = '';
    $scope.details = '';
    $scope.showError = false;

    $scope.validateSubject = function () {
        if ($scope.subject.trim() === '') {
            return false;
        }
        return true;
    };

    $scope.validateDetails = function () {
        if ($scope.details.trim() === '') {
            return false;
        }
        return true;
    };

    $scope.sendSupport = function () {
        if ($scope.validateSupport()) {
            $scope.subject = '';
            $scope.details = '';
            $scope.error = 'request is sent successfully.';
            $scope.showError = true;
            $scope.feedback_color = '#00CC66';
            setTimeout(function () {
                $scope.close();
            }, 3000);
        } else {
            $scope.error = 'fill in all inputs';
            $scope.feedback_color = '#f00';
            $scope.showError = true;
        }
    };

    $scope.validateSupport = function () {
        var valid = $scope.validateSubject() && $scope.validateDetails();
        return valid;
    };

    $scope.close = function () {
        $('#support').css('opacity', 0);
    };
});

app.controller('loginController', function ($scope, $http, $rootScope) {
    //alert('hiiii');
    var init = function () {
        $scope.error_username = '';
        $scope.username = '';
        $scope.error_password = '';
        $scope.password = '';
        $scope.button_disabled_class = 'background: #ccc;';
        $scope.feedback = '';
        $scope.showLoading = false;
    };
    init();
    $scope.showFeedback = function () {
        if ($scope.feedback === '') {
            return false;
        }
        return true;
    };
    $scope.validateUsername = function () {
        if ($scope.username.trim() === '') {
            $scope.error_username = "username is required";
            return false;
        }
        return true;
    };
    $scope.validatePassword = function () {
        if ($scope.password.trim() === '') {
            $scope.error_password = "password is required";
            return false;
        }
        return true;
    };
    $scope.validate = function () {
        var valid = $scope.validateUsername() && $scope.validatePassword();
        if (valid) {
            $scope.button_disabled_class = '';
        } else {
            $scope.button_disabled_class = 'background: #ccc;';
        }
        return !valid;
    };
    $scope.click = function () {
        $scope.showLoading = true;
        $http({
            url: 'http://localhost:8080/login?username=' + $scope.username.trim() + "&password=" + $scope.password.trim(),
            method: "GET",
            headers: {'Content-type': 'application/x-www-form-urlencoded'}
        }).then(function (response) {
            var res = response.data;
            if (res.loginSucceeded) {
                // $rootScope.dynamic = 'partials/account.php';
                window.location = "account.php?user=" + $scope.username + "&session_id=" + res.sessionId;
            } else {
                $scope.feedback = "Incorrect username and/or password";
                $scope.showLoading = false;
            }
        }, function (response) {
            $scope.remote_feedback = 'oooops, the server can not be reached, check your internet connection';
        });
    };
});

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');
    $stateProvider.state('login', {
        url: '/login',
        templateUrl: 'partials/login.php',
        controller: 'loginController'
    }).state("otherwise", {
        url: '/login',
        templateUrl: 'partials/login.php',
        controller: 'loginController'
    });
});

$(document).ready(function () {

    $('.page').click(function () {
        var id = $(this).attr('data-bind');
        $('#' + id).css('opacity', '1');
        $('.modalDialog').css('z-index', -100);
        $('#' + id).css('z-index', 100);
    });

    $('.close').click(function () {
        var id = $(this).attr('data-bind');
        $('#' + id).css('opacity', '0');
        $('.modalDialog').css('z-index', -100);
    });

});