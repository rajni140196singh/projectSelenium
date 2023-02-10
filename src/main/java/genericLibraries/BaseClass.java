package genericLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import popPages.ContactUsPage;
import popPages.CoreJavaForSeleniumPage;
import popPages.CoreJavaVideoPage;
import popPages.SeleniumTrainningPage;
import popPages.SkillRaryDemoAppPages;
import popPages.SkillRaryHomePage;
import popPages.TestingPage;

public class BaseClass {
	protected PropertiesFileUtility property;
	protected ExcelFileUtility excel;
	protected WebDriverUtility web;
	protected WebDriver driver;
	protected SkillRaryHomePage home;
	protected SkillRaryDemoAppPages demoApp;
	protected ContactUsPage contact;
	protected SeleniumTrainningPage selenium;
	protected TestingPage testing;
	protected CoreJavaForSeleniumPage coreJava;
	protected CoreJavaVideoPage javaDemo;
	
	//@BeforeSuite
	//@BeforeTest
	
	@BeforeClass
	public void classSetup() {
		property = new PropertiesFileUtility();
		excel = new ExcelFileUtility();
		web = new WebDriverUtility();
		
		property.propertiesInitialization(IConstantPath.PROPERTY_FILE_PATH);
		String browser = property.fetchProperty("browser");
		String url = property.fetchProperty("url");
		long time = Long.parseLong(property.fetchProperty("timeouts"));
		
		driver = web.openApplication(browser, url, time);
		home = new SkillRaryHomePage(driver);
		Assert.assertTrue(home.getLogo().isDisplayed());
	}
	
	@BeforeMethod
	public void methodSetup() {
		excel.excelInitialization(IConstantPath.EXCEL_FILE_PATH);
		demoApp = new SkillRaryDemoAppPages(driver);
		contact = new ContactUsPage(driver);
		selenium = new SeleniumTrainningPage(driver);
		testing = new TestingPage(driver);
		coreJava = new CoreJavaForSeleniumPage(driver);
		javaDemo = new CoreJavaVideoPage(driver);
	}
	
	@AfterMethod
	public void methodTearDown() {
		excel.closeWorkbook();
	}
	
	@AfterClass
	public void classTearDown() {
		web.quitBrowser();
	}
	//@AfterTest
	//@AfterSuite

	

}
