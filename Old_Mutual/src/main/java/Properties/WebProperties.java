package Properties;

public class WebProperties
{
    public String OldMutualURL()
    {
        return "https://www.oldmutualfinance.co.za/";
    }

    public String PageName()
    {
        return "Bank and Borrow Solutions | Old Mutual";
    }

    public String personalMainMenu()
    {
        return "//om-main-navigation-menu[@activemenuitemname='PERSONAL']//li//a[@href='/personal']";
    }

    public String ToolsAndCalculatorsMenu()
    {
        return "//om-main-navigation-menu[@slot='main-navigation-items-desktop']//ul[@slot='navigation-menu']//li[@class='active']//ul//a[contains(text(),'Tools & Calculators')]";
    }

    public String PersonalLoansCalculatorMenu()
    {
        return "//om-main-navigation-menu[@slot='main-navigation-items-desktop']//ul[@slot='navigation-menu']//li[@class='active']//ul//a[contains(text(),'Tools & Calculators')]/..//a[@href='/personal/tools-and-calculators/personal-loan-calculator/']";
    }
    ////om-main-navigation-menu[@slot='main-navigation-items-desktop']//ul[@slot='navigation-menu']//li[@class='active']//ul//a[contains(text(),'Tools & Calculators')]/..//span[text()='Personal Loans Calculator']

    public String HowMuchDoYouNeedDropdown()
    {
        return "//input[@placeholder='How much do you need?']//..//../span[@class='om-dropdown-field__arrow']";
    }

    public String SelectAmount()
    {
        return "//om-form-dropdown-field//ul[@class='dropdown-options-list']//li[text()='R50 000']";
    }

    public String NextButton()
    {
        return "//button//span[text()='Next']";
    }
    public String RepaymentDurationDropdown()
    {
        return "//om-form-dropdown-field-wrapper[@id='repaymentDuration']//om-icon[@icon-name='chevron_down']";
    }

    public String SelectDuration()
    {
        return "//om-form-dropdown-field//ul[@class='dropdown-options-list']//li[text()='60 Months']";
    }

    public String CalculateButton()
    {
        return "//button//span[text()='Calculate']";
    }

    public String PersonalLoanBanner()
    {
        return "//span[@slot='hero-banner-header']";
    }

    public String PersonalLoanRepaymentResults()
    {
        return "//p[text()='Your monthly repayment will be'] /..//strong";
    }

    public String MinimumRepayment()
    {
        return "R1 656.43";
    }

    public String MaximumRepayment()
    {
        return "R1 810.05";
    }
}
