package pages;

import org.openqa.selenium.By;

import base.Page;

public class LoginPage extends Page {

	
	public ZohoAppPage doLogin(String userName,String password) {
		
		type("loginId_ID",userName);
		
		click("IdNextBtn_ID");
		
		type("password_ID",password);
		
		click("signInBtn_XPATH");
		
		return new ZohoAppPage();
	}
}
