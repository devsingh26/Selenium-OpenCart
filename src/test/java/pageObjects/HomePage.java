package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	WebDriver driver;
	// Constructor 
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath= "//a[@class =\"btn btn-link navbar-btn\"]")
WebElement lnkMyaccount;

@FindBy(xpath ="//a[@class =\"btn btn-black navbar-btn\"]")
WebElement lnkResister;


public void clickMyaccount()
{
	lnkMyaccount.click();
}

public void clickResister()
{
	lnkResister.click();
}
}
