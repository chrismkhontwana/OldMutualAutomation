package Utillities;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import Engine.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Utillities.ExtentTestManager.getTest;

public class SeleniumUtility extends Base
{
    public static int WaitTimeout = 6;


    public static void takeSnapShot(WebDriver webdriver,String fileWithPath)
    {
        try {

            TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(fileWithPath);
            FileUtils.copyFile(SrcFile, DestFile);
        }
        catch (Exception e)
        {
            Assert.fail("\n[ERROR] Failed to take screenshot" + e.getMessage());
        }
    }
    public static void clickElementBy(By element, String errorMessage)
    {
        try
        {
            WebElement elementToClick = driver.findElement(element);
            elementToClick.click();
        }
        catch(Exception e) {
            Assert.fail("\n[ERROR] Failed to click on element  ---  " + element + "' - " + e.getMessage());
        }
    }

    public static void clickElementByActions(By element, String errorMessage) {

        try {
            Actions actions = new Actions(driver);

            WebElement ele = driver.findElement(element);
            actions.moveToElement(ele);
            actions.click();
            actions.perform();
        }
        catch (Exception e) {
            Assert.fail("\n[ERROR] Failed to click on element --- " + element + "' - " + e.getMessage());
        }
    }

    public static void hoveOverElementByActions(By elementToMoveOver, String errorMessage) {

        try {
            WebElement menu =  driver.findElement(elementToMoveOver);
            Actions action = new Actions(driver);
            action.moveToElement(menu).perform();

        }
        catch (Exception e) {
            Assert.fail("\n[ERROR] Failed to Move Over And Click on element ---- " + e.getMessage());
        }
    }

    public static void scrollIntoView(By elementToMoveOver, String errorMessage)
    {

        try {
            WebElement menu =  driver.findElement(elementToMoveOver);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", driver.findElement(elementToMoveOver));

        }
        catch (Exception e) {
            Assert.fail("\n[ERROR] Failed to Move Over And Click on element ---- " + e.getMessage());
        }
    }

    public static void hoveOverAndClickElementByActions(By elementToMoveOver,By elementToClick, String errorMessage) {

        try {
            WebElement menu =  driver.findElement(elementToMoveOver);
            WebElement sub_menu =  driver.findElement(elementToClick);
            Actions action = new Actions(driver);
            action.moveToElement(menu).perform();

            sub_menu.click();
        }
        catch (Exception e) {
            Assert.fail("\n[ERROR] Failed to Move Over And Click on element ---- " + e.getMessage());
        }
    }

    public static String extractTextByXpath(String elementXpath, String errorMessage) {

        String retrievedText = "";
        try {
            waitForElementByXpath(elementXpath, errorMessage);
            WebElement elementToRead = driver.findElement(By.xpath(elementXpath));
            retrievedText = elementToRead.getText();


            return retrievedText;
        }
        catch (Exception e) {
            Assert.fail("\n[ERROR] Failed to retrieve text from element - " + elementXpath + " - " + e.getMessage());

            return retrievedText;
        }
    }

    public static void pause(int millisecondsWait) {

        try {
            Thread.sleep(millisecondsWait);
        }
        catch (Exception e) {
            Assert.fail("\n[ERROR] Failed to pause --- " + e.getMessage());
        }
    }

    public static void waitForElementBy(By element, String errorMessage) {

        boolean elementFound = false;
        try {
            int waitCount = 0;
            while (!elementFound && waitCount < WaitTimeout) {
                try {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1)); //TODO new

                    wait.until(ExpectedConditions.presenceOfElementLocated(element));
                    wait.until(ExpectedConditions.elementToBeClickable(element));
                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(element)) != null) {
                        elementFound = true;
                        break;
                    }
                }
                catch (Exception e) {
                    elementFound = false;
                }
                waitCount++;
            }
            if (waitCount == WaitTimeout) {
                GetElementFound(elementFound);
                Assert.fail("\n[ERROR] Reached TimeOut whilst waiting for element by : '" + element + "'");
            }
        }
        catch (Exception e) {
            Assert.fail("\n[ERROR] Failed to wait for element by --- " + element + "' - " + e.getMessage());
        }

        GetElementFound(elementFound);
    }


    public static void waitForElementByXpath(String elementXpath, String errorMessage) {

        boolean elementFound = false;
        try {
            int waitCount = 0;
            while (!elementFound && waitCount < WaitTimeout) {
                try {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1)); //TODO new

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementFound = true;
                        break;
                    }
                }
                catch (Exception e) {
                    elementFound = false;
                }
                waitCount++;
            }
            if (waitCount == WaitTimeout) {
                GetElementFound(elementFound);
                Assert.fail("\n[ERROR] Reached TimeOut whilst waiting for element by Xpath: '" + elementXpath + "'");
            }
        }
        catch (Exception e) {
            Assert.fail("\n[ERROR] Failed to wait for element by Xpath --- " + elementXpath + "' - " + e.getMessage());
        }

        GetElementFound(elementFound);
    }

    private static boolean GetElementFound(boolean elementFound) {
        return elementFound;
    }

    public static void waitForElementToBeDisplayed(By element, Integer timeout, String errorMessage) {

        boolean elementDisplayed = false;
        try {
            int waitCount = 0;
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            while (!elementDisplayed && waitCount < timeout) {
                try {
                    if ((driver.findElement(element).isDisplayed())) {
                        elementDisplayed = true;
                        break;
                    }
                }
                catch (Exception e) {
                    elementDisplayed = false;
                }
                waitCount++;
            }
        }
        catch (Exception e) {
            elementDisplayed = false;
            Assert.fail("\n[ERROR] Failed to wait for element to be displayed: '" + element);
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        GetElementFound(elementDisplayed);
    }

    public static void waitForPageToLoad() {

        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(expectation);
        }
        catch (Throwable error) {
            Assert.fail("\n[ERROR] Timeout waiting for Page Load Request to complete.");
        }
    }
}
