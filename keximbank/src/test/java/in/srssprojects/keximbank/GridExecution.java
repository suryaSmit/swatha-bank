package in.srssprojects.keximbank;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class GridExecution extends Execution{
	
	
	@Parameters({"brName","platform","nodeURL"})
	@BeforeClass
	public void launchBrowser(String brname, String platform, String nodeURL) throws Exception{
		DesiredCapabilities caps = new DesiredCapabilities();
		if(platform.equals("mac")) {
			caps.setPlatform(Platform.MAC);
		}
		if(platform.equals("linux")) {
			caps.setPlatform(Platform.LINUX);
		}
		if(brname.equals("firefox")) {
			caps = DesiredCapabilities.firefox();
		}
		if(brname.equals("chrome")) {
			caps = DesiredCapabilities.chrome();
		}
		obj = new RemoteWebDriver(new URL(nodeURL), caps);
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
	public void closeBrowser() {
		driver.close();
	}

}
