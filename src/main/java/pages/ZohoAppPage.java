package pages;

import org.openqa.selenium.By;

import base.Page;
import pages.crm.CRMHomePage;

public class ZohoAppPage extends Page {

	
	public CRMHomePage goToCRM() {
		//driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[6]/div/a/div")).click();
		
		click("crmLinkClcik_XPATH");
		return new CRMHomePage();
	}
	
	public void goToBooks() {
		
	}
	public void goTocampaigns() {
		
	}
}
