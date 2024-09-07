// Define variáveis para tratamento e geração de estrutura geral
var tabela = document.getElementById("tabela-tarefas");
var tabelaItens = document.getElementById("tabela-itens");
var botaoAdicionar = document.getElementById("adicionar");
var botaoSalvar = document.getElementById("salvar");
var botaoLimpar = document.getElementById("limpar");
var botaoCancelar = document.getElementById("cancelar");
var botaoDesfazer = document.getElementById("desfazer");
var botaoCancelarEdicao = document.getElementById("cancelarEdicao");
var caixaFormularioCadastro = document.getElementById("caixa-branca-cadastro");
var caixaFormularioEdicao = document.getElementById("caixa-branca-editar");
var formularioCadastro = document.getElementById("frmCadastro");
var formularioEdicao = document.getElementById("frmEdicao");

// Abre formulário para adicionar nova tarefa
document.getElementById("botao-adicionar-nova-tarefa").onclick = function () {
    caixaFormularioCadastro.style.display = "initial";
    caixaFormularioEdicao.style.display = "none";

};

// // Atualiza lista de tarefas com infomações do localstorage, sempre que a página é carregada completamente
document.addEventListener("DOMContentLoaded", function () {
    listarTarefas();
});


function limparFormulario(formulario) {
    formulario.reset();
};

let limparFrmCadastro = formularioCadastro;
let limparFrmEdicao = formularioEdicao; 



// Validação de dados do formulário
function validarDados(campos, formulario) {

    for (const campo of campos) {
        let valor = formulario[campo.nome].value.trim();
        if (!valor) {
            alert(campo.mensagem);
            formulario[campo.nome].focus()
            return false;
        }
    }

    return true;
};

// Definição e campos do formulário de cadastro para validação
const camposCadastro = [
    { nome: "nome", mensagem: "O campo Nome está vazio." },
    { nome: "descricao", mensagem: "O campo Descrição está vazio." },
    { nome: "data", mensagem: "O campo Data está vazio." },
    { nome: "categoria", mensagem: "O campo Categoria está vazio." },
    { nome: "prioridade", mensagem: "O campo Prioridade está vazio." },
    { nome: "status", mensagem: "O campo Status está vazio." }
];

// Definição e campos do formulário de ddição para validação
const camposEdicao = [
    { nome: "nomeEdit", mensagem: "O campo Nome está vazio." },
    { nome: "descricaoEdit", mensagem: "O campo Descrição está vazio." },
    { nome: "dataEdit", mensagem: "O campo Data está vazio." },
    { nome: "categoriaEdit", mensagem: "O campo Categoria está vazio." },
    { nome: "prioridadeEdit", mensagem: "O campo Prioridade está vazio." },
    { nome: "statusEdit", mensagem: "O campo Status está vazio." }
];




function cadastrarTarefa() {
    // Lista para guardar dados das tarefas antes de enviar para o localStorage
    let listaTarefas = JSON.parse(localStorage.getItem("minhaTarefa")) || [];

    // recebe os dados preenchidos no formulário
    let id = listaTarefas.length + 1;
    let nome = frmCadastro.nome.value;
    let descricao = frmCadastro.descricao.value;
    let data = frmCadastro.data.value;
    let categoria = frmCadastro.categoria.value;
    let prioridade = frmCadastro.prioridade.value;
    let status = frmCadastro.status.value;

    // cria o objeto tarefa
    const tarefa = { id: id, nome: nome, descricao: descricao, data: data, categoria: categoria, prioridade: prioridade, status: status };

    // Adiciona a tarefa na lista
    listaTarefas.push(tarefa);

    // Armazena a lista atualizada no localStorage
    localStorage.setItem("minhaTarefa", JSON.stringify(listaTarefas));

    alert("Tarefa cadastrada com sucesso!");

}

botaoAdicionar.onclick = function () {

    if (validarDados(camposCadastro,frmCadastro)) {

        caixaFormularioCadastro.style.display = "none";
        cadastrarTarefa();
        limparFormulario(limparFrmCadastro);
        listarTarefas();

    };
};

botaoLimpar.onclick = function () {

    let resposta = confirm('Tem certeza que deseja limpar o formulário?');

    if (resposta === true) {
        limparFormulario(limparFrmCadastro);
    }
};

botaoCancelar.onclick = function () {

    let resposta = confirm('Tem certeza que deseja fechar o formulário?');

    if (resposta === true) {
        limparFormulario(limparFrmCadastro);
        caixaFormularioCadastro.style.display = "none";
    }

};



function preencherFormulario(idTarefa) {


    // Lista para guardar dados das tarefas antes de enviar para o localStorage
    let listaTarefas = JSON.parse(localStorage.getItem("minhaTarefa")) || [];
    // Localiza a tarefa com base no ID
    let elementoTarefa = listaTarefas.find(tarefa => tarefa.id === idTarefa);

    if (elementoTarefa) {
        // Exibe o formulário de edição
        caixaFormularioEdicao.style.display = "initial";

        // Preenche os campos do formulário com os dados da tarefa
        document.querySelector('input[name="id"]').value = elementoTarefa.id || "";
        document.querySelector('input[name="nomeEdit"]').value = elementoTarefa.nome || "";
        document.querySelector('input[name="descricaoEdit"]').value = elementoTarefa.descricao || "";
        document.querySelector('input[name="dataEdit"]').value = elementoTarefa.data || "";
        document.querySelector('select[name="categoriaEdit"]').value = elementoTarefa.categoria || "";
        document.querySelector('select[name="prioridadeEdit"]').value = elementoTarefa.prioridade || "";
        document.querySelector('select[name="statusEdit"]').value = elementoTarefa.status || "";

    } else {
        console.log("Tarefa não encontrada.");
    }

}




