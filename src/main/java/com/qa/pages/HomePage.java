package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Logout method
    public void logout() {
        driver.findElement(By.className("oxd-userdropdown-tab")).click(); // profile menu
        driver.findElement(By.xpath("//a[text()='Logout']")).click();    // Logout link
    }

    public String getLoginPageTitle() {
        return driver.getTitle();
    }
}
