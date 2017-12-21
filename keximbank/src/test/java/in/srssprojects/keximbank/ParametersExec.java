package in.srssprojects.keximbank;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class ParametersExec extends Execution{
	@Parameters({"url"})
	@BeforeClass
	public void launchBrower(String url) {
		System.setProperty("webdriver.gecko.driver", "/Users/surya/Documents/selenium/softwares/geckodriver");
		obj = new FirefoxDriver();
		driver = new EventFiringWebDriver(obj);
		Listener listener = new Listener();
		driver.register(listener);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		keximHomePageObj = new KeximHomePage();
		adminHomePageObj = new AdminHomePage(driver);
		branchDetailsPage = new BranchDetailsPage(driver);
		rolesPageObj = new RolesPage(driver);
		roleCreationPageObj = new RoleCreationPage(driver);
	}
	
	@AfterClass
	public void closeBroser() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}


}
