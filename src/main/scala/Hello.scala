package hello

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.{By, WebDriver, WebElement, Alert}
import org.openqa.selenium.support.ui.{WebDriverWait, ExpectedConditions}
import java.util.concurrent.TimeUnit

/**
 * Created by june on 11/11/14.
 */
object Hello {

  def test() = {

    System.setProperty("webdriver.chrome.driver", "/Users/june/workspace/sbt_skelton/drivers/chromedriver")
    val capabilities:DesiredCapabilities = DesiredCapabilities.chrome()

    val options:ChromeOptions = new ChromeOptions()
    options.addArguments("-incognito")
    capabilities.setCapability("chrome.switches", options)
    val chrome:WebDriver = new ChromeDriver(capabilities)
    val baseUrl = "http://naver.com/"

    chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)
    chrome.get(baseUrl + "/")
    val wait:WebDriverWait = new WebDriverWait(chrome, 10000)
    //wait.withMessage("Your desired Message")


    wait.until(ExpectedConditions.visibilityOf(chrome.findElement(By.id("query")))).clear()
    wait.until(ExpectedConditions.visibilityOf(chrome.findElement(By.id("query")))).sendKeys("바보")

    wait.until(ExpectedConditions.visibilityOf(chrome.findElement(By.id("search_btn")))).click()

    wait.until(ExpectedConditions.visibilityOf(chrome.findElement(By.cssSelector("a.sh_movie_link > strong")))).click()
    chrome.get("http://daum.net")
    wait.until(ExpectedConditions.visibilityOf(chrome.findElement(By.id("query")))).clear()
    wait.until(ExpectedConditions.visibilityOf(chrome.findElement(By.id("query")))).sendKeys("고기")

    chrome.close()

  }

  def main(args: Array[String]) {
    test()
  }




}
