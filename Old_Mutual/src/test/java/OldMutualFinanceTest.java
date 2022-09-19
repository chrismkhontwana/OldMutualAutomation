import Engine.Base;
import WebPages.OldMutualFinance;
import org.testng.annotations.Test;

public class OldMutualFinanceTest extends Base
{
    OldMutualFinance page = new OldMutualFinance();

    @Test
    public  void ValidateOldMutualFinance()
    {
        page.OpenOlMutualPage();
        page.validatePageTitle();
        page.NavigateToPersonalLoans();
        page.ValidatePersonalLoanPage();
        page.CalculateLoan();
        page.ValidateCalculationResults();
    }

}