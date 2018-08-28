'use strict';

app
    .controller('LoginCtrl', ['$scope', 'login', '$http', '$rootScope', '$cookies', '$location', function ($scope, login, $http, $rootScope, $cookies, $location) {
        $scope.login = login;
        
     // Mostra a URL sem a base nem as queries
    	$scope.currentBaseUrl = $location.protocol() + "://" + location.host;
        
        /**
    	 * Cria um novo serviço baseado nos parâmetros adicionados no input e executa o comando POST via REST
    	 */
    	$scope.logar = function (dadoLogin) {
    		var funcionario = null;
    		
    		$http({
                url: $scope.currentBaseUrl + '/conductor/funcionario/pass',
                method: "POST",
                data: {'email' : dadoLogin.email.trim() ,'senha' : dadoLogin.senha.trim()},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	var funcionario = response.data;
            	
            	if(funcionario) {
            		$rootScope.globals = {
                        funcionarioAtual: {
                        	nomeusuario: funcionario.nome.trim(),
                            emailusuario: funcionario.email.trim(),
                            senhausuario: funcionario.senha.trim(),
                            usuarioanalista: funcionario.analista,
                        }
                    };
            		
            		// Insere o auth header padrão para requisições http
                    $http.defaults.headers.common['Authorization'] = 'Basic ' + funcionario.senha.trim();
                    
                    // armazena detalhes do usuário em cookies globais que fazem o manter logado por 1 semana (ou até eles deslogarem)
                    var cookieExp = new Date();
                    cookieExp.setDate(cookieExp.getDate() + 7);
                    $cookies.putObject('globals', $rootScope.globals, { expires: cookieExp });
            		$location.path("/");
	            } else {
	            	BootstrapDialog.show({
	                    title: 'Erro',
	                    message: 'E-mail e/ou senha incorretos!'
	                });
	            }
            }, 
            function(response) { // opcional
            	BootstrapDialog.show({
                    title: 'Erro',
                    message: 'E-mail e/ou senha incorretos!'
                });
            });
    		
            
        };
    }]);