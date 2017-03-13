/**
 * jQuery code
 * @type {*}
 */

var about = $('#about');

$.ajax({
    type: 'GET',
    url:  '/version',
    success: function (version) {
        about.append('<li><a href=""> version:' + version +  '</a></li>');
    }

});

var details = $('#customer');
$.ajax({
    type: 'GET',
    url:  'http://localhost:8080/recommendation?name="Brian Sam-Bodden"&balance=1300.00&date="2007-06-21"',
    success: function (customer) {
        details.text('customer:' +  customer);
    }
});


var customerApp = angular.module('customer', []);
customerApp.controller('customerCtrl', function($scope,$http) {
    $http({
        method : "GET",
        url : 'http://localhost:8080/recommendation?name={{name}}&balance={{combinedBalance}}&date={{joinDate}}"',
    }).then(function mySuccess(response) {
        $scope.recommendation = response.data;
    }, function myError(response) {
        $scope.recommendation = "request failed: HTTP " + response;
    });
    $scope.name = "Brian Sam-Bodden";
    $scope.combinedBalance = "1300.00";
    $scope.joinDate = "2007-06-21";
});









