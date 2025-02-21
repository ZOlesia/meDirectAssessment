package Assessment.UI.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
    By searchBar = By.cssSelector("input[data-t='expandable-input-element']");
    By table = By.cssSelector("table.me-tbl.w-full");
    By cookieConsentButton = By.className("iubenda-cs-accept-btn");
    By tableRows = By.cssSelector("tr.me-tbl-row");
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void acceptCookies() {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(cookieConsentButton));
            acceptButton.click();
        } catch (Exception e) {
            System.out.println("Cookie consent popup did not appear or was already handled.");
        }
    }

    public void enterSearchText(String text) {
        driver.findElement(searchBar).sendKeys(text);
    }

    public boolean isTableDisplayed() {
        return driver.findElement(table).isDisplayed();
    }

    public List<WebElement> getTableRows() {
        return driver.findElements(tableRows);
    }
    
    public WebElement getTable() {
    	return driver.findElement(table);
    }
    
    public void waitForEmptyTable() {
    	wait.until(ExpectedConditions.numberOfElementsToBe(tableRows, 0));
    }
    
    public void scroll(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ")");
    }
    
    public void scroll(WebElement element) {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }

}
