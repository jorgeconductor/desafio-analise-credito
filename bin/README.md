# desafio-analise-credito

### Desafio Conductor de Seleção 
Olá, queremos convidá-lo a participar de nosso desafio de seleção.  Pronto para participar? Seu trabalho será visto por nosso time e você receberá ao final um feedback sobre o que achamos do seu trabalho. Não é legal?

### Sobre a oportunidade 
A vaga é para Desenvolvedor Java, temos vagas com diversos níveis de senioridade e para cada um deles utilizaremos critérios específicos considerando esse aspecto, combinado? 
Se você for aprovado nesta etapa, será convidado para uma entrevista final com nosso time técnico.

### Desafio Técnico
  Desenvolver um sistema de análise de crédito para novos portadores de cartão, onde devemos ter usuários com dois tipos de permissão, sendo eles:
    * Usuário de captação de propostas de novos cartões: este usuário será responsável pelo cadastro de novos portadores de cartão.  Após o cadastro dos portadores, este usuário terá permissão apenas de verificar o resultado das análises realizadas pelos analistas.
    * Usuário analista de crédito: este usuário será responsável por verificar as propostas cadastradas, podendo aprovar ou negar a concessão de crédito. O resultado da análise será disponibilizado para o usuário de captação de proposta.
    
  - Dicionário:
    ```
    * Portador de cartão: toda pessoa que tem sua análise de crédito aprovada.
    * Análise de crédito: o processo pelo qual o analista verifica documentos, spc e quaisquer dados disponíveis para concessão de crédito.
    ```
    
  - Pré-requisitos:
    ```
    * Utilização do SGBD Oracle, MySQL ou Postgres.
    * JDK 1.8+
    * Maven 3+ (ou gradle,  ou afins)
    * JUnit 4+
    * Framework Web a critério (Servlets, JSF, Spring MVC ou afins, preferencialmente Angular)
    * Criação de um DUMP com massa de dados.
    ```

  - O que esperamos como escopo:
    ```
    * Adicionar e Manter propostas de crédito;
    * Consultar propostas de crédito, por filtros de dados;
    * Painel de análise de crédito, contendo todos os clientes cadastrados mas que não foram analisados ainda;
    * Perfis diferentes para o usuário que realiza a captação da proposta e o usuário que realiza a análise;
    ```
  - O que não esperamos como escopo:
    ```
    * Fluxo de geração de cartão
    * Qualquer fluxo realizado após a aprovação ou reprovação da proposta
    ```
  
  - O que vamos avaliar:
    ```
    * Seu código; 
    * Organização;
    * Boas práticas;
    ```

### Instruções
      1. Faça o fork do desafio e crie uma branch 'desafio_analise_credito';
      2. Desenvolva. Você terá 3 (três) dias a partir da data do envio do desafio; 
      3. Crie um arquivo de texto com a nomenclatura README.MD com a explicação de como devemos executar o 
        projeto e com uma descrição do que foi feito; 
      4. Após finalizado, envie um e-mail com o projeto zipado para dangellys.feitosa@conductor.com.br e daniel.dorta@conductor.com.br;
      
Em caso de dúvidas relacionadas a negócio, pode entrar em contato através dos e-mails acima. Desejamos boa sorte e que a força esteja com você!
