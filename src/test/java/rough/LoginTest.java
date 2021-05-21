package rough;
import org.openqa.selenium.WebDriver;

import base.Page;
import pages.HomePage;
import pages.LoginPage;
import pages.ZohoAppPage;
import pages.crm.CRMHomePage;
import pages.crm.accounts.AccountsPage;
import pages.crm.accounts.CreateAccountPage;

public class LoginTest {

	static WebDriver driver;
	public static void main(String[] args) {
		
		
		HomePage home=new HomePage();
		LoginPage lPage= home.goToSignIn();
		
		ZohoAppPage zp=lPage.doLogin("mylearning1311@gmail.com", "LearningTesting");			
		CRMHomePage cp=zp.goToCRM();
		AccountsPage accPage=Page.menu.goToAccount();	
		CreateAccountPage cap=accPage.goToCreateAccountPage();
		cap.CreateAccount("sushama");
		
	}
}
