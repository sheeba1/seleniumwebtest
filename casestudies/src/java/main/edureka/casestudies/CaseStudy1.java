package edureka.casestudies;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CaseStudy1 {

	// Global declaration
	WebDriver driver;
	WebElement webElement;

	public void setupBrowser(String browser, String url) {
		String currDir = System.getProperty("user.dir");

		System.out.println("curr," + currDir);

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", currDir + "\\driver\\chromedriver.exe");
			driver = new ChromeDriver();// To start a chrome driver instance
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", currDir + "\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();// To start a firefox driver instance
		} else if (browser.equalsIgnoreCase("edge")) {
			// system property
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", currDir + "\\driver\\<>");// add the ie driver
			driver = new InternetExplorerDriver();
		}

		else {
			System.out.println("Driver object is null, hence stopping the automation run");
			System.exit(0);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Wait for the Web Page to load completely before the test execution starts
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		if (!url.equalsIgnoreCase(""))
			driver.get(url);// to open a url
		else
			driver.get("about:blank");

	}

	public void goToRegister() {
		// Wait till the web link “Register” is displayed
		WebDriverWait wait = new WebDriverWait(driver, 10);
		webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("REGISTER")));
		webElement.click();
	}

	public void registerDetails() throws InterruptedException {

		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("John");
		driver.findElement(By.name("lastName")).sendKeys("Smith");
		driver.findElement(By.name("phone")).sendKeys("222222");
		driver.findElement(By.id("userName")).sendKeys("ljohn@email.com");
		driver.findElement(By.name("address1")).sendKeys("60 Trailing Way");
		driver.findElement(By.name("address2")).sendKeys("California");

		driver.findElement(By.name("city")).sendKeys("tustin");
		driver.findElement(By.name("state")).sendKeys("CA");
		driver.findElement(By.name("postalCode")).sendKeys("9200");
		Select selectObj = new Select(driver.findElement(By.name("country")));
		selectObj.selectByVisibleText("UNITED KINGDOM");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		 webElement =
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email")));

		driver.findElement(By.cssSelector("#email")).sendKeys("ljohn");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("confirmPassword")).sendKeys("password");
		driver.findElement(By.name("register")).submit();

	}

	public void signIn() {

		System.out.println("in signIn" + driver.getCurrentUrl());
		driver.findElement(By.xpath("//a[contains(text(),'sign')]")).click();
	}

	public void verifyUser() {

		driver.findElement(By.xpath("//*[contains(text(),'Dear')]"));

		WebDriverWait wait = new WebDriverWait(driver, 10);
		webElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Dear')]")));

		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		webElement = wait2
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Note')]")));

		WebElement webelement3 = driver.findElement(By.xpath("//*[contains(text(),'Note')]"));
		boolean b1 = webelement3.getText().contains("ljohn");

		if (b1) {

			System.out.println("User is verified and user name is " + webelement3.getText());
		}


	}

	public void clickFligtsLink() {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Flights")));
		webElement.click();

	}

	public void selectFlightDetails() {
		WebElement select = driver.findElement(By.xpath("//input[@name='tripType']"));
		boolean radioSelected = select.isEnabled();

		if (!radioSelected)
			select.click();

		Select passengers = new Select(driver.findElement(By.xpath("//select[1]")));
		passengers.selectByIndex(1);

		Select fromMonth = new Select(driver.findElement(By.xpath("//select[@name='fromMonth']")));
		fromMonth.selectByIndex(4);

		Select fromDay = new Select(driver.findElement(By.xpath("//select[@name='fromDay']")));

		fromMonth.selectByIndex(8);

		Select toMonth = new Select(driver.findElement(By.xpath("//body//child::select[@name='toMonth']")));

		toMonth.selectByIndex(8);

		WebElement toDay = driver.findElement(By.xpath("//body//select[@name='toMonth']/child::option[10]"));
		toDay.click();

		Select departure = new Select(driver.findElement(By.xpath("//select[contains(@name,'fromPort')]")));

		departure.selectByIndex(6);

		Select toPort = new Select(driver.findElement(By.name("toPort")));

		toPort.selectByIndex(4);

		WebElement element = driver.findElement(By.xpath("//input[@name='servClass' and @value='First']"));
		element.click();

	

		WebElement pref = driver.findElement(By.xpath("//body//select[@name='airline']/child::option[2]"));
		pref.click();

		driver.findElement(By.name("findFlights")).click();

	}

	public void selectReturnFlightDetails() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		webElement = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//img[@src='/images/masts/mast_selectflight.gif']")));

		List<WebElement> listOfFlights = driver.findElements(By.cssSelector("input[name='outFlight']"));

		listOfFlights.get(3).click();

		List<WebElement> listOfReturnFLights = driver.findElements(By.cssSelector("input[name='inFlight']"));

		listOfReturnFLights.get(3).click();

		driver.findElement(By.name("reserveFlights")).click();

		// working code by using xpath axes
		// driver.findElement(By.xpath("//form[@name='results']//td/child::input[@value='Pangea
		// Airlines$362$274$9:17']")).click();

	}

	public void securePurchase() {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("buyFlights")));
		driver.findElement(By.name("passFirst0")).sendKeys("John");
		driver.findElement(By.name("passLast0")).sendKeys("Smith");
		Select mealPreference = new Select(driver.findElement(By.name("pass.0.meal")));

		mealPreference.selectByIndex(5);

		driver.findElement(By.name("passFirst1"));

		driver.findElement(By.name("passFirst1")).sendKeys("Mrs.John");
		driver.findElement(By.name("passLast1")).sendKeys("Smith");
		Select mealPreference2 = new Select(driver.findElement(By.name("pass.1.meal")));

		mealPreference2.selectByIndex(2);

		driver.findElement(By.name("creditnumber")).sendKeys("12345");

		Select credit_exp_date = new Select(driver.findElement(By.name("cc_exp_dt_mn")));
		credit_exp_date.selectByIndex(4);

		Select credit_exp_year = new Select(driver.findElement(By.name("cc_exp_dt_yr")));
		credit_exp_year.selectByIndex(5);

		driver.findElement(By.name("buyFlights")).click();

	}

	public void verifyItenary() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		webElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Your')]")));

	
	}

	public void closeBrowser() {

		if (driver != null) {

			driver.close();
			driver.quit();

		}

	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		CaseStudy1 caseStudy1 = new CaseStudy1();
		caseStudy1.setupBrowser("chrome", "http://newtours.demoaut.com/");
		caseStudy1.goToRegister();
		caseStudy1.registerDetails();

		caseStudy1.verifyUser();
		caseStudy1.clickFligtsLink();
		caseStudy1.selectFlightDetails();
		caseStudy1.selectReturnFlightDetails();
		caseStudy1.securePurchase();
		caseStudy1.verifyItenary();
		caseStudy1.closeBrowser();

	}

}
