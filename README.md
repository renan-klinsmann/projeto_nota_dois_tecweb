# projeto_nota_dois_tecweb

Requisitos do projeto 

1 - Validar o acesso do usuário - Verificar se o usuário existe no bando de dados, e caso exista, leva o mesmo para a home do sistema. Caso contrário, devolve o usuário para tela de login, informando o erro de acesso.
2 - Cadastrar Usuários - Desenvolver um caso de uso para cadastro de usuários, onde o sistema irá listar todos os usuários cadastrados, onde o usuário que está logado, poderá remover, editar e cadastro outro usuário.
3 - Plus: Esse requisito vale, 3.0 pontos. Será necessário que o usuário ao tentar se logar, passe pelo processo de autenticação por meio de um filtro, conforme código já desenvolvido no projeto.

O projeto criado atende todos os requisitos solicitados, ao configurar e acessar o projeto o mesmo será redirecionado para uma tela de login com a opção de primeiro acesso, no primeiro acesso terá os campos de email, nome, phone e senha preenchendo os campos e salvando o mesmo será redirecionado para a tela de login onde poderá efetuar seu acesso, referente ao primeiro acesso caso o mesmo ja tenha email ou phone cadastrados sera apresentado um alerta informando para o mesmo.
Login: caso o email ou senha esteja incorreto será exibido uma mensagem de alerta para o mesmo, ao logar o mesmo entrara em um filtro onde será levantado uma sessão onde para invalidar e deslogar, o usuario precisa clicar no botão de logout no canto superior direito da tela. estando tudo ok com o acesso o usuario sera redirecionado para uma home, com a opção de Cadastros de Usuarios, onde ao clicar será listado todos os cadastros que estão na base com a opção de remover e editar, acima da lista terá um botão para cadastrar um novo usuario, o botão de remover ao clicar ira remover o usuario da sua linha e será exibido uma mensagem de sucesso, ao clicar no editar ele sera redirecionado para outra tela com os campos preenchidos para edição ao salva sera redicionado para lista ja com o usuario editado carregado e será exibido uma mensagem de sucesso.
Metodos jpa:
Remove: EntityManager remove(objeto)
Update: EntityManager merge(objeto)
Save: EntityManager persist(objeto)
Get: EntityManager createNamedQuery(objeto.parametros)

Configurações para rodar:
Clonar o projeto
No eclipse -> import -> exist maven project -> 
                         run -> maven clean -> 
                         maven -> update project marcar force update project
No persistence do projeto configura o arquivo para sua base de dados.
Se for mysql so mudar o root e senha do persistence e criar a tabela com os campos igual ao model client, segue abaixo os scripts mysql

CREATE DATABASE acme;

CREATE TABLE clients (
  Id int NOT NULL AUTO_INCREMENT,
  Email varchar(100) DEFAULT NULL,
  Phone varchar(100) DEFAULT NULL,
  Name varchar(100) DEFAULT NULL,
  senha varchar(200) DEFAULT NULL,
  PRIMARY KEY (Id)
) 
