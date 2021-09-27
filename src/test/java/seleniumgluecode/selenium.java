package seleniumgluecode;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageobject.HomePage;
import com.pageobject.JobSearchPage;
import com.pageobject.ResultPage;
import com.pageobject.ReturnToJobPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class selenium {
    public static WebDriver driver;
    
    WebDriverWait wait;
    JavascriptExecutor js;
    ResultPage resultPage;
    ReturnToJobPage returnPage;
    
    
    @Given("^user navigate to URL \"([^\"]*)\"$")
    public void user_navigate_to_URL(String url) throws Throwable {
    	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
    	driver=new ChromeDriver(); 
    	wait = new WebDriverWait(driver, 20);
    	js = (JavascriptExecutor) driver;
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
        driver.get(url);
    }

    @When("^user click on careers link$")
    public void user_click_on_careers_link() throws Throwable {
    	Thread.sleep(2000);
    	HomePage homePage = new HomePage(driver);
    	homePage.clickCareerLink();
    	Set<String> windows = driver.getWindowHandles();
    	for(String window : windows) {
    		driver.switchTo().window(window);
    	}
    }

    @When("^user search for \"([^\"]*)\"$")
    public void user_search_for(String data) throws Throwable {
    	JobSearchPage searchPage = new JobSearchPage(driver);
    	searchPage.searchForJob(data);   
    }

    @When("^user selects \"([^\"]*)\"$")
    public void user_selects(String data) throws Throwable {
    	resultPage = new ResultPage(driver);
    	resultPage.selelctResult(data);      
    }

    @Then("^User verify \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_verify_and(String jobTitle, String jobLocation, String jobId) throws Throwable {
    	resultPage.verifyDetails(jobTitle, jobLocation, jobId);
    	
    }

    @Then("^user confirms introduction contains \"([^\"]*)\"$")
    public void user_confirms_introduction_contains(String description) throws Throwable {
       resultPage.confirmIntroduction(description);
    }

    @Then("^user confirms Requirements is there in page$")
    public void user_confirms_Requirements_is_there_in_page() throws Throwable {
    	resultPage.verifyRequirement();
    }

    @Then("^user confirms shift in page$")
    public void user_confirms_shift_in_page() throws Throwable {
    	resultPage.verifyHeaderShift();
    }

    @Then("^user confirms first suggested automation tool contains \"([^\"]*)\"$")
    public void user_confirms_first_suggested_automation_tool_contains(String arg1) throws Throwable {
    	resultPage.confirmAutomationTool();
    }

    @Then("^user click on Apply button$")
    public void user_click_on_Apply_button() throws Throwable {
    	resultPage.clickApplyButton();  
   }
    
    @Then("^I verify \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verifyDetails(String jobTitle, String jobLocation, String jobId) throws Throwable {
    	returnPage = new ReturnToJobPage(driver);
    	returnPage.verifyDetails(jobTitle, jobLocation, jobId);
    }
    
    @Then("^I confirms sentence of third paragraph$")
    public void i_confirms_sentence_of_third_paragraph() throws Throwable {
       returnPage.verifyThirdParagraph();
    }

    @Then("^user clicks on return to job search button$")
    public void user_clicks_on_return_to_job_search_button() throws Throwable {
    	returnPage.clickReturnToJob();
    }
    
    @Then("^user click on page to close popup$")
    public void closePopup() throws Throwable {
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//div[@class='si-overlay']")).click();
    }
    
    @Then("^user close the browser$")
    public void user_close_the_browser() throws Throwable {
    	Thread.sleep(2000);
    	if(driver!=null){
    		driver.quit();
    	}
    }
    
}