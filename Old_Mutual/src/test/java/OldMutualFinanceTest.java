import Engine.Base;
import WebPages.OldMutualFinance;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utillities.ExtentTestManager.startTest;

public class OldMutualFinanceTest extends Base
{
    OldMutualFinance page = new OldMutualFinance();

    @Test(description = "Navigate and calculate personal loan")
    public  void ValidateOldMutualFinance(Method method)
    {
        startTest(method.getName(), "Navigate and calculate personal");
        page.OpenOlMutualPage();
        page.validatePageTitle();
        page.NavigateToPersonalLoans();
        page.ValidatePersonalLoanPage();
        page.CalculateLoan();
        page.ValidateCalculationResults();
    }

}