'use strict';

app
    .controller('RegistroCtrl', ['$scope', 'registro', '$http', '$location', function ($scope, registro, $http, $location) {
        $scope.registro = registro;
        
        // Mostra a URL sem a base nem as queries
    	$scope.currentBaseUrl = $location.protocol() + "://" + location.host;
        
        /**
    	 * Cria um novo serviço baseado nos parâmetros adicionados no input e executa o comando POST via REST
    	 */
    	$scope.registrar = function (reg) {
    		$scope.registro = {};
    		$scope.registro.nome = reg.nome;
    		$scope.registro.email = reg.email;
    		$scope.registro.senha = reg.senha;
    		$scope.registro.analista = reg.analista == null ? false : true;
            $http({
                url: $scope.currentBaseUrl + '/conductor/funcionario/',
                method: "POST",
                data: { 'nome' : reg.nome ,'email' : reg.email,
                	'senha': reg.senha, 'analista': $scope.registro.analista},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	$location.path('login');
            }, 
            function(response) { // opcional
            	BootstrapDialog.show({
                    title: 'Erro',
                    message: 'Erro ao registrar novo funcionário! Provavelmente o e-mail já existe, tente um outro diferente.'
                });
            });
            
        };
        
    }]);