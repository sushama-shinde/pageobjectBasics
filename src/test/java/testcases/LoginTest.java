package testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.ZohoAppPage;
import utilities.Utilities;


public class LoginTest extends BaseTest{

	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void loginTest(Hashtable<String, String> data) {
		HomePage home=new HomePage();
		LoginPage lPage= home.goToSignIn();
		
		ZohoAppPage zp=lPage.doLogin(data.get("username"), data.get("password"));
	}
}
