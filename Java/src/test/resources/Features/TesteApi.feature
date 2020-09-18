#Author: elsonolilveira@gmail.com
Feature: Desafio Webservice
  A proposta destes testes eh cobrir os cenarios propostos pelo desafio webservice

  Background: Autentica utilizador
    Given Que eu esteja autenticado

  Scenario: Cadastrar usuario
    When Eu solicito o cadastro do usuario
    Then Eu recebo reposta ok

  Scenario: Listar todos os usuarios
    When Eu solicito a listagem de todos os usuarios
    Then Eu recebo a lista de todos os usuarios

  Scenario: Listar usuario cadastrado
    When Eu solicito listar usuario "3392"
    Then Eu recebo o registro do usuario

  Scenario: Alterar usuario cadastrado
    When Eu solicito alterar usuario "3392" do "nome" pelo "Alteracao by TesteApi"
    Then Eu recebo o registro do usuario