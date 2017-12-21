package in.srssprojects.keximbank;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class MultiBrowser extends Execution {
	@Parameters({"driverPath", "brName" })
	@BeforeClass
	public void launchBrower(String driverPath, String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver");
			obj = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
			obj = new ChromeDriver();
		}
		driver = new EventFiringWebDriver(obj);
		Listener listener = new Listener();
		driver.register(listener);
		driver.get("http://srssprojects.in");
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
