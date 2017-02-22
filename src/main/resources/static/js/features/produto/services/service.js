(function() {
  'use strict';

  angular.module('desafio')
    .service('ProdutoService', ProdutoService);

  ProdutoService.$inject = ['$http'];

  function ProdutoService($http) {
      var service = {
        listar: listar
      };

      return service;

      function listar(fornecedor) {
        return $http({
          method: 'GET',
          url: '/api/fornecedores/' + fornecedor.cnpj + '/produtos' 
        });
      }
  }

})();