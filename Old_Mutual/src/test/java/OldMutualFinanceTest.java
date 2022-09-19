import Engine.Base;
import WebPages.OldMutualFinance;
import org.testng.annotations.Test;

public class OldMutualFinanceTest extends Base
{
    OldMutualFinance page = new OldMutualFinance();

    @Test
    public  void ValidateOldMutualFinanceTitle()
    {
        page.OpenOlMutualPage();
        page.validatePageTitle();
    }

}