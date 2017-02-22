(function() {
	'use strict';

	angular.module('desafio')
			.controller('PedidosController', PedidosController);

	PedidosController.$inject = [ 'PedidosService' ];

	function PedidosController(PedidosService) {
		var vm = this;

		vm.pedidos = [];

		vm.listarPedidos = function listarPedidos() {
			PedidosService.listaPedidos().then(function(res) {
				vm.pedidos = res.data;
				console.log(vm.pedidos);
			});
		};

		function init() {
			vm.listarPedidos();
		}

		init();

	}

})();
