'use strict';

/**
 * Cria o app e faz as configurações de cada rota de cada URL. Para cada rota, há uma página diferente e tem comportamentos diferentes
 */
var app = angular
    .module('controlApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch'
    ])
    .config(function ($routeProvider) {
        $routeProvider
        	
        	//Quando estiver na home, abra a página main do HTML e mostre as informações do serviço, menu e opções de criação, edição e exclusão
	        .when('/', {
	            templateUrl: 'app/views/servico/main.html',
	            controller: 'MainCtrl',
	            controllerAs: 'main',
	            resolve: {
	            	servico: function ($http, $location) { //  assinatura para acessar a lista de serviços
	                    return $http.get($location.protocol() + "://" + location.host + "/control/servico").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
	        })
        
            .when('/ordem', {
                templateUrl: 'app/views/ordem/ordemservico.html',
                controller: 'OrdemCtrl',
                controllerAs: 'ordemservico',
                resolve: {
                	ordemServico: function ($http, $location) { //  assinatura para acessar a lista de ordem de serviços
                        return $http.get($location.protocol() + "://" + location.host + "/control/ordemServico").then(function (response) {
                            return response.data;
                        })
                    }
                }
            })

            .when('/relatorio', {
                templateUrl: 'app/views/relatorio/relatorio.html',
                controller: 'RelatorioCtrl',
                controllerAs: 'relatorio',
                resolve: {
                	relatorio: function ($http, $location) { //  assinatura para acessar o relatório
                        return $http.get($location.protocol() + "://" + location.host + "/control/relatorio").then(function (response) {
                            return response.data;
                        })
                    }
                }
            })
            
            .when('/criarservico', {
                templateUrl: 'app/views/servico/criarservico.html',
                controller: 'MainCtrl',
	            controllerAs: 'main',
	            resolve: {
	            	servico: function ($http, $location) { //  assinatura para criar os serviços
	                    return $http.get($location.protocol() + "://" + location.host + "/control/servico").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .when('/atualizarservico', {
                templateUrl: 'app/views/servico/atualizarservico.html',
                controller: 'MainCtrl',
	            controllerAs: 'main',
	            resolve: {
	            	servico: function ($http, $location) { //  assinatura para atualizar os serviços
	                    return $http.get($location.protocol() + "://" + location.host + "/control/servico").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .when('/criarordem', {
                templateUrl: 'app/views/ordem/criarordem.html',
                controller: 'OrdemCtrl',
	            controllerAs: 'ordemservico',
	            resolve: {
	            	ordemServico: function ($http, $location) { //  assinatura para criar as ordens de serviço
	                    return $http.get($location.protocol() + "://" + location.host + "/control/ordemServico").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .when('/atualizarordem', {
                templateUrl: 'app/views/ordem/atualizarordem.html',
                controller: 'OrdemCtrl',
	            controllerAs: 'ordemservico',
	            resolve: {
	            	ordemServico: function ($http, $location) { //  assinatura para atualizar as ordens de serviço
	                    return $http.get($location.protocol() + "://" + location.host + "/control/ordemServico").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .when('/verificarordem', {
                templateUrl: 'app/views/ordem/verificarordem.html',
                controller: 'OrdemCtrl',
	            controllerAs: 'ordemservico',
	            resolve: {
	            	ordemServico: function ($http, $location) { //  assinatura para verificar as ordens de serviço
	                    return $http.get($location.protocol() + "://" + location.host + "/control/ordemServico").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .when('/login', {
            	templateUrl: 'app/views/autenticacao/login.html',
                controller: 'LoginCtrl',
                controllerAs: 'login',
                resolve: {
	            	login: function ($http, $location) { //  assinatura para logar o funcionário
	                    return $http.get($location.protocol() + "://" + location.host + "/control/funcionario").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .when('/registrar', {
            	templateUrl: 'app/views/autenticacao/registro.html',
                controller: 'RegistroCtrl',
                controllerAs: 'registro',
                resolve: {
                	registro: function ($http, $location) { //  assinatura para registrar os funcionários
	                    return $http.get($location.protocol() + "://" + location.host + "/control/funcionario").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .otherwise({
                redirectTo: '/'
            });
    }).run(function ($rootScope, $location, $cookies, $http) {
        
    	// manter usuário logado após dar refresh na página
        $rootScope.globals = $cookies && $cookies.getObject('globals') != null ?  $cookies.getObject('globals') : {};
        
        var loggedIn = $rootScope && $rootScope.globals && $rootScope.globals.funcionarioAtual ? true : false;
        
        if (loggedIn) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.funcionarioAtual.senhausuario;
        }
        
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
        	
        	// redireciona para a página de login se não estiver logando ou estiver tentando acessar uma página restrista
            var restrictedPage = $.inArray($location.path(), ['/login', '/registrar']) === -1;
            var loggedIn = $rootScope && $rootScope.globals && $rootScope.globals.funcionarioAtual ? true : false;
            if (restrictedPage && !loggedIn) {
                $location.path('login');
            }
        });
    });

/**
 * 
 * @returns a variável setada e retornada referenciada na factory
 */
app.factory('myFactory', function() {
	 var savedData = {}
	 
	 function set(data) {
		 savedData = data;
	 }
	 
	 function get() {
		 return savedData;
	 }

	 return {
		 set: set,
		 get: get
	 }

});

/**
 * Controller global que funciona em todas as páginas HTML
 */
app.controller("GlobalCtrl", ['$scope', '$http', '$rootScope', '$cookies', '$location', function ($scope, $http, $rootScope, $cookies, $location) {
	
	$scope.nomeusuario = $rootScope.globals.funcionarioAtual.nomeusuario;
	
	/**
     * Limpar credenciais do usuário, será necessário efetuar o login novamente
     */
    $scope.logout = function() {
        $rootScope.globals = {};
        $cookies.remove('globals');
        $http.defaults.headers.common.Authorization = 'Basic';
        $location.path('login');
    }
    
}]);