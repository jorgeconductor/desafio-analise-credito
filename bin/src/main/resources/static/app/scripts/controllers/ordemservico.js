'use strict';

app
    .controller('OrdemCtrl', ['$scope', 'ordemServico', '$http', 'myFactory', '$location', function ($scope, ordemServico, $http, myFactory, $location) {
        
    	$scope.ordemServico = ordemServico;
    	
    	// Pega as informações passadas na factory do app
    	$scope.ordemServicoEdicao = myFactory.get();
    	
    	// Mostra a URL sem a base nem as queries
    	$scope.currentBaseUrl = $location.protocol() + "://" + location.host;
    	
    	/**
    	 * Cria uma nova ordem de serviço baseado nos parâmetros adicionados no input e executa o comando POST via REST
    	 */
        $scope.inserirOrdemServico = function (ordemServico) {
    		$scope.ordemServico.servicoId = ordemServico.id;
    		$scope.ordemServico.quantidade = ordemServico.quantidade;
    		$scope.ordemServico.nomeFuncionario = ordemServico.nomeFuncionario;
    		$scope.ordemServico.data = ordemServico.data;
    		$scope.ordemServico.horaInicio = ordemServico.horaInicio;
    		$scope.ordemServico.horaFim = ordemServico.horaFim;
    		$scope.ordemServico.detalhe = ordemServico.detalhe;
            $http({
                url: $scope.currentBaseUrl + '/control/ordemServico/',
                method: "POST",
                data: { 'servicoId' : ordemServico.id ,'quantidade' : ordemServico.quantidade, 
                	'nomeFuncionario' : ordemServico.nomeFuncionario , 'data': ordemServico.data, 'horaInicio' : ordemServico.horaInicio, 
                	'horaFim' : ordemServico.horaFim ,'detalhe' : ordemServico.detalhe
                	},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	$location.path('ordem');
            }, 
            function(response) { // opcional
            	$location.path('ordem');
            });
            
        };
        
        /**
         * Atualiza a ordem de serviço específico passando todos os parâmetros editados para o comando PUT via REST, depois redireciona para a página da ordem
         */
        $scope.atualizarOrdemServico = function (ordemServico) {
        	$scope.ordemServico = {};
        	$scope.ordemServico.servicoId = ordemServico.servico.id;
    		$scope.ordemServico.quantidade = ordemServico.quantidade;
    		$scope.ordemServico.nomeFuncionario = ordemServico.nomeFuncionario;
    		$scope.ordemServico.data = ordemServico.data;
    		$scope.ordemServico.horaInicio = ordemServico.horaInicio;
    		$scope.ordemServico.horaFim = ordemServico.horaFim;
    		$scope.ordemServico.detalhe = ordemServico.detalhe;
            $http({
                url: $scope.currentBaseUrl + '/control/ordemServico/' + ordemServico.id,
                method: "PUT",
                data: { 'servicoId' : ordemServico.servico.id ,'quantidade' : ordemServico.quantidade, 
                	'nomeFuncionario' : ordemServico.nomeFuncionario , 'data': ordemServico.data, 'horaInicio' : ordemServico.horaInicio, 
                	'horaFim' : ordemServico.horaFim ,'detalhe' : ordemServico.detalhe
                	},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	$location.path('ordem');
            }, 
            function(response) { // opcional
            	$location.path('ordem');
            });
            
        };
        
        /**
         * Mostra uma janela de confirmação e caso confirmado, deleta a ordem deserviço específico selecionado passando como parâmetro na chamada REST o ID da ordem de serviço
         */
        $scope.deletarOrdemServico = function (ordemServico) {
        	BootstrapDialog.show({
                title: 'Confirmação de ação',
                message: "Tem certeza que deseja deletar a ordem do serviço " + ordemServico.id + "?",
                buttons: [{
                    label: 'Sim',
                    cssClass: 'btn-primary',
                    action: function(dialog) {
                    	dialog.close();
                    	$scope.ordemServico = {};
                    	$http({
                            url: $scope.currentBaseUrl + '/control/ordemServico/' + ordemServico.id,
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

     // Limpa todos os dados da lista de ordem de serviços chamando o comando via REST
        $scope.limparOrdens = function (ordemServico) {
            $http.delete($scope.currentBaseUrl + '/control/ordemServico/');
            $scope.recarregarPagina();
        };
        
        //Recarrega a página atual
		$scope.recarregarPagina = function() {
		    location.reload(); 
		 };
		 
		 // Edita a ordem de serviço e passa as variáveis para a factory para ser reutilizado na página de edição
		$scope.editarOrdemServico = function(d) {
		 	myFactory.set(d);
		};
		
		 // Cria a ordem de serviço e passa as variáveis para a factory para ser reutilizado na página de criação
		$scope.criarOrdemServico = function(d) {
		 	myFactory.set(d);
		};
		
		// Verifica a ordem de serviço e passa as variáveis para a factory para ser reutilizado na página de verificação
		$scope.verificarOrdemServico = function(d) {
		  	myFactory.set(d);
		};


    }]);