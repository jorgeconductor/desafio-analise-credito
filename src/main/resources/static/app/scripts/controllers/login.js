'use strict';

app
    .controller('LoginCtrl', ['$scope', 'login', '$http', '$rootScope', '$cookies', '$location', function ($scope, login, $http, $rootScope, $cookies, $location) {
        $scope.login = login;
        
        /**
    	 * Cria um novo serviço baseado nos parâmetros adicionados no input e executa o comando POST via REST
    	 */
    	$scope.logar = function (dadoLogin) {
    		var encontrado = false;
            for(var i = 0; i < login.length; i++) {
            	var funcionario = login[i];
            	if(funcionario.email === dadoLogin.email.trim() && funcionario.senha === dadoLogin.senha.trim()) {
            		$rootScope.globals = {
                        funcionarioAtual: {
                        	nomeusuario: funcionario.nome,
                            emailusuario: dadoLogin.email.trim(),
                            senhausuario: dadoLogin.senha.trim(),
                            usuarioanalista: funcionario.analista,
                        }
                    };
            		
            		// Insere o auth header padrão para requisições http
                    $http.defaults.headers.common['Authorization'] = 'Basic ' + dadoLogin.senha.trim();
                    
                    // armazena detalhes do usuário em cookies globais que fazem o manter logado por 1 semana (ou até eles deslogarem)
                    var cookieExp = new Date();
                    cookieExp.setDate(cookieExp.getDate() + 7);
                    $cookies.putObject('globals', $rootScope.globals, { expires: cookieExp });
            		encontrado = true;
            		$location.path("/");
            		break;
            	}
            }
            
            if(!encontrado) {
            	BootstrapDialog.show({
                    title: 'Erro',
                    message: 'E-mail e/ou senha incorretos!'
                });
            }
        };
    }]);