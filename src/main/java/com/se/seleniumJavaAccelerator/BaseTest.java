package com.se.seleniumJavaAccelerator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;
import com.se.seleniumJavaAccelerator.utils.ReportManager;
import com.se.seleniumJavaAccelerator.utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;



public class BaseTest {
    protected WebDriver driver;
    private int defaulTimeoutUntilClickable = Config.getIntProperty("defaultTimeoutSecondsUntilClickable");
    private int defaulTimeoutUntilVisible = Config.getIntProperty("defaultTimeoutSecondsUntilVisible");
    protected ExtentTest logTest;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public static void setUpReport() {
    	System.setProperty("allure.results.directory", "target/allure-results");
    	ReportManager.setUpReport();
    }
 
    @BeforeMethod
	public void setUp(ITestResult result) {
        String browser = Config.getStringProperty("browser");
        String url = Config.getStringProperty("url");
        String description = "Verification of " + result.getMethod().getDescription();
        ReportManager.startTest(result.getMethod().getMethodName(), description, result.getMethod().getGroups());
        startAndMaximizeBrowser(browser);
        goToURL(url);
    }
    
    public void startAndMaximizeBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" is not supported.");
        }
        driver.manage().window().maximize();
     //   ReportManager.logInfo("Browser "+ browser + " started and maximized");
    }
    
    public void goToURL (String url) {
    	driver.get(url);
    	ReportManager.logInfo("Navigating to URL: " + url);
    }

    @AfterMethod
	public void tearDown() {
        if (driver != null) {
        	Utils.takeScreenshot(driver);
        	ReportManager.logInfo("Closing the browser");
            driver.quit();
        }
        ReportManager.tearDown();
    }
    
    
    /* Elements interactions */
    public WebElement waitForElementClickable(By locator) {
    	ReportManager.logInfo("Waiting for element to be clickable: " + locator);
        return new WebDriverWait(driver, Duration.ofSeconds(defaulTimeoutUntilClickable)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementVisible(By locator) {
    	ReportManager.logInfo("Waiting for element to be visible: " + locator);
        return new WebDriverWait(driver, Duration.ofSeconds(defaulTimeoutUntilVisible)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator) {
    	scrollToElement(locator);
        waitForElementClickable(locator).click();
        ReportManager.logInfo("Click on element: " + locator);
    }

    public void enterText(By locator, String text) {
    	scrollToElement(locator);
        WebElement element = waitForElementVisible(locator);
        element.sendKeys(text);
        ReportManager.logInfo("Entering search text " + "'" + text + "'" + " on element: " + locator);
    }

    public void clearAndEnterText(By locator, String text) {
    	scrollToElement(locator);
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
        ReportManager.logInfo("Entering search text " + "'" + text + "'" + " on element: " + locator);
    }

    public void selectCheckbox(By locator) {
    	scrollToElement(locator);
        WebElement checkbox = waitForElementClickable(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
            ReportManager.logInfo("Checkbox selected: " + locator);
        } else {
            ReportManager.logInfo("Checkbox already selected: " + locator);
        }
    }

    public void selectDropdownByText(By locator, String text) {
    	scrollToElement(locator);
        WebElement element = waitForElementVisible(locator);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
        ReportManager.logInfo("Selected dropdown option by text " + "'" + text + "'" + " on element: " + locator);
    }

    public void selectDropdownByValue(By locator, String value) {
    	scrollToElement(locator);
        WebElement element = waitForElementVisible(locator);
        Select dropdown = new Select(element);
        dropdown.selectByValue(value);
        ReportManager.logInfo("Selected dropdown option by value " + "'" + value + "'" + " on element: " + locator);
    }

    public String getElementAttribute(By locator, String attribute) {
        String value = waitForElementVisible(locator).getAttribute(attribute);
        ReportManager.logInfo("Got attribute " + attribute +" value: " + value + " from element: "+ locator);
        return value;
    }

    public String getElementText(By locator) {
        String text = waitForElementVisible(locator).getText();
        ReportManager.logInfo("Got text " + "'" + text + "'" + " from element: " + locator);
        return text;
    }

    public void switchToTab(int tabIndex) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabIndex));
        ReportManager.logInfo("Switched to tab with index: " + tabIndex);
    }

    public void switchToFrame(int frameIndex) {
        driver.switchTo().frame(frameIndex);
        ReportManager.logInfo("Switched to frame with index: " + frameIndex);
    }

    public void switchToIframe(By locator) {
        WebElement iframe = waitForElementVisible(locator);
        driver.switchTo().frame(iframe);
        ReportManager.logInfo("Switched to iframe: " + locator);
    }

    public void scrollToElement(By locator) {
        WebElement element = waitForElementVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ReportManager.logInfo("Scrolled to element: " + locator);
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            ReportManager.logInfo("Element is present: " + locator);
            return true;
        } catch (NoSuchElementException e) {
            ReportManager.logInfo("Element is not present: " + locator);
            return false;
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            boolean isVisible = driver.findElement(locator).isDisplayed();
            ReportManager.logInfo("Element is visible: " + locator);
            return isVisible;
        } catch (NoSuchElementException e) {
            ReportManager.logInfo("Element is not visible: " + locator);
            return false;
        }
    }
    
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public Alert switchToAlert() {
        Alert alert = driver.switchTo().alert();
        ReportManager.logInfo("Switched to alert");
        return alert;
    }

    public void hoverOverElement(By locator) {
        WebElement element = waitForElementVisible(locator);
        new Actions(driver).moveToElement(element).perform();
        ReportManager.logInfo("Hovered over element:" + locator);
    }

    public boolean validatePageTitle(String expectedTitle) {
        boolean isValid = driver.getTitle().equals(expectedTitle);
        ReportManager.logInfo("Page title validation result: " + isValid + " (expected: " +expectedTitle+")");
        return isValid;
    }

    public boolean validatePageUrl(String expectedUrl) {
        boolean isValid = driver.getCurrentUrl().equals(expectedUrl);
        ReportManager.logInfo("Page URL validation result: " + isValid + " (expected: " +expectedUrl+")");
        return isValid;
    }
    
    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurar el estado de la interrupción
            System.err.println("La espera fue interrumpida");
        }
    }
}
