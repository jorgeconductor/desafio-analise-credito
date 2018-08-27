'use strict';

app
    .controller('AnaliseCtrl', ['$scope', 'analise', '$http', 'myFactory', '$location', function ($scope, analise, $http, myFactory, $location) {
        
    	$scope.analises = analise;
    	
    	// Pega as informações passadas na factory do app
    	$scope.analiseEdicao = myFactory.get();
    	
    	// Mostra a URL sem a base nem as queries
    	$scope.currentBaseUrl = $location.protocol() + "://" + location.host;
    	
    	/**
    	 * Cria uma nova análise baseado nos parâmetros adicionados no input e executa o comando POST via REST
    	 */
        $scope.inserirAnalise = function (analise) {
        	$scope.analise = {};
    		$scope.analise.portadorId = analise.portador.id;
    		$scope.analise.aprovado = analise.aprovado != null && analise.aprovado.toLowerCase() === "aprovado" ? true : false;
            $http({
                url: $scope.currentBaseUrl + '/conductor/analise/',
                method: "POST",
                data: { 'portadorId' : $scope.analise.portadorId  ,'aprovado' : $scope.analise.aprovado},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	$location.path('analise-analista');
            }, 
            function(response) { // opcional
            	$location.path('analise-analista');
            });
            
        };
        
        /**
         * Atualiza a análise específica de um portador passando todos os parâmetros editados para o comando PUT via REST, depois redireciona para a página da lista de análises
         */
        $scope.atualizarAnalise = function (analise) {
        	$scope.analise = {};
        	$scope.analise.portadorId = analise.portador.id;
    		$scope.analise.aprovado = analise.aprovado != null && analise.aprovado.toLowerCase() === "aprovado" ? true : false;
            $http({
                url: $scope.currentBaseUrl + '/conductor/analise/' + analise.id,
                method: "PUT",
                data: {'portadorId' : $scope.analise.portadorId ,'aprovado' : $scope.analise.aprovado},
                headers: {'Content-Type': 'application/json'}
            })
            .then(function(response) {
            	$location.path('analise-analista');
            }, 
            function(response) { // opcional
            	$location.path('analise-analista');
            });
            
        };
        
        /**
         * Mostra uma janela de confirmação e caso confirmado, deleta a análise específica de um portador selecionado passando como parâmetro na chamada REST o ID da análise
         */
        $scope.deletarAnalise = function (analise) {
        	BootstrapDialog.show({
                title: 'Confirmação de ação',
                message: "Tem certeza que deseja deletar a análise do portador " + analise.id + "?",
                buttons: [{
                    label: 'Sim',
                    cssClass: 'btn-primary',
                    action: function(dialog) {
                    	dialog.close();
                    	$scope.analise = {};
                    	$http({
                            url: $scope.currentBaseUrl + '/conductor/analise/' + analise.id,
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

        // Limpa todos os dados da lista de análises chamando o comando via REST
        $scope.limparAnalises = function (analise) {
            $http.delete($scope.currentBaseUrl + '/conductor/analise/');
            $scope.recarregarPagina();
        };
        
        //Retorna apenas os dados de análise pendentes
        $scope.filtrarPendentes = function () {
        	$http.get($scope.currentBaseUrl + '/conductor/analise/pendentes')
            .then(function(response) {
            	$scope.analises = response.data;
            });
        };
        
      //Retorna todos os dados de análise dos portadores
        $scope.filtrarTodos = function () {
        	$http.get($scope.currentBaseUrl + '/conductor/analise')
            .then(function(response) {
            	$scope.analises = response.data;
            });
        };
        
        //Recarrega a página atual
		$scope.recarregarPagina = function() {
		    location.reload(); 
		 };
		 
		 // Edita a análise e passa as variáveis para a factory para ser reutilizado na página de edição
		$scope.editarAnalise = function(d) {
			d.dataAnalise = new Date(d.dataAnalise);
		 	myFactory.set(d);
		};
		
		 // Cria a análise e passa as variáveis para a factory para ser reutilizado na página de criação
		$scope.criarAnalise = function(d) {
		 	myFactory.set(d);
		};
		
		// Verifica a análise e passa as variáveis para a factory para ser reutilizado na página de verificação
		$scope.verificarAnalise = function(d) {
		  	myFactory.set(d);
		};


    }]);


app.filter('isSpc', function() {
	return function(input) {
        return input ? 'Sim' : 'Não';
    }
})

app.filter('isAproved', function() {
	return function(input) {
		
		if (input == null) {
			return "Pendente";
		} else if (input) {
			return "Aprovado";
		}
		
        return "Reprovado";
    }
})
