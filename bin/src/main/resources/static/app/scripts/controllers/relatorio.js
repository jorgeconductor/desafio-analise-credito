'use strict';

app
    .controller('RelatorioCtrl', ['$scope', 'relatorio', '$http', function ($scope, relatorio, $http) {
    	// Passa os dados do relatório ao escopo
        $scope.relatorios = relatorio;
    }]);