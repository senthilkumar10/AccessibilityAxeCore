package com.tac.allytest;

import com.deque.html.axecore.axeargs.AxeRunOptions;
import com.deque.html.axecore.extensions.WebDriverExtensions;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * @author senthil
 */
public class AccessbilityTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testAccessibility() throws OperationNotSupportedException, IOException {
        driver.get("https://www.amazon.co.uk/");

        Results allyResults = WebDriverExtensions.analyze(driver);

        List<Rule> ruleList = allyResults.getViolations();
        if(ruleList.size()==0){
            assertTrue("No violations found", true);
        }else{
            AxeReporter.getReadableAxeResults("Accessibility Test",driver,ruleList);
            assertTrue(AxeReporter.getAxeResultString(),false);
        }
    }

}
