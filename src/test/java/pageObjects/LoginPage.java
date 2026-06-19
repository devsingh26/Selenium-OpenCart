package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{

	// Constructor 
		public LoginPage(WebDriver driver)
		{
			super(driver);
		}
		
	@FindBy(xpath = "//input[@name='email'")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@name='password'")
	WebElement txtpassword;
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-lg btn-block visible-xs-block'")
	WebElement btnlogin;
	
	
	public void setemail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void setpassword (String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void clicklogin()
	{
		btnlogin.click();
	}
	
}
