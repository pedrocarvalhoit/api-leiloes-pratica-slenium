package login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach(){
        this.loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        this.loginPage.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos(){

        loginPage.preencheFormularioDeLogin("fulano", "pass");
        loginPage.submeteFormularioDeLogin();
        Assert.assertFalse(loginPage.certificaPaginaDeLogin());

    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos(){
        loginPage.preencheFormularioDeLogin("fulano", "245254");
        loginPage.submeteFormularioDeLogin();
        Assert.assertTrue(loginPage.certificaPaginaDeLoginComErro());
        Assert.assertTrue(loginPage.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void deveriaAcessarApenasLogado(){
        loginPage.navegaParaUrl("http://localhost:8080/leiloes/2");
        Assert.assertTrue(loginPage.certificaPaginaDeLogin());
        Assert.assertFalse(loginPage.contemTexto("Dados do leilão"));
    }
}
