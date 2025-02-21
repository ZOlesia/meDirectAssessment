package Assessment.UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationTabs {
	protected WebDriver driver;
	
    By fundsTab = By.xpath("//p[contains(text(), 'Funds')]/ancestor::a"); 
    By equitiesTab = By.xpath("//p[contains(text(), 'Equities')]/ancestor::a");
    By etfsTab = By.xpath("//p[contains(text(), 'ETFs')]/ancestor::a");
    By bondsTab = By.xpath("//p[contains(text(), 'Bonds')]/ancestor::a");
    
    public NavigationTabs(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to click on tabs
    public void clickFundsTab() {
        driver.findElement(fundsTab).click();
    }

    public void clickEquitiesTab() {
        driver.findElement(equitiesTab).click();
    }

    public void clickETFsTab() {
        driver.findElement(etfsTab).click();
    }

    public void clickBondsTab() {
        driver.findElement(bondsTab).click();
    }
}
