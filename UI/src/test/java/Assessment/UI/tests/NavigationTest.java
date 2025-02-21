package Assessment.UI.tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Assessment.UI.base.TestBase;
import Assessment.UI.pages.EquitiesPage;

public class NavigationTest extends TestBase {
	
    private EquitiesPage equitiesPage;

    @BeforeMethod
    public void setUp() {
    	launchApplication();
        equitiesPage = new EquitiesPage(driver);
        equitiesPage.acceptCookies();
    }
    
	@Test
    public void testNavigation() {
		equitiesPage.scroll(equitiesPage.getNavigationBar());
		
        List<String> securityTypes = Arrays.asList("Funds", "Equities", "ETFs", "Bonds");

        for (String securityType : securityTypes) {
            switch (securityType) {
                case "Funds":
                	equitiesPage.navigateToFunds();
                    break;
                case "Equities":
                	equitiesPage.navigateToEquities();
                    break;
                case "ETFs":
                	equitiesPage.navigateToETFs();
                    break;
                case "Bonds":
                	equitiesPage.navigateToBonds();
                    break;
            }

            equitiesPage.scroll(equitiesPage.getNavigationBar());
            Assert.assertTrue(equitiesPage.getPageTitle().contains(securityType),
                "Page title does not match the selected tab: " + securityType);
        }
    }
}
