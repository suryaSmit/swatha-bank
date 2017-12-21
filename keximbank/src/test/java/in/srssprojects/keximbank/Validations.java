package in.srssprojects.keximbank;

import org.openqa.selenium.WebElement;

public class Validations {
	
	//by comparing text
	public static boolean isTextEqualsTo(String actualText,String expectedText) {
		return actualText.equals(expectedText);
	}
	
	//by checking visiblility of an element
	public static boolean isElementPresent(WebElement ele) {
		return ele.isDisplayed();
	}
	

}
