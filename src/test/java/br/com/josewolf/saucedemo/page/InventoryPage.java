package br.com.josewolf.saucedemo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver navegador;
    private WebDriverWait wait;

    private By addBackpackCart = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private By addOnesieCart = By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']");
    private By addBikeLightCart = By.cssSelector("[data-test='inventory-item-name']");
    private By clickImage = By.cssSelector("[data-test='inventory-item-sauce-labs-backpack-img']");
    private By facebookLink = By.cssSelector("[data-test='inventory-item-sauce-labs-backpack-img']");
    private By filter = By.cssSelector("[data-test='inventory-item-sauce-labs-backpack-img']");
    private By backProduct = By.cssSelector("[data-test='back-to-products']");

    public ProductPage(WebDriver navegador) {
        this.navegador = navegador;
        this.wait = new WebDriverWait(navegador, Duration.ofSeconds(5));
    }

    public void addItemToCart(){
        navegador.findElement(addBackpackCart).click();
        navegador.findElement(addOnesieCart).click();
        navegador.findElement(addBikeLightCart).click();
    }

    public void itemDescription(){
        navegador.findElement(clickImage).click();
    }

    public void backToProductPage(){
        navegador.findElement(backProduct).click();
    }

    public void applyFilter(){

    }

    public void visitFacebook(){
        navegador.findElement(facebookLink).click();
    }

}
