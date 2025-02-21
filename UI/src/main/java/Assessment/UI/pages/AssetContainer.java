package Assessment.UI.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AssetContainer extends BasePage {
	
	By lockSymbolSpan = By.xpath(".//span[2]");
	By plusLockElement = By.cssSelector("div.text-success-400");
	By usdLockElement = By.cssSelector("div.text-6xl.font-semibold.text-gray-600");
	By assetNameElement = By.cssSelector("h2.text, h4.text");
	By assetDetailsBlock = By.cssSelector("div.elementor-asset-detail-widget");
	
	public AssetContainer(WebDriver driver) {
		super(driver);
	}
	
	public String getAssetName() {
        WebElement assetNameElementElement = getAssetDetailsBlock().findElement(assetNameElement);
        return assetNameElementElement.getText();
    }
	
	public String getUsdLockText() {
        WebElement usdLockElementElement = getAssetDetailsBlock().findElement(usdLockElement);
        return usdLockElementElement.getText();
    }
	
	public String getPlusLockText() {
        WebElement plusLockElementElement = getAssetDetailsBlock().findElement(plusLockElement);
        return plusLockElementElement.getText();
    }
	
	public List<WebElement> getLockSymbolSpans() {
        WebElement parentContainer = driver.findElement(By.cssSelector("div.elementor-asset-detail-widget div.flex.flex-wrap"));
        return parentContainer.findElements(lockSymbolSpan);
    }
	
	public boolean isAssetDetailsBlockDisplayed() {
		return driver.findElement(assetDetailsBlock).isDisplayed();
	}
	
	public WebElement getAssetDetailsBlock() {
		return driver.findElement(assetDetailsBlock);
	}
	
}
