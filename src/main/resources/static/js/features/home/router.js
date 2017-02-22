(function() {
  'use strict';

  angular.module('desafio')
    .config(routes);

  routes.$inject = ['$stateProvider', '$urlRouterProvider'];

  function routes($stateProvider, $urlRouterProvider) {
      $stateProvider.state('home', {
          url: '/home',
          templateUrl: 'js/features/home/templates/template.html',
          controller: 'HomeController as vm'
      });

      return $urlRouterProvider.otherwise('/home');
  }

})();