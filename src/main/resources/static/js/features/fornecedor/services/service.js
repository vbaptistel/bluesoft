(function() {
  'use strict';

  angular.module('desafio')
    .service('FornecedorService', FornecedorService);

  FornecedorService.$inject = ['$http'];

  function FornecedorService($http) {
      var service = {
        listar: listar
      };

      return service;

      function listar() {
        return $http({
          method: 'GET',
          url: '/api/fornecedores'
        });
      }
  }

})();