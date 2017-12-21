package in.srssprojects.keximbank;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RolesPage {
	WebDriver driver;
	
	public RolesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//new roles
	@FindBy(how=How.ID, using="btnRoles")
	private WebElement newRole;
	
	public void clickNewRole() {
		this.newRole.click();
	}

}
