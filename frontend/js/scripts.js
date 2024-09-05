// Abrir formulário para adicionar nova tarefa
document.getElementById("botao-adicionar-nova-tarefa").onclick = function () {
    document.getElementById("caixa-branca").style.display = "initial";
};

// Atualiza lista de tarefas com infomações do localstorage, sempre que a página é carregada completamente
document.addEventListener("DOMContentLoaded", function () {
    listarTarefas();
})

// Validação de dados do formulário
function validarDados() {

    const campos = [
        { nome: "nome", mensagem: "O campo Nome está vazio." },
        { nome: "descricao", mensagem: "O campo Descrição está vazio." },
        { nome: "data", mensagem: "O campo Data está vazio." },
        { nome: "categoria", mensagem: "O campo Categoria está vazio." },
        { nome: "prioridade", mensagem: "O campo Prioridade está vazio." },
        { nome: "status", mensagem: "O campo Status está vazio." }
    ];

    for (const campo of campos) {
        let valor = frmCadastro[campo.nome].value.trim();
        if (!valor) {
            alert(campo.mensagem);
            frmCadastro[campo.nome].focus()
            return false;
        }
    }

    return true;
};

// Define variáveis para tratamento e geração de estrutura
var tabela = document.getElementById("tabela-tarefas");
var tabelaItens = document.getElementById("tabela-itens");

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

};

function listarTarefas() {

    let dados = JSON.parse(localStorage.getItem("minhaTarefa")) || [];

    // Limpa lista de itens antes de preencher novamente
    tabelaItens.innerHTML = "";

    dados.forEach(tarefa => {

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

    });

}

function limparFormulario() {
    let formulario = document.getElementById("frmCadastro")
    formulario.reset();
}

document.getElementById("adicionar").onclick = function () {

    if (validarDados()) {

        document.getElementById("caixa-branca").style.display = "none";
        cadastrarTarefa();
        listarTarefas();

    };
};

document.getElementById("limpar").onclick = function () {

    let resposta = confirm('Tem certeza que deseja limpar o formulário?');

    if (resposta === true) {
        limparFormulario();
    }
};

document.getElementById("cancelar").onclick = function () {

    let resposta = confirm('Tem certeza que deseja fechar o formulário?');

    if (resposta === true) {
        limparFormulario();
        document.getElementById("caixa-branca").style.display = "none";
    }

};


function criarBotaoContainer() {
    // Cria o div com a classe 'botao-container'
    var divContainer = document.createElement("div");
    divContainer.className = "botao-container";

    // Função auxiliar para criar botões com SVG
    function criarBotao(classeBotao, svgConteudo, texto) {
        var botao = document.createElement("button");
        botao.className = classeBotao;

        // Adiciona o SVG
        var svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        svg.setAttribute("xmlns", "http://www.w3.org/2000/svg");
        svg.setAttribute("width", "16");
        svg.setAttribute("height", "16");
        svg.setAttribute("viewBox", "0 0 24 24");
        svg.setAttribute("fill", "none");
        svg.setAttribute("stroke", "#fff");
        svg.setAttribute("stroke-width", "2");
        svg.setAttribute("stroke-linecap", "round");
        svg.setAttribute("stroke-linejoin", "round");

        // Adiciona o conteúdo do SVG
        svg.innerHTML = svgConteudo;

        // Adiciona o SVG e o texto ao botão
        botao.appendChild(svg);
        botao.appendChild(document.createTextNode(" "));

        return botao;
    }

    // Criar os três botões
    var botaoVer = criarBotao("botao1",
        '<path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path>' +
        '<circle cx="12" cy="12" r="3"></circle>'
    );

    var botaoEditar = criarBotao("botao2",
        '<path d="M20 14.66V20a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h5.34"></path>' +
        '<polygon points="18 2 22 6 12 16 8 16 8 12 18 2"></polygon>'
    );

    var botaoApagar = criarBotao("botao3",
        '<polyline points="3 6 5 6 21 6"></polyline>' +
        '<path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>' +
        '<line x1="10" y1="11" x2="10" y2="17"></line>' +
        '<line x1="14" y1="11" x2="14" y2="17"></line>'
    );

    // Adiciona os botões ao container
    divContainer.appendChild(botaoVer);
    divContainer.appendChild(botaoEditar);
    divContainer.appendChild(botaoApagar);

    return divContainer;

}

