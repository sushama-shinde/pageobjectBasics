package testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import base.Page;
import pages.ZohoAppPage;
import pages.crm.CRMHomePage;
import pages.crm.accounts.AccountsPage;
import pages.crm.accounts.CreateAccountPage;
import utilities.Utilities;



public class CreateAccountTest {

	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void createAccountTest(Hashtable<String,String> data) {
		
		ZohoAppPage zp=new ZohoAppPage();
		CRMHomePage cp=zp.goToCRM();
		AccountsPage accPage=Page.menu.goToAccount();	
		CreateAccountPage cap=accPage.goToCreateAccountPage();
		cap.CreateAccount(data.get("accountname"));
		
	}
}
