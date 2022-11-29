package login;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    //Find Elemente recupera o dado de Login pelo ID do input
    //sendkeys envia o texto desejado para o campo "USERNAME"
    //Submete os dados ao login-form com SUBMIT
    //Se o login funcionar, a página atual não será mais a de login
    //Por isso se faz a verificação de url

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_LEILAO = "http://localhost:8080/leiloes/2";
    private static final String URL_ERROLOGIN = "http://localhost:8080/login?error";
    private WebDriver browser;

    public LoginPage(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    public void fechar() {
        this.browser.quit();
    }

    public void preencheFormularioDeLogin(String username, String passWord){
        this.browser.findElement(By.id("username")).sendKeys(username);
        this.browser.findElement(By.id("password")).sendKeys(passWord);
    }

    public void submeteFormularioDeLogin(){
        this.browser.findElement(By.id("login-form")).submit();
    }

    public boolean certificaPaginaDeLogin(){
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public boolean certificaPaginaDeLeiloes(){
        return browser.getCurrentUrl().equals(URL_LEILAO);
    }

    public boolean certificaPaginaDeLoginComErro() {
        return browser.getCurrentUrl().equals(URL_ERROLOGIN);
    }

    public void navegaParaUrl(String url){
        this.browser.navigate().to(url);
    }

    public boolean contemTexto(String dadosInformados) {
        //Recupera o conteúdo da página
        return browser.getPageSource().contains(dadosInformados);
    }


}
