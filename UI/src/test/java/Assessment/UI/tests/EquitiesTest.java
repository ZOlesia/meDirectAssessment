package Assessment.UI.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Assessment.UI.base.TestBase;
import Assessment.UI.pages.AssetContainer;
import Assessment.UI.pages.EquitiesPage;

public class EquitiesTest extends TestBase {
    private EquitiesPage equitiesPage;
    private AssetContainer assetContainer;

    @BeforeMethod
    public void setUp() {
    	launchApplication();
        equitiesPage = new EquitiesPage(driver);
        assetContainer = new AssetContainer(driver);
        equitiesPage.acceptCookies();
        equitiesPage.scroll(equitiesPage.getNavigationBar());
    }
    
    
    @Test(priority=1)
    public void testEquitiesTabHasDataInTable() {
        String classAttribute = equitiesPage.getEquitiesTabClassAttribute();

        Assert.assertTrue(classAttribute.contains("bg-white") &&
                          classAttribute.contains("text-gray-300") &&
                          classAttribute.contains("text-gray-400"),
                          "Equities tab is not selected properly!");

        List<WebElement> rows = equitiesPage.getTableRows();
        Assert.assertTrue(rows.size() > 0, "The table is empty!");
    }
    
    
    @Test(priority=2)
    public void testSearchNonExistingEquity() {
        String searchText = "..............";
        equitiesPage.enterSearchText(searchText);
        equitiesPage.scroll(equitiesPage.getTable());
        equitiesPage.waitForEmptyTable();
        List<WebElement> rows = equitiesPage.getTableRows();
        Assert.assertTrue(rows.isEmpty(), "The table is not empty after filtering with a non-existing equity!");
        
    }
    
    @Test(priority=3)
    public void testEquitySearchAndDetails() {
        String searchText = "1st Constitution Bancorp";
        equitiesPage.enterSearchText(searchText);

        if (!equitiesPage.isTableDisplayed()) {
            equitiesPage.scroll(equitiesPage.getTableRows().get(0));
        }
                
        List<WebElement> rows = equitiesPage.getTableRows();
        Assert.assertTrue(rows.size() > 0, "No rows found after search!"); //

        equitiesPage.clickMoreInfoButton();
        
        if(!assetContainer.isAssetDetailsBlockDisplayed()) {
            equitiesPage.scroll(assetContainer.getAssetDetailsBlock());
        }
        
        String assetName = assetContainer.getAssetName();
        Assert.assertTrue(assetName.contains(searchText), "The asset name does not contain the search text!");

        String usdLockText = assetContainer.getUsdLockText();
        Assert.assertTrue(usdLockText.contains("🔒"), "The 'USD🔒' element does not contain the lock symbol!");

        String plusLockText = assetContainer.getPlusLockText();
        Assert.assertTrue(plusLockText.contains("🔒"), "The '+🔒 (+🔒%)' element does not contain the lock symbol!");

        List<WebElement> lockSymbolSpans = assetContainer.getLockSymbolSpans();
        for (WebElement lockSymbolSpan : lockSymbolSpans) {
            Assert.assertTrue(lockSymbolSpan.isDisplayed(), "The lock symbol (🔒) is not visible in one of the divs!");
            Assert.assertEquals(lockSymbolSpan.getText(), "🔒", "The span does not contain the lock symbol (🔒)!");
        }
    }
}
