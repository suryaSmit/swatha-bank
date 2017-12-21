package in.srssprojects.keximbank;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RoleCreationPage {
	WebDriver driver;

	public RoleCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// role name
	@FindBy(how=How.ID, using="txtRname")
	private WebElement roleName;

	// role description
	@FindBy(how=How.ID, using="txtRDesc")
	private WebElement roleDesc;

	// role type
	@FindBy(how=How.ID, using="lstRtypeN")
	private WebElement roleType;

	// submit
	@FindBy(how=How.ID, using="btninsert")
	private WebElement submit;
	
	// reset
	@FindBy(how=How.ID, using="Btn_Reset")
	private WebElement reset;

	// cancle
	@FindBy(how=How.ID, using="Btn_cancel")
	private WebElement cancel;
	
	//fill role name
	public void fillRoleName(String roleName) {
		this.roleName.sendKeys(roleName);
	}
	//file role description
	public void fillRoleDescription(String roleDescription) {
		this.roleDesc.sendKeys(roleDescription);
	}
	
	//select role type
	public void selectRoleType(String roleType) {
		new Select(this.roleType).selectByVisibleText(roleType);
	}
	
	//click submit
	public void clickSubmit() {
		this.submit.click();
	}
	
	//click reset
	public void clickReset() {
		this.reset.click();
	}
	
	//click cancel
	public void clickCancel() {
		this.cancel.click();
	}
}
