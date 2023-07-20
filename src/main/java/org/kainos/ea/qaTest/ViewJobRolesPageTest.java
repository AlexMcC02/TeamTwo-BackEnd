package org.kainos.ea.qaTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class ViewJobRolesPageTest {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub


        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/job_roles");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        String Header = driver.findElement(By.xpath("//h1[normalize-space()='View Job Roles']")).getText();
        String Title = driver.findElement(By.xpath("//h2[normalize-space()='Jobs']")).getText();
        String NameHeading = driver.findElement(By.xpath("//th[normalize-space()='Name']")).getText();
        String SpecHeading = driver.findElement(By.xpath("//th[normalize-space()='Specification']")).getText();
        String BandIDHeading = driver.findElement(By.xpath("//th[normalize-space()='BandID']")).getText();
        String CapabilityIDHeading = driver.findElement(By.xpath("//th[normalize-space()='CapabilityID']")).getText();


        Assert.assertEquals(Header, "View Job Roles");
        Assert.assertEquals(Title, "Jobs");
        Assert.assertEquals(NameHeading, "Name");
        Assert.assertEquals(BandIDHeading, "BandID");
        Assert.assertEquals(CapabilityIDHeading, "CapabilityID");

        System.out.println(Header);
        System.out.println(Title);
        System.out.println(NameHeading);
        System.out.println(BandIDHeading);
        System.out.println(CapabilityIDHeading);


        driver.quit();
    }
}
