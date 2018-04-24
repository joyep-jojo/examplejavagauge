package utils;


import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.screenshot.ICustomScreenshotGrabber;

import core.driver.Driver;

public class WebUtils implements ICustomScreenshotGrabber{
	
	WebDriver driver = Driver.webDriver;
    public void WaitForElement(By by){
    	WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(getProperty("selenium_timeout")));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public WebElement findElement(String type, String selectorValue){
    	switch (type) {
		case "id":
			return findById(selectorValue);
		case "name":
			return findByName(selectorValue);
		case "class":
			return findByClass(selectorValue);
		case "xpath":
			return findByXpath(selectorValue);
		case "css":
			return findByCss(selectorValue);
		default:
			try {
				throw new Exception("The type " + type + "does not exist. Please use id|name|class|xpath|css");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
    }
    public WebElement findById(String id){
    	WaitForElement(By.id(id));
    	return driver.findElement(By.id(id));    	
    }
    public WebElement findByName(String name){
    	WaitForElement(By.name(name));
    	return driver.findElement(By.name(name));    	
    }
    public WebElement findByClass(String className){
    	WaitForElement(By.className(className));
    	return driver.findElement(By.className(className));    	
    }
    public WebElement findByXpath(String xPath){
    	WaitForElement(By.xpath(xPath));
    	return driver.findElement(By.xpath(xPath));    	
    }
    public WebElement findByCss(String css){
    	WaitForElement(By.cssSelector(css));
    	return driver.findElement(By.cssSelector(css));    	
    }
    public String getProperty(String propertyKey) {
    	return System.getenv(propertyKey);
    }
    public void saveScreenshot() {
    	byte[] encoded = Base64.encodeBase64(takeScreenshot());
    	Gauge.writeMessage("<img src='data:image/png;base64," + new String(encoded) + "'>");
    }
    
	@Override
	public byte[] takeScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	} 
}
