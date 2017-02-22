(function() {
  'use strict';

  angular.module('desafio')
    .controller('navBarController', navBarController);

  navBarController.$inject = ['$state'];

  function navBarController($state) {
      var vm = this;
  }

})();