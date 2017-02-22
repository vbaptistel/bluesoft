(function() {
  'use strict';

  angular.module('desafio')
    .config(routes);

  routes.$inject = ['$stateProvider', '$urlRouterProvider'];

  function routes($stateProvider, $urlRouterProvider) {
      $stateProvider.state('pedidos', {
          url: '/pedidos',
          templateUrl: 'js/features/pedidos/templates/template.html',
          controller: 'PedidosController as vm'
      });

      return $urlRouterProvider.otherwise('/home');
  }

})();