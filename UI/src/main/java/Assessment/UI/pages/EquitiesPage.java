package Assessment.UI.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EquitiesPage extends BasePage {
	private NavigationTabs navigationTabs;
	private AssetContainer assetContainer;
	
    By pageTitle = By.cssSelector("#assets-search .elementor-element .elementor-widget-container .elementor-heading-title");
    By equitiesTabContainer = By.xpath("//p[contains(text(), 'Equities')]/parent::span");
    By moreInfoButton = By.cssSelector("button.me-btn.me-btn-secondary.me-btn-lg");
    By navigationBar = By.cssSelector(".me-scrollable");

	public EquitiesPage(WebDriver driver) {
        super(driver);
        this.navigationTabs = new NavigationTabs(driver);
        this.assetContainer = new AssetContainer(driver);
    }
	
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void navigateToFunds() {
        navigationTabs.clickFundsTab();
    }

    public void navigateToEquities() {
        navigationTabs.clickEquitiesTab();
    }

    public void navigateToETFs() {
        navigationTabs.clickETFsTab();
    }

    public void navigateToBonds() {
        navigationTabs.clickBondsTab();
    }

    public String getEquitiesTabClassAttribute() {
        WebElement equitiesTabContainerElement = driver.findElement(equitiesTabContainer);
        return equitiesTabContainerElement.getAttribute("class");
    }

    public void clickMoreInfoButton() {
        WebElement moreInfoButtonElement = driver.findElement(moreInfoButton);
//        wait.until(ExpectedConditions.elementToBeClickable(moreInfoButtonElement));
        moreInfoButtonElement.click();
    }
    
    public WebElement getNavigationBar() {
    	return driver.findElement(navigationBar);
    }
}
