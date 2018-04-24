package steps;
import org.openqa.selenium.WebDriver;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

import core.driver.Driver;
import utils.WebUtils;

public class Common extends WebUtils {
	WebDriver driver = Driver.webDriver;
	DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();
	
    @Step("I navigate to <url>")
    public void navigateToPage(String url){
    	scenarioStore.put("url", url);
    	driver.get(url);
    	saveScreenshot();
    }
    @Step("I click to element <element> with <type>=<value>")
    public void clickToElement(String element,String type, String value){
    	scenarioStore.put(element, value);
    	findElement(type, value).click();
    	saveScreenshot();
    }
    @Step("I send the keys <keys> to element <element> with <type>=<value>")
    public void sendToElement(String keys,String element,String type, String value){
    	scenarioStore.put(element, value);
    	findElement(type, value).sendKeys(keys);
    	saveScreenshot();
    }

}
