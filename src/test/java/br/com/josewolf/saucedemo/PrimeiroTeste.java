package br.com.josewolf.saucedemo;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    private Actions actions;

    @BeforeEach
    void setUp() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");

        navegador = new FirefoxDriver(firefoxOptions);
        navegador.manage().window().maximize();
        wait = new WebDriverWait(navegador, Duration.ofSeconds(5));

        navegador.get("https://www.saucedemo.com/");
    }

    @Test
    void deveFazerLoginComSucesso(){
        WebElement campoUsuario =  navegador.findElement(By.cssSelector("[data-test='username']"));
        WebElement campoPassword =  navegador.findElement(By.cssSelector("[data-test='password']"));

        campoUsuario.sendKeys("standard_user");
        campoPassword.sendKeys("secret_sauce");

        WebElement botaoLogin = navegador.findElement(By.cssSelector("[data-test='login-button']"));

        botaoLogin.click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        assertEquals("https://www.saucedemo.com/inventory.html", navegador.getCurrentUrl());
    }

    @AfterEach
    void tearDown() {
        if (navegador != null) {
            navegador.quit();
        }
    }
}

