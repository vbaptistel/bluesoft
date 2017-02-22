(function() {
	'use strict';

	angular.module('desafio').service('PedidosService', ProdutoService);

	ProdutoService.$inject = [ '$http' ];

	function ProdutoService($http) {
		var service = {
			checkout : checkout,
			incluir : incluir,
			listaPedidos: listaPedidos
		};

		return service;

		function checkout(pedido) {
			return $http({
				method : 'POST',
				url : '/api/pedido/checkout',
				data : pedido
			});
		}

		function incluir(pedido) {
			return $http({
				method : 'POST',
				url : '/api/pedido',
				data : pedido
			});
		}

		function listaPedidos() {
			return $http({
				method : 'GET',
				url : '/api/pedido',
			});

		}
	}

})();