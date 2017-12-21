package in.srssprojects.keximbank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeximHomePage {
	
	//username
	public WebElement username(WebDriver driver) {
		return driver.findElement(By.id("txtuId"));
	}
	
	//password
	public WebElement password(WebDriver driver) {
		return driver.findElement(By.id("txtPword"));
	}
	
	//login button
	public WebElement loginButton(WebDriver driver) {
		return driver.findElement(By.id("login"));
	}
	
	//fill username
	public void fillUsername(WebDriver driver, String username) {
		this.username(driver).sendKeys(username);
	}
	//fill password
	public void fillPassword(WebDriver driver, String password) {
		this.password(driver).sendKeys(password);
	}
	//click login button
	public void clickLoginButton(WebDriver driver) {
		this.loginButton(driver).click();
	}

}
