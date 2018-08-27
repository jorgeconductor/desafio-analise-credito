'use strict';

app
    .controller('MainCtrl', ['$scope', 'servico', '$http', 'myFactory', '$location', function ($scope, servico, $http, myFactory, $location) {
    	
    	$scope.servico = servico;
    	
    	// Pega as informações passadas na factory do app
    	$scope.servicoEdicao = myFactory.get();
    	
    	// Mostra a URL sem a base nem as queries
    	$scope.currentBaseUrl = $location.protocol() + "://" + location.host;
    	
    	/**
    	 * Cria um novo serviço baseado nos parâmetros adicionados no input e executa o comando POST via REST
    	 */
    	$scope.criarServico = function (servico) {
    		$scope.servico.descricao = servico.descricao;
    		$scope.servico.valor = servico.valor;
            $http({
                url: $scope.currentBaseUrl + '/control/servico/',
                method: "POST",
                data: { 'descricao' : $scope.servico.descricao ,'valor' : $scope.servico.valor},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	$location.path('/control/#!/');
            }, 
            function(response) { // opcional
            	$location.path('/control/#!/');
            });
            
        };
        
        /**
         * Atualiza o serviço específico passando todos os parâmetros editados para o comando PUT via REST, depois redireciona para a página do serviço
         */
        $scope.atualizarServico = function (servico) {
        	$scope.servico.descricao = servico.descricao;
    		$scope.servico.valor = servico.valor;
            $http({
                url: $scope.currentBaseUrl + '/control/servico/' + servico.id,
                method: "PUT",
                data: {'descricao' : $scope.servico.descricao ,'valor' : $scope.servico.valor},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	$location.path('/control/#!/');
            }, 
            function(response) { // opcional
            	$location.path('/control/#!/');
            });
            
        };
        
        /**
         * Mostra uma janela de confirmação e caso confirmado, deleta o serviço específico selecionado passando como parâmetro na chamada REST o ID do serviço
         */
        $scope.deletarServico = function (servico) {
        	BootstrapDialog.show({
                title: 'Confirmação de ação',
                message: "Tem certeza que deseja deletar o serviço de número " + servico.id + "? Isso fará deletar todas as ordens de serviço relacionadas também.",
                buttons: [{
                    label: 'Sim',
                    cssClass: 'btn-primary',
                    action: function(dialog) {
                    	dialog.close();
                    	$scope.servico = {};
                    	$http({
                            url: $scope.currentBaseUrl + '/control/servico/' + servico.id,
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
        
        // Limpa todos os dados da lista de serviços chamando o comando via REST
        $scope.limparServicos = function (servico) {
            $http.delete($scope.currentBaseUrl + '/control/servico/');
            $scope.recarregarPagina();
        };
        
      // Recarrega a página atual
        $scope.recarregarPagina = function() {
           location.reload(); 
        };
        
     // Edita o serviço e passa as variáveis para a factory para ser reutilizado na página de edição
        $scope.editarServico = function(d) {
        	myFactory.set(d);
         };
         
      // Cria o serviço e passa as variáveis para a factory para ser reutilizado na página de criação
         $scope.criarOrdemServico = function(d) {
         	myFactory.set(d);
          };
          
        
    }]);
