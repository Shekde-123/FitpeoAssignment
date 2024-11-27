package com.FitPeo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assignment {
	
	static WebDriver driver= new ChromeDriver();
	public static Actions action = new Actions(driver);
	public static SoftAssert softassert = new SoftAssert();
	
	@BeforeSuite
	public void DataSetup() {
		driver.navigate().to("https://www.fitpeo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	
	@AfterSuite
	public void DataTearDown() {
		driver.close();
	}
	
	@BeforeTest
	public void BfrTest() {
		driver.get("https://www.fitpeo.com/revenue-calculator");
	}

	@Test(testName = "Revenue Calculator Page",priority = 1)
	public void test1() {
		driver.findElement(By.linkText("Revenue Calculator")).click();
	}
		
	@Test(testName = "Slider section",priority = 2)
	public void test2() {
		WebElement Scrltoelemt = driver.findElement(By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']"));
		action.scrollToElement(Scrltoelemt).perform();
	}	
	
	@Test(testName = "Adjust Slider",priority = 3)
	public void test3() {
	  int offset = (int) ((820.0 / 2000.0) * 300);  // Desired value of 820, range of 2000, width of 300
      WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
	  action.moveToElement(slider).clickAndHold().moveByOffset(offset, 0).release().perform();
	  }

	@Test(testName = "field value",priority = 4)
	public void test4() throws InterruptedException {
		driver.get("https://www.fitpeo.com/revenue-calculator");
		WebElement fieldvalue = driver.findElement(By.xpath("//input[@type='number']"));
		fieldvalue.click();
		for(int i=0;i<3;i++) {
		fieldvalue.sendKeys(Keys.BACK_SPACE);
		}
		fieldvalue.sendKeys("560");
	}
	
	@Test(testName = "validate value",priority = 5)
	public void test5() {
		String actualvalue="560";
		String expectedvalue = driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value");
		softassert.assertEquals(actualvalue,expectedvalue);
		softassert.assertAll();
	}
	
	@Test(testName = "CPT codes",priority = 6)
	public void test6() throws InterruptedException {
		driver.get("https://www.fitpeo.com/revenue-calculator");
    	WebElement fieldvalue = driver.findElement(By.xpath("//input[@type='number']"));
		fieldvalue.click();
		for(int i=0;i<3;i++) {
		fieldvalue.sendKeys(Keys.BACK_SPACE);
		}
		Thread.sleep(2000);
		fieldvalue.sendKeys("820");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/label/span[1]/input")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/label/span[1]/input")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[3]/label/span[1]/input")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[8]/label/span[1]/input")).click();
		}
	
    @Test(testName = "validate amount",priority = 7)
	public void test7(){
    	String actualamount="$110700";
		String expectedamount = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/div[3]/p[2]")).getText();   
		softassert.assertEquals(actualamount, expectedamount);
		softassert.assertAll();
    }
}
	
	  
	
	
	
	
	