package br.com.josewolf.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver navegador;
    private WebDriverWait wait;

    private By campoUsuario = By.cssSelector("[data-test='username']");
    private By password = By.cssSelector("[data-test='password']");
    private By buttonLongin = By.cssSelector("[data-test='login-button']");
    private By mensagemErro = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver navegador) {
        this.navegador = navegador;
        this.wait = new WebDriverWait(navegador, Duration.ofSeconds(5));
    }

    public void preencherUsuario(String usuario) {
        navegador.findElement(By.cssSelector("[data-test='username']")).sendKeys(usuario);
    }

    public void preencherSenha(String senha) {
        navegador.findElement(By.cssSelector("[data-test='password']")).sendKeys(senha);
    }

    public void clicarBotaoLogin(){
        navegador.findElement(buttonLongin).click();
    }

    public void realizarLogin(String usuario, String senha) {
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarBotaoLogin();
    }

    public String obterMensagemErro() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemErro)).getText();
    }
}
