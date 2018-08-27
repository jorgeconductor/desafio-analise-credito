'use strict';

/**
 * Cria o app e faz as configurações de cada rota de cada URL. Para cada rota, há uma página diferente e tem comportamentos diferentes
 */
var app = angular
    .module('conductorApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch'
    ])
    .config(function ($routeProvider) {
        $routeProvider
        	
        	//Quando estiver na home, abra a página main do HTML e mostre as informações do portador, menu e opções de criação, edição e exclusão
	        .when('/', {
	            templateUrl: 'app/views/portador/main.html',
	            controller: 'MainCtrl',
	            controllerAs: 'main',
	            resolve: {
	            	portador: function ($http, $location) { //  assinatura para acessar a lista de portadores
	                    return $http.get($location.protocol() + "://" + location.host + "/conductor/portador").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
	        })
            
            .when('/criarportador', {
                templateUrl: 'app/views/portador/criarportador.html',
                controller: 'MainCtrl',
	            controllerAs: 'main',
	            resolve: {
	            	portador: function ($http, $location) { //  assinatura para criar os portadores
	                    return $http.get($location.protocol() + "://" + location.host + "/conductor/portador").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            
            .when('/verificarportador', {
                templateUrl: 'app/views/portador/verificarportador.html',
                controller: 'MainCtrl',
	            controllerAs: 'main',
	            resolve: {
	            	portador: function ($http, $location) { //  assinatura para verificar os portadores
	                    return $http.get($location.protocol() + "://" + location.host + "/conductor/portador").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .when('/atualizarportador', {
                templateUrl: 'app/views/portador/atualizarportador.html',
                controller: 'MainCtrl',
	            controllerAs: 'main',
	            resolve: {
	            	portador: function ($http, $location) { //  assinatura para atualizar os portadores
	                    return $http.get($location.protocol() + "://" + location.host + "/conductor/portador").then(function (response) {
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
	                    return $http.get($location.protocol() + "://" + location.host + "/conductor/funcionario").then(function (response) {
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
	                    return $http.get($location.protocol() + "://" + location.host + "/conductor/funcionario").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .when('/analise-analista', {
                templateUrl: 'app/views/analise/analiseanalista.html',
                controller: 'AnaliseCtrl',
                controllerAs: 'analise',
                resolve: {
                	analise: function ($http, $location) { //  assinatura para acessar a lista de análises como um analista
                        return $http.get($location.protocol() + "://" + location.host + "/conductor/analise").then(function (response) {
                            return response.data;
                        })
                    }
                }
            })
            
            .when('/analise-captador', {
                templateUrl: 'app/views/analise/analisecaptador.html',
                controller: 'AnaliseCtrl',
                controllerAs: 'analise',
                resolve: {
                	analise: function ($http, $location) { //  assinatura para acessar a lista de análises como um captador
                        return $http.get($location.protocol() + "://" + location.host + "/conductor/analise").then(function (response) {
                            return response.data;
                        })
                    }
                }
            })

            .when('/criaranalise', {
                templateUrl: 'app/views/analise/criaranalise.html',
                controller: 'AnaliseCtrl',
	            controllerAs: 'analise',
	            resolve: {
	            	analise: function ($http, $location) { //  assinatura para criar as análises
	                    return $http.get($location.protocol() + "://" + location.host + "/conductor/analise").then(function (response) {
	                        return response.data;
	                    })
	                }
	            }
            })
            
            .when('/atualizaranalise', {
                templateUrl: 'app/views/analise/atualizaranalise.html',
                controller: 'AnaliseCtrl',
	            controllerAs: 'analise',
	            resolve: {
	            	analise: function ($http, $location) { //  assinatura para atualizar as análises, como um analista
	                    return $http.get($location.protocol() + "://" + location.host + "/conductor/analise").then(function (response) {
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
            } else if (loggedIn) {
            	
            	// verifica se o usuário é um analista e não e limita páginas de acesso para cada nível
            	var isAnalista = $rootScope.globals.funcionarioAtual.usuarioanalista;
            	if (isAnalista) {
            		var restrictedPage = $.inArray($location.path(), ['/', '/criarportador', '/verificarportador', '/atualizarportador', '/analise-captador']) >= 0;
            		if(restrictedPage) {
            			$location.path('analise-analista');
            		}
            	} else {
            		var restrictedPage = $.inArray($location.path(), ['/analise-analista', '/criaranalise', '/atualizaranalise']) > 0;
            		if(restrictedPage) {
            			$location.path('analise-captador');
            		}
            	}
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
	
	$scope.portadorLink = null;
	$scope.analistaLink = null;
	$scope.captadorLink = null;
	
	if ($rootScope.globals.funcionarioAtual.usuarioanalista) {
		$scope.analistaLink = "/conductor/#!/analise-analista";
	} else {
		$scope.captadorLink = "/conductor/#!/analise-captador";
		$scope.portadorLink = "/conductor/#!/";
	}
	
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

