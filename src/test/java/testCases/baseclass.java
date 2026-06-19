package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

//import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class baseclass 
{
	 public static WebDriver driver;
	 public Logger logger;
	 public Properties p;
	    @SuppressWarnings("deprecation")
		@BeforeClass(groups = {"sainity","Master","Regression"})
	    @Parameters({"os","browser"})
		public void setup(String os, String br) throws IOException
	     {
	    	// loading logger xml file
		    logger = LogManager.getLogger(this.getClass()); 
		    
		    // loading config.properties file
		    
	    	FileReader file = new FileReader("./src//test//resources/config.properties");
	    	p = new Properties();
	    	p.load(file);
	    	
	    	
		 // Set Chrome options
	        ChromeOptions options = new ChromeOptions();

	        // Example: Pretend to be a normal Chrome browser
	        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
	                             "AppleWebKit/537.36 (KHTML, like Gecko) " +
	                             "Chrome/123.0.0.0 Safari/537.36");

	        // Disable automation flags
	        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
	        options.setExperimentalOption("useAutomationExtension", false);

	        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	        {
	        	DesiredCapabilities capabilities = new DesiredCapabilities();
	        	//os
	        	if(os.equalsIgnoreCase("windows"))
	        	{
	        		capabilities.setPlatform(Platform.WIN11);
	        	}
	        	else if(os.equalsIgnoreCase("mac"))
	        	{
	        		capabilities.setPlatform(Platform.MAC);
	        	}
	        	
	        	else
	        	{
	        		System.out.println("No matching os");
	        		return;
	        	}
	        	
	        	//browser
	        	switch(br.toLowerCase())
		        {
		        	case "chrome": capabilities.setBrowserName("chrome");break;
		        	case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
		        	case "firefox": capabilities.setBrowserName("Firefox");break;
		        	default: System.out.println("Invalid browser name.."); return;
		        }
	        	driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
	        }
	        
	        
	        if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	        { 
	        // Launch browsers
	        switch(br.toLowerCase())
	        {
	        	case "chrome": driver = new ChromeDriver(options);break;
	        	case "edge": driver = new EdgeDriver();break;
	        	case "firefox": driver = new FirefoxDriver();break;
	        	default: System.out.println("Invalid browser name.."); return;
	        }
	         
	    	//driver = new ChromeDriver();
		     driver.manage().deleteAllCookies();
		     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
	        }
		    // driver.get("https://www.opencart.com/index.php?route=common/home");
		     //driver.get("https://www.opencart.com/");
		     driver.get(p.getProperty("appurl"));
		     driver.manage().window().maximize();
	     }
	    
	    @AfterClass(groups = {"sainity","Master","Regression"})
	    public void teardown()
	    {
	    	driver.close();
	    }
	    
	   /* public String RandomString()
	    {
	    	String Generate = RandomStringUtils.randomAlphanumeric(0);
	    	return Generate;
	    }*/
	    
	    public String CaptureScreen(String tname)
	    {
	    	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	TakesScreenshot TakesScreenShot = (TakesScreenshot) driver;
	    	File SourceFile = TakesScreenShot.getScreenshotAs(OutputType.FILE);
	    	
	    	String targetFilePath = System.getProperty("user.id")+ "\\screenshots\\"+tname + "-"+ timestamp + ".png";
	    	File targetFile = new File(targetFilePath);
	    	
	    	SourceFile.renameTo(targetFile)	;
	    	
	    	return targetFilePath;
	    }

}
