package leiloes;

import login.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTeste {

    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastroDeLeilao;

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaDeLogin = new LoginPage();
        this.paginaDeLeiloes = paginaDeLogin.efetuarLogin("fulano", "pass");
        this.paginaDeCadastroDeLeilao = paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.fechar();
        this.paginaDeCadastroDeLeilao.fechar();
    }

    /** Para cadastrar um novo leilão, é preciso entrar na página de login
     * e só depois de efetuar o login, acessar novo leilão
     * Para os asserts, criar variáveis para os dados de cada campo*/
    @Test
    public void deveriaCadastrarLeilao() {
        //Variáveis para cadastro do leilão
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nomeLeilao = "Leilao do dia " + hoje;
        String valorInicial = "500.00";
        //Cadastra o leilão e submete formulário e retorna para página de leilão
        this.paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao(nomeLeilao, valorInicial, hoje);
        //Certifica leilão cadastrado
        Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nomeLeilao, valorInicial, hoje));
    }

    @Test
    public void deveriaExecutarValidacaoAoCadastrarLeilaoComDadosInvalidos() {
        this.paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao("", "", "");

        Assert.assertFalse(paginaDeCadastroDeLeilao.isPaginaAtual());
        Assert.assertTrue(paginaDeLeiloes.isPaginaAtual());
        Assert.assertTrue(paginaDeCadastroDeLeilao.isMensagensDeValidacaoVisiveis());
    }

}
