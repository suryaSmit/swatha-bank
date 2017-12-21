package in.srssprojects.keximbank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminHomePage {
	WebDriver driver;
	
	public AdminHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//branches button
	public WebElement branches() {
	return	driver.findElement(By.xpath("//a[@href='admin_banker_master.aspx']"));
	}
	
	//roles button
	public WebElement roles() {
		return driver.findElement(By.xpath("//a[@href='Admin_Roles_details.aspx']"));
	}
	
	//employee button
	public WebElement employee() {
		return driver.findElement(By.xpath("//a[@href='Admin_Emp_details.aspx']"));
	}
	
	//users button
	public WebElement users() {
		return driver.findElement(By.xpath("//a[@href='userdetails.aspx']"));
	}
	
	//logout button
	public WebElement logout() {
		return driver.findElement(By.xpath("//a[@href='home.aspx']"));
	}
	
	//click branches
	public void clickBranches() {
		this.branches().click();
	}
	
	//click roles
	public void clickRoles() {
		this.roles().click();
	}
	
	//click employee
	public void clickEmployee() {
		this.employee().click();
	}
	
	//click user
	public void clickUser() {
		this.users().click();
	}
	
	//clikc logout
	public void clickLogout() {
		this.logout().click();
	}
	
	
	
	
	
	
	
	

}
