# projetos
Sistema de Cadastro, consulta, atualização e remoção de registro de alunos.

##Utilização
É necessário o banco de dados [MySQL](https://dev.mysql.com/downloads/mysql/) na versão 8.

 Após a instalação, criar base de dados sistemaaluno, caso tenha interesse em utilizar outro nome de base, alterar dentro do arquivo [application.properties](src/main/resources/application.properties).
 
 Dentro do mesmo arquivo adicionar usuário e senha para acessar o banco de dados. 
 
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sistemaaluno?serverTimezone=UTC

##Utilizar usuario e senha cadastrados na instalação do MySQL
##Utilizados no exemplo são usuário: root e senha: root
spring.datasource.username=
spring.datasource.password=
```

Caso usuário e senha de acesso ao banco sejam diferentes, alterar o arquivo [data.sql](src/main/resources/data.sql)

```SQL
##usuario padrão: root
##senha padrão, codificada em BCrypt: $2a$10$WAp6RUji4jjsRP0gl.b6EeCO1gCGXZ0WqLAs9v0cqMPqcDOb8OBTO
INSERT INTO usuario (nome, senha) VALUES ('usuario', 'senha');
```

Para criptografar nova senha ir ao arquivo [SecurityConfigurations.java](src/main/java/br/com/compasso/sistemaaluno/config/security/SecurityConfigurations.java)

Descomentar código ultimas linhas de código
````java
//Alterar "senha" pela nova
public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("senha"));
    }
````

##Documentação da API
Para acessar a documentação da Api acessar o link [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).