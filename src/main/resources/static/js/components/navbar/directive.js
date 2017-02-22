(function() {
  'use strict';

  angular.module('desafio')
    .directive('navBar', navBar);



    function navBar() {
      var directive = {
        controller: 'navBarController as vm',
        templateUrl: 'js/components/navbar/template.html',
        restrict: 'E',
        bindToController: {},
        scope: {}
      };

      return directive;
    }

})();