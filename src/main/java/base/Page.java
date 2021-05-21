package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.LoginPage;
import pages.ZohoAppPage;
import utilities.ExcelReader;
import utilities.ExtentManager;

import utilities.Utilities;

public class Page {

	public static WebDriver driver;

	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fi;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testData.xlsx");
	public ExtentReports report = new ExtentManager().getInstance();
	public static ExtentTest test;
	public static String browser;
	// public static FileInputStream fi;
	// For alert validation
	public static WebDriverWait wait;

	public static TopMenu menu; // encapsulate TopMenu here

	public Page() {

		if (driver == null) {

			try {
				fi = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fi);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			log.debug("config file loded !!");

			try {
				fi = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				OR.load(fi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("OR file loded !!");

			// Jenkins browser filter configuration
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");

			}
			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.debug("firefox launch !!");

			} else if (config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();

				// disable popup
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions option = new ChromeOptions();
				option.setExperimentalOption("prefs", prefs);
				option.addArguments("--disable-extensions");
				option.addArguments("--disable-infobars");

				driver = new ChromeDriver(option);
				log.debug("chrome launch !!");

			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("IE launch !!");

			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("nevigate to " + config.getProperty("testsiteurl"));

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);

			menu = new TopMenu(driver);

		}

	}

	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	//for click on button
		public static void click(String locator) {
			
			if(locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			} else if(locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).click();
			}else if(locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).click();
			}
			
			log.debug("Clicking on element :"+locator);
			//for test step is maintain in extent report
			test.log(LogStatus.INFO, "clicking on  "+locator);
		}
		
		//for type in textbox
		public void type(String locator,String value) {
		
			if(locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			}else if(locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			}else if(locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			}
			
			log.debug("Typing in an Element :"+locator+ "Entered value in :"+value);
			//for test step is maintain in extent report
			test.log(LogStatus.INFO, "Typing in "+ locator +"Entered value as "+value);
		}
		//for dropdown select
		static WebElement dropDown;
		public static void select(String locator,String value) {
			
			
			if(locator.endsWith("_CSS")) {
				dropDown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
			}else if(locator.endsWith("_XPATH")) {
				dropDown=driver.findElement(By.xpath(OR.getProperty(locator)));
			}else if(locator.endsWith("_ID")) {
				dropDown=driver.findElement(By.id(OR.getProperty(locator)));
			}
			
			Select select=new Select(dropDown);
			select.selectByVisibleText(value);
			
			log.debug("Selecting from an Element  :"+ locator +"Value as : "+ value);
			//for test step is maintain in extent report
			test.log(LogStatus.INFO, "selecting from dropdown "+ locator +"Value as "+value);
			
		}
		//for soft assertion every failed test cases should logged in to report
		public static void verifyEquals(String actual,String expected) throws IOException {
			try {
				
				Assert.assertEquals(actual, expected);
			}catch(Throwable t) {
				
				Utilities.captureScreenshot();
				//for reportNG
				Reporter.log("<br>"+"Verification Failure"+t.getMessage()+"</br>");
				Reporter.log("<a target=\"_blank\"href="+Utilities.screenshotName +"><img src="+ Utilities.screenshotName+" height=200 width=200></img> </a>");
				Reporter.log("<bt>");
				Reporter.log("<bt>");
				
				//for Extent report
				test.log(LogStatus.FAIL,"Varification Failed with exception.."+t.getMessage());
				test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
			}
		}
		
}
