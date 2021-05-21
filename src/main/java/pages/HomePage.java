package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.Page;

public class HomePage extends Page {

	
	public void goToSupport() {
		
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/a[2]")).click();
	}
	public LoginPage goToSignIn() {
		
		
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/a[4]")).click();
		//click("signinLink_XPATH");
		return new LoginPage();
	}
	
	public void goToCustomers() {
		
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/a[1]")).click();
	}
	public void goToAccessYourApps() {
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/a[6]")).click();
	}
	
	public void goTocontactSales() {
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/a[3]")).click();
	}
	
	public void validateFooterLinks() {
		
	}
}
