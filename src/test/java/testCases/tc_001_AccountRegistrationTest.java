package testCases;

import org.testng.annotations.Test;

import pageObjects.AccountRegistartionPage;
import pageObjects.HomePage;

public class tc_001_AccountRegistrationTest extends baseclass
{
   
    @Test(groups = {"sainity","Master"})
    public void UserRegistration() 
    {
    	logger.info("******** Started Test cases *****");
    	
    	HomePage hp = new HomePage(driver);
    	//hp.clickMyaccount();
    	hp.clickResister();
    	
    	AccountRegistartionPage repage = new AccountRegistartionPage(driver);
    	
    	repage.setUsertName("dsingh12");
    	repage.setFirstName("Dev");
    	repage.setlasttName("Singh");
    	repage.setemail("abc@xyz.com");
    	repage.setCountry("Ind");
    	repage.setpassword("admin123@");
    	repage.btnsubmit();
    	logger.info("******** End of Test cases *****");
    	
    }
    
    
}
