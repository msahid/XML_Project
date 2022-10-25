package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	static WebDriver driver;
	static String Url;
	static String Browser;
	
	public static void readConfig() {
		Properties prop = new Properties();
		
		try{
			InputStream input = new FileInputStream("src/main/java/config/config.properties");
			prop.load(input);
			Browser = prop.getProperty("Browser");
			Url = prop.getProperty("url");
			
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public  static WebDriver init() {
		readConfig();
		
		if(Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
			driver = new ChromeDriver();
			
		}else if(Browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
			driver = new FirefoxDriver();
			
		}


//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(Url);
		return driver;
	}
	
	
	
	public static void tearDown() {
		driver.close();
		driver.quit();
	}


}