function salvarTarefaEditada(idTarefa) {
    // Recupera a lista de tarefas do localStorage
    let listaTarefas = JSON.parse(localStorage.getItem("minhaTarefa")) || [];

    // Localiza o índice da tarefa no array
    let indiceTarefa = listaTarefas.findIndex(tarefa => tarefa.id === idTarefa);

    if (indiceTarefa !== -1) {
        // Recebe os dados preenchidos no formulário
        let nomeEdit = frmEdicao.nomeEdit.value;
        let descricaoEdit = frmEdicao.descricaoEdit.value;
        let dataEdit = frmEdicao.dataEdit.value;
        let categoriaEdit = frmEdicao.categoriaEdit.value;
        let prioridadeEdit = frmEdicao.prioridadeEdit.value;
        let statusEdit = frmEdicao.statusEdit.value;

        // Atualiza o objeto tarefa com os novos dados
        listaTarefas[indiceTarefa] = {
            id: idTarefa,
            nome: nomeEdit,
            descricao: descricaoEdit,
            data: dataEdit,
            categoria: categoriaEdit,
            prioridade: prioridadeEdit,
            status: statusEdit
        };

        // Armazena a lista atualizada no localStorage
        localStorage.setItem("minhaTarefa", JSON.stringify(listaTarefas));

        alert("Tarefa editada com sucesso!");

    } else {

        alert("Tarefa não pode ser editada, tente novamente!");

    }
}

botaoSalvar.onclick = function () {
    let idTarefa = parseInt(document.querySelector('input[name="id"]').value);

    if (validarDados(camposEdicao,frmEdicao)) {
        salvarTarefaEditada(idTarefa);
        caixaFormularioEdicao.style.display = "none";
        listarTarefas();
    };
};

botaoDesfazer.onclick = function () {
    let idTarefa = parseInt(document.querySelector('input[name="id"]').value);
    let resposta = confirm('Tem certeza que deseja desfazer as alterações?');

    if (resposta === true) {
        limparFormulario(limparFrmEdicao);
        preencherFormulario(idTarefa);
    }
};

botaoCancelarEdicao.onclick = function () {

    let resposta = confirm('Tem certeza que deseja fechar o formulário?');

    if (resposta === true) {
        caixaFormularioEdicao.style.display = "none";
    }

};





function listarTarefas() {

    let dados = JSON.parse(localStorage.getItem("minhaTarefa")) || [];

    // Limpa lista de itens antes de preencher novamente
    tabelaItens.innerHTML = "";

    dados.forEach((tarefa, index) => {

        var linha = tabelaItens.insertRow();
        var cellId = linha.insertCell(0);
        var cellNome = linha.insertCell(1);
        var cellData = linha.insertCell(2);
        var cellOpcoes = linha.insertCell(3);

        cellId.innerHTML = tarefa.id;
        cellNome.innerHTML = tarefa.nome;
        cellData.innerHTML = tarefa.data;

        var botaoContainer = criarBotaoContainer(); // Função que cria o container com os botões
        cellOpcoes.appendChild(botaoContainer);

        // Para que os botões funcionem, devemos atribuir eventos logo após serem adicionados ao DOM
        var botaoEditar = botaoContainer.querySelector('.botao2');
        var botaoExcluir = botaoContainer.querySelector('.botao3');

        // Botão editar
        botaoEditar.onclick = (function (id) {
            return function () {
                caixaFormularioCadastro.style.display = "none";
                limparFormulario(limparFrmCadastro);
                preencherFormulario(id);
            };
        })(tarefa.id);

        // Botão excluir
        botaoExcluir.onclick = (function (id) {
            return function () {
                // Lógica para excluir a tarefa com o ID fornecido
                excluirTarefa(id); // Supondo que exista uma função excluirTarefa
            };
        })(tarefa.id);
    });

};




function criarBotaoContainer() {
    // Cria o div com a classe 'botao-container'
    var divContainer = document.createElement("div");
    divContainer.className = "botao-container";

    // Função auxiliar para criar botões com SVG
    function criarBotao(classeBotao, texto) {
        var botao = document.createElement("button");
        botao.className = classeBotao;

        // Adiciona o SVG e o texto ao botão
        botao.appendChild(document.createTextNode(" "));

        return botao;
    }

    // Criar os três botões
    var botaoVer = criarBotao("botao1");
    var botaoEditar = criarBotao("botao2");
    var botaoApagar = criarBotao("botao3");

    // Adiciona os botões ao container
    divContainer.appendChild(botaoVer);
    divContainer.appendChild(botaoEditar);
    divContainer.appendChild(botaoApagar);

    return divContainer;

}
