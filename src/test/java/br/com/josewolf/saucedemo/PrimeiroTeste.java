package br.com.josewolf.saucedemo;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimeiroTeste {

    private WebDriver navegador;
    private WebDriverWait wait;

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
        WebElement campoUsuario =  navegador.findElement(By.cssSelector("[data-test='username']"));
        WebElement campoPassword =  navegador.findElement(By.cssSelector("[data-test='password']"));

        campoUsuario.sendKeys(usuario);
        campoPassword.sendKeys("secret_sauce");

        WebElement botaoLogin = navegador.findElement(By.cssSelector("[data-test='login-button']"));

        botaoLogin.click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        assertEquals("https://www.saucedemo.com/inventory.html", navegador.getCurrentUrl());
    }

    @Test
    void loginLockedUser(){
        WebElement campoUsuario = navegador.findElement(By.cssSelector("[data-test='username']"));
        WebElement campoPassword =  navegador.findElement(By.cssSelector("[data-test='password']"));

        campoUsuario.sendKeys("locked_out_user");
        campoPassword.sendKeys("secret_sauce");

        WebElement botaoLogin = navegador.findElement(By.cssSelector("[data-test='login-button']"));

        botaoLogin.click();
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[data-test='error']"))
        );

        assertEquals("Epic sadface: Sorry, this user has been locked out.", errorMessage.getText());
    }

    @AfterEach
    void tearDown() {
        if (navegador != null) {
            navegador.quit();
        }
    }
}

