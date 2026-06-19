package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistartionPage extends BasePage
{
   public AccountRegistartionPage(WebDriver driver)
   {
	   super(driver);
   }
   
   // user name
   @FindBy(xpath = "//input[@placeholder ='Username']")
   WebElement txtuserName;
   
// First Name
@FindBy(xpath = "//input[@name='firstname']")
WebElement txtfirstName;

// Last Name
@FindBy(xpath = "//input[@name='lastname']")
WebElement txtlastName;

// Email
@FindBy(xpath = "//input[@name='email']")
WebElement txtemail;

@FindBy(xpath ="//select[@id='input-country']")
WebElement txtCountry;

// Telephone
@FindBy(xpath = "//input[@name='telephone']")
WebElement txttelephone;

// Password
@FindBy(xpath = "//input[@name='password']")
WebElement txtpassword;

// Password Confirm
@FindBy(xpath = "//input[@name='confirm']")
WebElement txtconfirmPassword;

// Newsletter Subscribe - Yes
@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
WebElement newsletterYes;

// Newsletter Subscribe - No
@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
WebElement newsletterNo;

// Privacy Policy Agreement Check box
@FindBy(xpath = "//input[@name='agree']")
WebElement chkprivacyPolicyCheckbox;

// Continue/Submit Button
@FindBy(xpath = "//input[@type='submit'][@value='Continue']")
WebElement btncontinueButton;

// Alternative Continue Button (if using button tag)
@FindBy(xpath = "//button[contains(text(), 'Continue')]")
WebElement btncontinueButtonAlt;

// Submit button
@FindBy(xpath = "//button[@type='submit']")
WebElement btnsubmit;

public void setUsertName(String uname)
{
	txtuserName.sendKeys("uname");
}

public void setFirstName(String fname)
{
	txtfirstName.sendKeys("fname");
}

public void setlasttName(String lname)
{
	txtlastName.sendKeys("lname");
}
   
public void setemail(String email)
{
	txtemail.sendKeys("email");
}

public void setphone(String pnum)
{
	txttelephone.sendKeys("pnum");
}

public void setpassword(String password)
{
	txtpassword.sendKeys("password");
	
}


public void setCountry(String country)
{
	txtCountry.sendKeys("country");
	
}

public void btnsubmit()
{
	btnsubmit.click();
}

}
