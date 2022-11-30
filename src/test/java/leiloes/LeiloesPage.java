package leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage  extends PageObject{

    //Find Elemente recupera o dado de Login pelo ID do input
    //sendkeys envia o texto desejado para o campo "USERNAME"
    //Submete os dados ao login-form com SUBMIT
    //Se o login funcionar, a página atual não será mais a de login
    //Por isso se faz a verificação de url

    private static final String URL_LIST = "http://localhost:8080/leiloes";
    private static final String URL_FORM = "http://localhost:8080/leiloes/new";

    public LeiloesPage(WebDriver browser) {
        super(browser);
    }

    //Direciona para página CadastroDeLeilao
    public CadastroLeilaoPage carregarFormulario() {
        this.browser.navigate().to(URL_FORM);
        return new CadastroLeilaoPage(browser);
    }

    /** Precisa recuperar a última coluna da tabela de leilões que está no index --
     * A navegação é pelo ID, e depois pelo seletor de CSS*/
    public boolean isLeilaoCadastrado(String nome, String valorInicial, String dataAbertura) {
        WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome) && colunaDataAbertura.getText().equals(dataAbertura) && colunaValorInicial.getText().equals(valorInicial);
    }

    public boolean isPaginaAtual() {
        return this.browser.getCurrentUrl().equals(URL_LIST);
    }
}
