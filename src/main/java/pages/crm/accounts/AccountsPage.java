package pages.crm.accounts;

import org.openqa.selenium.By;

import base.Page;

public class AccountsPage extends Page{

	public CreateAccountPage goToCreateAccountPage() {
		driver.findElement(By.xpath("//*[@id=\"table_row_1\"]/lyte-td[3]/span[1]/link-to/button")).click();
		//click("createAccountBtn_XPATH");
		//driver.findElement(By.xpath("[@id=\"mainMenuTabDiv\"]/crm-menu/div[1]/crm-tab/div[2]/div[4]/a")).click();
		//driver.findElement(By.xpath("//*[@id=\"mainMenuTabDiv\"]/crm-menu/div[1]/crm-tab/div[2]/div")).click();
		//*[@id="mainMenuTabDiv"]/crm-menu/div[1]/crm-tab/div[2]/div
		
		
		return new CreateAccountPage();
	}
	public void goToImportAccount() {
		
	}
	
}
