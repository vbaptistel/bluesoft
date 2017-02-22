(function() {
  'use strict';

  angular.module('desafio')
    .service('CompradorService', CompradorService);

  CompradorService.$inject = ['$http'];

  function CompradorService($http) {
      var service = {
        listar: listar
      };

      return service;

      function listar() {
        return $http({
          method: 'GET',
          url: '/api/compradores'
        });
      }
  }

})();