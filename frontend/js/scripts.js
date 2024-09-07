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

// Atualiza lista de tarefas com infomações obtidas do localstorage, sempre que a página é carregada completamente
document.addEventListener("DOMContentLoaded", function () {
    listarTarefas();
});


function salvarNoLocalStorage(chave, dados) {
    try {
        localStorage.setItem(chave, JSON.stringify(dados));
    } catch (erro) {
        console.error("Erro ao salvar dados no localStorage:", erro);
        alert("Ocorreu um erro ao salvar os dados. Tente novamente mais tarde.");
    }
}

function obterDoLocalStorage(chave) {
    try {
        let dados = localStorage.getItem(chave);
        return JSON.parse(dados) || [];
    } catch (erro) {
        console.error("Erro ao obter dados do localStorage:", erro);
        alert("Ocorreu um erro ao recuperar os dados. Tente novamente mais tarde.");
        return [];
    }
}


function limparFormulario(formulario) {
    formulario.reset();
};

let limparFrmCadastro = formularioCadastro;
let limparFrmEdicao = formularioEdicao;


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

// Definição e campos do formulário de edição para validação
const camposEdicao = [
    { nome: "nomeEdit", mensagem: "O campo Nome está vazio." },
    { nome: "descricaoEdit", mensagem: "O campo Descrição está vazio." },
    { nome: "dataEdit", mensagem: "O campo Data está vazio." },
    { nome: "categoriaEdit", mensagem: "O campo Categoria está vazio." },
    { nome: "prioridadeEdit", mensagem: "O campo Prioridade está vazio." },
    { nome: "statusEdit", mensagem: "O campo Status está vazio." }
];


function gerarId() {

    let listaTarefas = obterDoLocalStorage("minhaTarefa");
    let idGlobal;

    if (listaTarefas.length > 0) {
        let ids = listaTarefas.map(tarefa => tarefa.id); // Extrair os IDs usando map
        let maiorId = Math.max(...ids); // Pega o maior valor dentro doo array ... < é chamado de operador spread, separa todos os itens de um array 1 por 1
        idGlobal = maiorId + 1;
    } else {
        idGlobal = 1;
    }

    return idGlobal;
}

function cadastrarTarefa() {
    let listaTarefas = obterDoLocalStorage("minhaTarefa");

    let id = gerarId();
    let nome = frmCadastro.nome.value;
    let descricao = frmCadastro.descricao.value;
    let data = frmCadastro.data.value;
    let categoria = frmCadastro.categoria.value;
    let prioridade = frmCadastro.prioridade.value;
    let status = frmCadastro.status.value;

    const tarefa = { id: id, nome: nome, descricao: descricao, data: data, categoria: categoria, prioridade: prioridade, status: status };

    listaTarefas.push(tarefa);

    salvarNoLocalStorage("minhaTarefa", listaTarefas);

    alert("Tarefa cadastrada com sucesso!");

}

botaoAdicionar.onclick = function () {

    if (validarDados(camposCadastro, frmCadastro)) {

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

    let listaTarefas = obterDoLocalStorage("minhaTarefa");
    // Localiza a tarefa com base no ID
    let elementoTarefa = listaTarefas.find(tarefa => tarefa.id === idTarefa);

    if (elementoTarefa) {

        caixaFormularioEdicao.style.display = "initial";

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

    let listaTarefas = obterDoLocalStorage("minhaTarefa");

    // Localiza o índice da tarefa no array
    let indiceTarefa = listaTarefas.findIndex(tarefa => tarefa.id === idTarefa);

    if (indiceTarefa !== -1) {

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

        salvarNoLocalStorage("minhaTarefa", listaTarefas);

        alert("Tarefa editada com sucesso!");

    } else {

        alert("Tarefa não pode ser editada, tente novamente!");

    }
}

botaoSalvar.onclick = function () {
    let idTarefa = parseInt(document.querySelector('input[name="id"]').value);

    if (validarDados(camposEdicao, frmEdicao)) {
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



function excluirTarefa(idTarefa) {

    let listaTarefas = obterDoLocalStorage("minhaTarefa");

    let indiceTarefa = listaTarefas.findIndex(tarefa => tarefa.id === idTarefa);

    if (indiceTarefa !== -1) {

        let resposta = confirm('Tem certeza que deseja remover a tarefa?');

        if (resposta === true) {

            listaTarefas.splice(indiceTarefa, 1);

            salvarNoLocalStorage("minhaTarefa", listaTarefas);
        }

        listarTarefas();
    } else {

        alert("Tarefa não pode ser removida, tente novamente!");
    }
}



function listarTarefas() {

    let dados = obterDoLocalStorage("minhaTarefa");

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

        botaoEditar.onclick = (function (id) {
            return function () {
                caixaFormularioCadastro.style.display = "none";
                limparFormulario(limparFrmCadastro);
                preencherFormulario(id);
            };
        })(tarefa.id);

        botaoExcluir.onclick = (function (id) {
            return function () {
                excluirTarefa(id);
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

        // Adiciona o SVG ao botão
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
