package WebPages;

import Engine.Base;
import Properties.WebProperties;
import Utillities.SeleniumUtility;
import org.openqa.selenium.By;
import org.testng.Assert;

public class OldMutualFinance extends Base
{
    WebProperties webProp = new WebProperties();
    SeleniumUtility seleniumUtility = new SeleniumUtility();
    public void OpenOlMutualPage()
    {
        try
        {
            driver.get(webProp.OldMutualURL());
            seleniumUtility.waitForPageToLoad();
        }
        catch (Exception e)
        {
            log("Failed to navigate to old mutual branch", "ERROR",  "text");
            Assert.fail("\n[ERROR] Failed to navigate to old mutual branch  - - " + e.getMessage());
        }
    }
    public void validatePageTitle()
    {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(webProp.PageName(), pageTitle);
        log("Successfully validated the title :"+pageTitle, "ERROR",  "text");
    }

    public void ValidatePersonalLoanPage()
    {
        String bannerText =  seleniumUtility.extractTextByXpath(webProp.PersonalLoanBanner(), "Failed to get text");
        Assert.assertTrue(bannerText.contains("Personal loan"));
    }

    public void CalculateLoan()
    {
//        Select you amount you need
        seleniumUtility.clickElementByActions(By.xpath(webProp.HowMuchDoYouNeedDropdown()),"Failed to click how much do you need loan");

        seleniumUtility.scrollIntoView(By.xpath(webProp.HowMuchDoYouNeedDropdown()),"");

        seleniumUtility.pause(5000);
        seleniumUtility.clickElementBy(By.xpath(webProp.SelectAmount()),"Failed to click amount");

        seleniumUtility.clickElementBy(By.xpath(webProp.NextButton()),"Failed to click next button");

//        Select the duration you want
        seleniumUtility.clickElementByActions(By.xpath(webProp.RepaymentDurationDropdown()),"Failed to click How long do you need to repay it?");

        seleniumUtility.scrollIntoView(By.xpath(webProp.RepaymentDurationDropdown()),"");

        seleniumUtility.pause(5000);
        seleniumUtility.clickElementBy(By.xpath(webProp.SelectDuration()),"Failed to click Duration");

        seleniumUtility.clickElementBy(By.xpath(webProp.CalculateButton()),"Failed to click Calculate button");

        seleniumUtility.pause(5000);
    }
    public void ValidateCalculationResults()
    {
        seleniumUtility.waitForPageToLoad();

        String repaymentResults =  seleniumUtility.extractTextByXpath(webProp.PersonalLoanRepaymentResults(), "Failed to get text");
        System.out.println("Repayment = "+repaymentResults);
        String[] stringArray = null;
        stringArray = repaymentResults.split(" - ");
        System.out.println("min = "+stringArray[0]);
        System.out.println("max = "+stringArray[1]);
        Assert.assertEquals(webProp.MinimumRepayment(),stringArray[0]);
        Assert.assertEquals(webProp.MaximumRepayment(),stringArray[1]);
    }

    public void NavigateToPersonalLoans()
    {
        seleniumUtility.clickElementByActions(By.xpath(webProp.personalMainMenu()),"Failed to click personal Menu");

        seleniumUtility.hoveOverAndClickElementByActions(By.xpath(webProp.ToolsAndCalculatorsMenu()),By.xpath(webProp.PersonalLoansCalculatorMenu()),"Failed to move over and click");
        seleniumUtility.pause(5000);

    }


}
