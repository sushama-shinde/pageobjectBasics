package base;

import org.testng.annotations.AfterSuite;

public class BaseTest {

	@AfterSuite
	public static void quit() {
		
			//Page.driver.quit();
		
	}
}
