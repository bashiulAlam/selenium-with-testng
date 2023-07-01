package com.assignment.tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.assignment.pages.BasePage;
import com.assignment.pages.Page;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	
	WebDriver driver;
	public Page page;
	
	private String url = "";

	public BaseTest(String url) {
		this.url = url;
	}
	
	@BeforeClass
	@Parameters(value= {"browser"})
	public void setupTest(String browser) {
		if(browser.equals("chrome")) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
		}
		
		else if(browser.equals("firefox")) {
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary(firefoxBinary);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(firefoxOptions);
		}
		else {
			logger.info("No Browser is Defined in xml");
		}

		// Default value 30 Seconds implicit timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Default value 300 Seconds page load timeout
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));

		driver.get(this.url);

		logger.info("Loading URL: " + this.url);

		page = new BasePage(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
