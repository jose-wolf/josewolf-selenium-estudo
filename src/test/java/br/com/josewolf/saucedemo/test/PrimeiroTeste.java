package br.com.josewolf.saucedemo;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimeiroTeste {

    private WebDriver navegador;
    private WebDriverWait wait;
    private String password = "secret_sauce";

    @BeforeEach
    void setUp() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");

        navegador = new FirefoxDriver(firefoxOptions);
        navegador.manage().window().maximize();
        wait = new WebDriverWait(navegador, Duration.ofSeconds(5));

        navegador.get("https://www.saucedemo.com/");
    }

    @ParameterizedTest
    @ValueSource(strings = {"standard_user", "error_user", "visual_user"})
    void deveFazerLoginComSucessoStandardUser(String usuario){
        LoginPage loginPage = new LoginPage(navegador);
        loginPage.realizarLogin(usuario, password);

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        assertEquals("https://www.saucedemo.com/inventory.html", navegador.getCurrentUrl());
    }

    @Test
    void loginLockedUser(){
        LoginPage loginPage = new LoginPage(navegador);
        loginPage.realizarLogin("locked_out_user", password);

        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.obterMensagemErro());
        assertEquals("https://www.saucedemo.com/", navegador.getCurrentUrl());

    }

    @AfterEach
    void tearDown() {
        if (navegador != null) {
            navegador.quit();
        }
    }
}

