package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.crm.accounts.AccountsPage;

/*every page has top menu so that we can not extends page class here
 
 TopMenu ISA Page ---Inheritance
 
 HomePage HASA TopMenu ---encapsulation
 AccountPage HASA TopMenu
 Page HASA TopMenu

*/

public class TopMenu  {

	WebDriver driver;
	public TopMenu(WebDriver driver) {
		this.driver=driver;
	}
	public void goToLeads() {

	}

	public void goToContacts() {

	}
	public AccountsPage goToAccount() {
		
		driver.findElement(By.xpath("//*[@id=\"mainMenuTabDiv\"]/crm-menu/div[1]/crm-tab/div[2]/div[4]/a")).click();
		//Page.click("accountTabs");
		
		return new AccountsPage();
	}
	public void goToDeals() {

	}

	public void goToActivities() {

	}

	public void goToReports() {

	}
}
