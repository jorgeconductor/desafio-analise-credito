'use strict';

app
    .controller('MainCtrl', ['$scope', 'portador', '$http', 'myFactory', '$location', function ($scope, portador, $http, myFactory, $location) {
    	
    	$scope.portador = portador;
    	
    	// Pega as informações passadas na factory do app
    	$scope.portadorEdicao = myFactory.get();
    	
    	// Mostra a URL sem a base nem as queries
    	$scope.currentBaseUrl = $location.protocol() + "://" + location.host;
    	
    	/**
		 * Cria um novo portador baseado nos parâmetros adicionados no input e
		 * executa o comando POST via REST
		 */
    	$scope.criarPortador = function (portador) {
    		$scope.portador = {};
    		$scope.portador.nome = portador.nome;
    		$scope.portador.email = portador.email;
    		$scope.portador.endereco = portador.endereco;
    		$scope.portador.rg = portador.rg;
    		$scope.portador.cpf = portador.cpf;
    		$scope.portador.telefone = portador.telefone;
    		$scope.portador.dataNascimento = portador.dataNascimento;
    		$scope.portador.profissao = portador.profissao;
    		$scope.portador.salario = portador.salario;
    		$scope.portador.spc = portador.spc != null && portador.spc.toLowerCase() === "sim" ? true : false;
            $http({
                url: $scope.currentBaseUrl + '/conductor/portador/',
                method: "POST",
                data: {'nome' : $scope.portador.nome ,'email' : $scope.portador.email, 'endereco': $scope.portador.endereco,
                	'rg': $scope.portador.rg, 'cpf':$scope.portador.cpf,'telefone':$scope.portador.telefone,'dataNascimento':$scope.portador.dataNascimento,
                	'profissao':$scope.portador.profissao,'salario':$scope.portador.salario,'spc':$scope.portador.spc},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	$location.path('/conductor/#!/analise-captador');
            }, 
            function(response) { // opcional
            	$location.path('/conductor/#!/analise-captador');
            });
            
        };
        
        /**
		 * Atualiza o portador específico passando todos os parâmetros editados
		 * para o comando PUT via REST, depois redireciona para a página de
		 * portadores
		 */
        $scope.atualizarPortador = function (portador) {
        	$scope.portador = {};
        	$scope.portador.nome = portador.nome;
    		$scope.portador.email = portador.email;
    		$scope.portador.endereco = portador.endereco;
    		$scope.portador.rg = portador.rg;
    		$scope.portador.cpf = portador.cpf;
    		$scope.portador.telefone = portador.telefone;
    		$scope.portador.dataNascimento = portador.dataNascimento;
    		$scope.portador.profissao = portador.profissao;
    		$scope.portador.salario = portador.salario;
    		$scope.portador.spc = portador.spc != null && portador.spc.toLowerCase() === "sim" ? true : false ;
            $http({
                url: $scope.currentBaseUrl + '/conductor/portador/' + portador.id,
                method: "PUT",
                data: {'nome' : $scope.portador.nome ,'email' : $scope.portador.email, 'endereco': $scope.portador.endereco,
                	'rg': $scope.portador.rg, 'cpf':$scope.portador.cpf,'telefone':$scope.portador.telefone,'dataNascimento':$scope.portador.dataNascimento,
                	'profissao':$scope.portador.profissao,'salario':$scope.portador.salario,'spc':$scope.portador.spc},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	$location.path('/conductor/#!/analise-captador');
            }, 
            function(response) { // opcional
            	$location.path('/conductor/#!/analise-captador');
            });
            
        };
        
        /**
		 * Mostra uma janela de confirmação e caso confirmado, deleta o portador
		 * específico selecionado passando como parâmetro na chamada REST o ID
		 * do portador
		 */
        $scope.deletarPortador = function (portador) {
        	BootstrapDialog.show({
                title: 'Confirmação de ação',
                message: "Tem certeza que deseja deletar o portador de RG " + portador.rg + "? Isso fará deletar todas as análises de crédito relacionadas também.",
                buttons: [{
                    label: 'Sim',
                    cssClass: 'btn-primary',
                    action: function(dialog) {
                    	dialog.close();
                    	$scope.portador = {};
                    	$http({
                            url: $scope.currentBaseUrl + '/conductor/portador/' + portador.id,
                            method: "DELETE",
                            headers: {'Content-Type': 'application/json'}
                        })
                        .then(function(response) {
                        	$scope.recarregarPagina();
                        }, 
                        function(response) { // opcional
                        	$scope.recarregarPagina();
                        });
                    }
                }, {
                    label: 'Não',
                    action: function(dialog) {
                        dialog.close();
                    }
                }]
            });
        };
        
        // Limpa todos os dados da lista de portadores chamando o comando via
		// REST
        $scope.limparPortadores = function (portador) {
            $http.delete($scope.currentBaseUrl + '/conductor/portador/');
            $scope.recarregarPagina();
        };
        
      // Recarrega a página atual
        $scope.recarregarPagina = function() {
           location.reload(); 
        };
        
     // Edita o portador e passa as variáveis para a factory para ser
		// reutilizado na página de edição
        $scope.editarPortador = function(d) {
        	d.dataNascimento = new Date(d.dataNascimento);
        	myFactory.set(d);
         };
         
      // Verifica o portador e passa as variáveis para a factory para
		// ser reutilizado na página de verificação
 		$scope.verificarPortador = function(d) {
 		  	myFactory.set(d);
 		};
 		
    }]);


app.filter('isSpc', function() {
	return function(input) {
        return input ? 'Sim' : 'Não';
    }
})
