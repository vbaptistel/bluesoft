(function() {
	'use strict';

	angular.module('desafio').controller('HomeController', HomeController);

	HomeController.$inject = [ 'FornecedorService', 'ProdutoService',
			'CompradorService', 'PedidosService' ];

	function HomeController(FornecedorService, ProdutoService, CompradorService, PedidosService) {
		var vm = this;
		var totaisBase = {
				quantidade: 0,
				custoBruto: 0,
				icms: 0,
				pis: 0,
				cofins: 0,
				total: 0
		};
		var pedidoBase = {
				id: '',
				itens: [],
				comprador: {},
				fornecedor: {}
		};

		vm.hasMessage = false;
		vm.pedido = angular.copy(pedidoBase);
		vm.pedidoChecked = vm.pedido;
		vm.isCheckout = false;
		vm.totais = angular.copy(totaisBase);

		vm.listarProdutos = function listarProdutos(fornecedor) {
			vm.pedido = angular.copy(pedidoBase);
			if(fornecedor && vm.comprador){
				ProdutoService.listar(fornecedor).then(function(res) {
					vm.produtos = res.data;
					for ( var i in vm.produtos) {
						vm.pedido.itens.push({
							produto: vm.produtos[i],
							quantidade : 0
						});
					}
				});
			}
		};

		vm.listarFornecedores = function listarFornecedores() {
			FornecedorService.listar().then(function(res) {
				vm.fornecedores = res.data;
			});
		};

		vm.listarCompradores = function listarCompradores() {
			CompradorService.listar().then(function(res) {
				vm.compradores = res.data;
			});
		};

		vm.checkout = function checkout() {
			vm.pedido.comprador = vm.comprador;
			vm.pedido.fornecedor = vm.fornecedor;
			PedidosService.checkout(vm.pedido).then(function(res) {
				vm.pedidoChecked = res.data;
				calcularTotais(vm.pedidoChecked);
				vm.isCheckout = true;
				if(vm.totais.total > vm.comprador.credito){
					vm.hasMessage = true;
					vm.message = "Valor total do pedido excede o crédito disponível para o comprador.";
				} else{
					vm.hasMessage = false;
				}
			});
		};

		vm.finalizarPedido = function finalizarPedido(){
			PedidosService.incluir(vm.pedidoChecked).then(function(res) {
				vm.pedido = res.data;
				console.log(vm.pedido);
				vm.isDialogOpen = true;
			});
		};

		vm.hasQuantidade = function hasQuantidade(){
			var isDisabled = true;
			vm.pedido.itens.forEach(function(item){
				if(item.quantidade > 0)
					isDisabled = false;
			});
			return isDisabled;
		};

		function calcularTotais(pedido){
			vm.totais = angular.copy(totaisBase);
			pedido.itens.forEach(function (item){
				vm.totais.quantidade += item.quantidade;
				vm.totais.custoBruto += item.produto.preco * item.quantidade;
				vm.totais.icms += item.icms;
				vm.totais.pis += item.pis;
				vm.totais.cofins += item.cofins;
				vm.totais.total += item.total;
			});
		}

		function init() {
			vm.listarCompradores();
			vm.listarFornecedores();
		}

		init();

	}

})();
