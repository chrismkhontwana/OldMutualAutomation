package WebPages;

import Engine.Base;
import Properties.WebProperties;
import Utillities.SeleniumUtility;
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
        System.out.println("Title = "+driver.getTitle());
    }
}
