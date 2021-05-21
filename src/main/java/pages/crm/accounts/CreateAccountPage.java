package pages.crm.accounts;

import org.openqa.selenium.By;

import base.Page;

public class CreateAccountPage extends Page {

	public void CreateAccount(String accountName) {
		driver.findElement(By.xpath("//*[@id=\"Crm_Accounts_ACCOUNTNAME\"]")).sendKeys(accountName);
		//driver.findElement(By.cssSelector("#Crm_Accounts_ACCOUNTNAME")).sendKeys(accountName);
		
		//type("accountName_XPATH", accountName);
		//driver.findElement(By.xpath("//*[@id=\"table_row_1\"]/lyte-td[3]/span[1]/link-to/button")).click();
		//*[@id="table_row_1"]/lyte-td[3]/span[1]/link-to/button
		
		
		}
}
