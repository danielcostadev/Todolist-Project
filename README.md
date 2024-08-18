# TODOList - Autor: Daniel Costa - ACZG 6.0

TODOlist é uma aplicação Java que permite o gerenciamento eficiente de tarefas. Com funcionalidades como criação, edição, exclusão e ordenação de tarefas, esta aplicação está sendo desenvolvida como um dos projetos do ACZG 6.0.

## Funcionalidades

- **Adicionar Tarefas**: Crie novas tarefas especificando nome, descrição, data de término, prioridade, categoria e status.
- **Editar Tarefas**: Modifique as informações das tarefas existentes para mantê-las atualizadas.
- **Excluir Tarefas**: Remova tarefas que não são mais necessárias.
- **Ordenação de Tarefas**: Ordene as tarefas por prioridade, categoria ou status.
- **Persistência de Dados**: As tarefas são armazenadas em um arquivo CSV para fácil manipulação e recuperação.
- **Validação de Dados**: Entrada de dados validada para garantir a integridade das informações.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal utilizada no desenvolvimento do projeto.
- **Git e GitHub**: Para versionamento e armazenamento do projeto
- **JDK 8**: Versão do Java Development Kit utilizada.
- **CSV**: Formato de arquivo para armazenamento de dados persistentes.
- **IntelliJ IDEA**: Ambiente de Desenvolvimento Integrado (IDE) utilizado.

## Minhas breves considerações

> O projeto do TODO List e suas funcionalidades é um ótimo desafio para explorar diversos conceitos aprendidos até o momento um exemplo POO: Polimorfismo, Encapsulamento, herança e etc.
>
> O reaproveitamento de alguns métodos, e características estão sendo essenciais no desenvolvimento do projeto, poder "esconder" algumas funcionalidades com modificadores de acesso e com a utilização de pacotes par a organizar o código e delegar as responsabilidades é muito gratificante.
>
> Estou aprimorando minhas habilidades com as comparações através do comparator, no projeto também pude utilizar referências no método de criação das tabelas para poder ordenar a lista dinamicamente de acordo com a necessidade.
>
> Utilizei uma nova abordagem de validar os dados passados pelo usuário, onde é possível evitar exceções em tempo de execução.
>
> Também pude realizar a persistência dos dados, utilizando as classes de leitura e escrita BufferedReader e BufferedWriter para manipular o arquivo CSV.
>
> Devido a organização do projeto seguindo as premissas básicas do modelo MVC Apenas a classe DAO do pacote Model pode realizar alterações no arquivo.
>
> Como não houve a exigência de implementação de um front-end robusto estudei algumas soluções para exibição das tarefas em forma de tabela e adaptei as necessidades do projeto.
>

## COMO FUNCIONA?
### Tela Principal
- Ao executar a aplicação o usuário é apresentado a um modesto Menu interativo
- Onde é possível escolher ações como Cadastrar, Editar, Excluir ou Listar Tarefas
### Adicionar Tarefa
- Na opção de cadastrar tarefa o usuário preenche um breve formulário.
- Durante o preenchimento do formulário são realizadas validações quanto a integridade dos dados.
- Todas as entradas de dados passam por esse rigoroso sistema de validações.
- Essas validações asseguram que não sejam lançadas exceções durante a execução da aplicação
- Também garantem que não sejam passados dados nulos ou adversas aos dados esperados
- Após o preenchimento da última informação, os dados são armazenados em uma lista.
- É realizada uma reeordenação com o critério por prioridade.
- Essa lista é enviada para a classe DAO que é responsável por gravar as informações no arquivo CSV.
### Editar Tarefa
- É exibida uma lista com todas as tarefas cadastradas,e  o usuário informa o id da tarefa a ser editada
- Logo após os dados atuais da tarefa selecionada são exibidos para o usuário.
- Então o usuário precisa preencher um formulário semelhante ao de cadastro, o processo é idêntico.
### Excluir Tarefa
- O procedimento de excluir uma Tarefa utiliza StringBuilder, para armazenar as tarefas por linhas
- Segue o mesmo procedimento de Editar Tarefa para receber o id como parâmetro, verifica se aquele id Existe
- Caso exista O StringBuilder recebe os dados sem a linha em questão e o arquivo CSV é reescrito.
### Listar Tarefas
- São exibidas as tarefas cadastradas que por sua vez são recuperadas de uma lista que foi previamente populada
- O arquivo CSV é lido pela classe DAO, e os dados são armazenados em uma lista.
- O método de ordenação permite a exibição das tarefas por: Prioridade, Status, Categoria

### Outras funcionalidades podem ser exploradas com a utilização da aplicação. Aproveite a usabilidade!

## Estrutura do Projeto

### O projeto está organizado da seguinte forma:

- **Controller**: Contém os métodos responsáveis por gerenciar as operações das tarefas.
- **DAO (Data Access Object)**: Gerencia a leitura e escrita de dados no arquivo CSV.
- **Model**: Define as classes principais do domínio, como `Tarefa`.
- **View**: Contém a estrutura principal de exibição do Menu e tarefas.
- **Utils**: Contém métodos que implementam comparadores para ordenação das tarefas.

## Como Executar

### Pré-requisitos

- JDK 8 ou superior instalado.
- IDE como IntelliJ IDEA (opcional, mas recomendado).

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/danielcostadev/Todolist-Project.git
   cd Todolist-Project

2. Navegue até a pasta do projeto

   ```bash
   cd Todolist-Project/

3. Compile o projeto

   ```bash
   javac -d bin src/model/*.java src/view/*.java src/controller/*.java src/utilitarios/*.java
 
4. Execute o projeto

   ```bash
   java -cp bin view.ToDoList

### Download do arquivo JAR

- **Link:** [Clique aqui para baixar](https://github.com/danielcostadev/Todolist-Project/raw/master/Todolist-Project.rar) 
- Após baixar o arquivo RAR, extraia escolhendo a opção "Extrair aqui".
- Com auxílio do seu terminal navegue até a pasta que foi extraída.
- Para executar a aplicação utilize o comando abaixo:

   ```bash
  java -jar Todolist-Project.jar
### !!! Não apague o arquivo "tarefas.csv" por enquanto ele é a sua base de dados.

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

- **MIT LICENSE:** [Ver licença](https://github.com/danielcostadev/Todolist-Project/blob/master/LICENSE)


## Contato

Para maiores informações ou dúvidas, entre em contato:

- **Nome:** Daniel Costa
- **LinkedIn:** [DanielCostaDev](https://www.linkedin.com/in/danielcostadev)
