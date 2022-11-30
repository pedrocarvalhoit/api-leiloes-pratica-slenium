package login;

import br.com.alura.leilao.PageObject;
import leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends PageObject {

    //Find Elemente recupera o dado de Login pelo ID do input
    //sendkeys envia o texto desejado para o campo "USERNAME"
    //Submete os dados ao login-form com SUBMIT
    //Se o login funcionar, a página atual não será mais a de login
    //Por isso se faz a verificação de url

    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    private void preencherFormularioDeLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage efetuarLogin(String username, String password) {
        this.preencherFormularioDeLogin(username, password);
        browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public String getNomeUsuarioLogado() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().contains(URL_LOGIN);
    }

    public boolean isMensagemDeLoginInvalidoVisivel() {
        return browser.getPageSource().contains("Usuário e senha inválidos");
    }


}
