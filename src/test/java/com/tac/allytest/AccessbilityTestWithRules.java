package com.tac.allytest;

import com.deque.html.axecore.axeargs.AxeRuleOptions;
import com.deque.html.axecore.axeargs.AxeRules;
import com.deque.html.axecore.axeargs.AxeRunOptions;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.assertTrue;

/**
 * @author senthil
 */
public class AccessbilityTestWithRules {

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

        AxeRuleOptions enabledRules = new AxeRuleOptions();
        enabledRules.setEnabled(true);
        Map<String, AxeRuleOptions> rulesMap = new HashMap<>();
        rulesMap.put("performanceTimer",enabledRules);

        AxeRules rules = new AxeRules();
        rules.setRules(rulesMap);

        AxeRunOptions options = new AxeRunOptions();
        options.setRules(rulesMap);

        AxeBuilder axeBuilder = new AxeBuilder();
        Results allyResults = axeBuilder
                .withOptions(options)
                .analyze(driver);

        List<Rule> ruleList = allyResults.getViolations();
        if(ruleList.size()==0){
            assertTrue("No violations found", true);
        }else{
            AxeReporter.getReadableAxeResults("Accessibility Test",driver,ruleList);
            assertTrue(AxeReporter.getAxeResultString(),false);
        }
    }

}
