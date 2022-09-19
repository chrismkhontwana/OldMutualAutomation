package Utillities;
import org.openqa.selenium.*;
import Engine.Base;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SeleniumUtility extends Base
{
    public static int WaitTimeout = 6;

    public static void attachScreenShot(String description) {

        try {
            Allure.addAttachment(description, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES))); // get screenshot from somewhere
        }
        catch(Exception e) {

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
            attachScreenShot("\n[ERROR] Failed to click on element  ---  " + element); //take screenshot when action fails
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

            log("Clicked element by Xpath : " + element, "INFO",  "text");
        }
        catch (Exception e) {
            log(errorMessage, "ERROR",  "text");
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
            log(errorMessage, "ERROR",  "text");
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
            log(errorMessage, "ERROR",  "text");
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
            log(errorMessage, "ERROR",  "text");
            Assert.fail("\n[ERROR] Failed to Move Over And Click on element ---- " + e.getMessage());
        }
    }

    public static String extractTextByXpath(String elementXpath, String errorMessage) {

        String retrievedText = "";
        try {
            waitForElementByXpath(elementXpath, errorMessage);
            WebElement elementToRead = driver.findElement(By.xpath(elementXpath));
            retrievedText = elementToRead.getText();

            log("Text : " + retrievedText + " retrieved from element by Xpath - " + elementXpath, "INFO",  "text");

            return retrievedText;
        }
        catch (Exception e) {
            log(errorMessage, "ERROR",  "text");
            Assert.fail("\n[ERROR] Failed to retrieve text from element - " + elementXpath + " - " + e.getMessage());

            return retrievedText;
        }
    }

    public static void pause(int millisecondsWait) {

        try {
            Thread.sleep(millisecondsWait);
        }
        catch (Exception e) {
            log("Failed to pause", "ERROR",  "text");
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
                        log("Found element : " + element,"INFO",  "text");
                        break;
                    }
                }
                catch (Exception e) {
                    elementFound = false;
                    log("Did Not Find element : " + element, "ERROR",  "text");
                }
                waitCount++;
            }
            if (waitCount == WaitTimeout) {
                GetElementFound(elementFound);
                log(errorMessage, "INFO",  "text");
                attachScreenShot("\n[ERROR] Reached TimeOut whilst waiting for element by : '" + element);
                Assert.fail("\n[ERROR] Reached TimeOut whilst waiting for element by : '" + element + "'");
            }
        }
        catch (Exception e) {
            log(errorMessage, "ERROR",  "text");
            attachScreenShot("\n[ERROR] Failed to wait for element by --- " + element);
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
                        log("Found element by Xpath : " + elementXpath,"INFO",  "text");
                        break;
                    }
                }
                catch (Exception e) {
                    elementFound = false;
                    log("Did Not Find element by Xpath: " + elementXpath, "ERROR",  "text");
                }
                waitCount++;
            }
            if (waitCount == WaitTimeout) {
                GetElementFound(elementFound);
                log(errorMessage, "INFO",  "text");
                attachScreenShot("\n[ERROR] Reached TimeOut whilst waiting for element by Xpath: '" + elementXpath);
                Assert.fail("\n[ERROR] Reached TimeOut whilst waiting for element by Xpath: '" + elementXpath + "'");
            }
        }
        catch (Exception e) {
            log(errorMessage, "ERROR",  "text");
            attachScreenShot("\n[ERROR] Failed to wait for element by Xpath --- " + elementXpath);
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
            log(errorMessage, "ERROR", "text");
            attachScreenShot("\n[ERROR] Failed to wait for element to be displayed: '" + element);
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

            log("Waited Page to load successfully", "INFO",  "text");
        }
        catch (Throwable error) {
            log("\n[ERROR] Timeout waiting for Page Load Request to complete.", "ERROR",  "text");
            attachScreenShot("\n[ERROR] Timeout waiting for Page Load Request to complete."); //take screenshot when action fails
            Assert.fail("\n[ERROR] Timeout waiting for Page Load Request to complete.");
        }
    }
}
