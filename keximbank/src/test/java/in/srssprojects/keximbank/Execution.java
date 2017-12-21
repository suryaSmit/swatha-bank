package in.srssprojects.keximbank;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Execution {

	WebDriver obj;
	EventFiringWebDriver driver;
	KeximHomePage keximHomePageObj;
	AdminHomePage adminHomePageObj;
	BranchDetailsPage branchDetailsPage;
	RolesPage rolesPageObj;
	RoleCreationPage roleCreationPageObj;

	public String[][] getExcelData(String fileName, String sheetName) {
		Excel excel = new Excel();
		excel.setExcel(fileName, sheetName);
		int nor = excel.rowCount();
		int noc = excel.columnCount();
		String[][] data = new String[nor-1][noc];
		for (int r = 1; r < nor; r++) {
			for (int c = 0; c < noc; c++) {
				data[r-1][c] = excel.readData(r, c);
			}
		}
		return data;
	}

	@DataProvider(name = "role data")
	public Object[][] getRoleData() {
		return getExcelData("input data.xls", "roles");
	}
	
	@DataProvider(name="branch data")
	public Object[][] getBranchData(){
		return getExcelData("input data.xls", "branches");
	}

	@BeforeMethod(groups= {"data drivern", "roles","branches"})
	public void login() {
		// login
		keximHomePageObj.fillUsername(driver, "Admin");
		keximHomePageObj.fillPassword(driver, "Admin");
		keximHomePageObj.clickLoginButton(driver);
		boolean result = Validations.isElementPresent(adminHomePageObj.branches());
		assertTrue(result);
	}

	@Test(priority = 0, groups= {"branches"})
	public void branchSearch() {
		// branch search
		adminHomePageObj.clickBranches();
		branchDetailsPage.selectCountry("UK");
		branchDetailsPage.selectState("England");
		branchDetailsPage.selectCity("LONDON");
		branchDetailsPage.clickSearch();
		Reporter.log("brach search method");
		String actualText = driver.findElement(By.xpath("/html/body/div/form/div[1]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[5]")).getText();
		Reporter.log("actual text is: "+actualText+"\n"+"expected text is: "+"LONDON");
		boolean result = Validations.isTextEqualsTo(actualText, "LONDON");
		assertTrue(result);
	}

	@Test(priority = 1, groups= {"branches"})
	public void branchSearchClear() throws InterruptedException {
		adminHomePageObj.clickBranches();
		branchDetailsPage.selectCountry("UK");
		branchDetailsPage.selectState("England");
		branchDetailsPage.selectCity("LONDON");
		branchDetailsPage.clickClear();
		Reporter.log("brach search clear");
		String actualText = branchDetailsPage.getCountryOption();
		Reporter.log("actual text is: "+actualText+"\n"+"expected text is: "+"ALL");
		boolean result = Validations.isTextEqualsTo(actualText, "ALL");
		assertTrue(result);
//		assertEquals(actualText, "ALL");
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void createNewRole() {
		Excel excel = new Excel();
		excel.setExcel("input data.xls", "roles");
		int nor = excel.rowCount();
		int noc = excel.columnCount();
		for (int r = 1; r < nor; r++) {
			String roleName = excel.readData(r, 0);
			String roleType = excel.readData(r, 1);
			adminHomePageObj.clickRoles();
			rolesPageObj.clickNewRole();
			roleCreationPageObj.fillRoleName(roleName);
			roleCreationPageObj.selectRoleType(roleType);
			roleCreationPageObj.clickSubmit();
			System.out.println(driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
		}

	}
	
	@Test(priority=3, dataProvider="role data", groups= {"data drivern","roles"})
	public void createNewRoleUsingDP(String roleName, String roleType) {
		adminHomePageObj.clickRoles();
		rolesPageObj.clickNewRole();
		roleCreationPageObj.fillRoleName(roleName);
		roleCreationPageObj.selectRoleType(roleType);
		roleCreationPageObj.clickSubmit();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}
	
	@Test(priority=4, dataProvider="role data", groups= {"data drivern","roles"})
	public void resetNewRoleCreationFormUsingDP(String roleName, String roleType) {
		adminHomePageObj.clickRoles();
		rolesPageObj.clickNewRole();
		roleCreationPageObj.fillRoleName(roleName);
		roleCreationPageObj.selectRoleType(roleType);
		roleCreationPageObj.clickReset();
	}
	
	@Test(priority =5, dataProvider="branch data", groups= {"data drivern", "branches"})
	public void createNewBranchWithDP(String branchName, String add1, String zcode, String country, String state, String city) {
		System.out.println(branchName+"\t"+add1+"\t"+zcode+"\t"+country+"\t"+state+"\t"+city);
	}
	

	@AfterMethod(groups= {"data drivern", "roles", "branches"})
	public void logout() {
		adminHomePageObj.clickLogout();

	}

}
