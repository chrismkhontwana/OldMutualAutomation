import Engine.Base;
import org.testng.annotations.Test;

public class TestImplementation extends Base {


    @Test
    public static void getOldMutualTitle() {
        driver.get("https://www.oldmutual.co.za/");
        System.out.println("Title "+driver.getTitle());

    }

}