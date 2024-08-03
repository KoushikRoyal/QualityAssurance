package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.example.Credentials.LMS_URL;
import static org.openqa.selenium.By.*;

public class Main {
    public static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid black;');", element);
    }

    public static void open_browser(){
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new ChromeDriver();
        driver.get(LMS_URL);
        String title = driver.getTitle();
        System.out.println(title);
        driver.quit();
    }

    public static void open_url(String url){
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        String title = driver.getTitle();
        System.out.println(title);
        driver.quit();
    }

    public static void lms_login(){
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new ChromeDriver();
        driver.get(LMS_URL);
        String title = driver.getTitle();
        driver.findElement(name("username")).sendKeys(Credentials.LMS_USERNAME);
        driver.findElement(id("password")).sendKeys(Credentials.LMS_PASSWORD);
        driver.findElement(className("btn-lg")).submit();
        System.out.println(title);
        driver.quit();
    }

    public static void locate_all_locators(){
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new ChromeDriver();
        driver.get(LMS_URL);
        System.out.println(driver.getTitle());
        // Example of highlighting for different locators
        WebElement loginField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement logo = driver.findElement(By.className("span12"));
        WebElement forgotPasswordLink = driver.findElement(By.linkText("Lost password?"));
        WebElement h1Tag = driver.findElement(By.tagName("h1"));
        WebElement partialLink = driver.findElement(By.partialLinkText("ost pass"));
        // Highlighting each element
        highlight(driver, loginField);
        driver.findElement(By.name("password")).sendKeys("welcome");
        highlight(driver, passwordField);
        driver.findElement(By.className("span12"));
        highlight(driver, logo);
        highlight(driver, forgotPasswordLink);
        driver.findElement(By.tagName("h1"));
        highlight(driver, h1Tag);
        highlight(driver,partialLink);
        driver.quit();
    }

    public static void locate_css_selector(){
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://lms.kluniversity.in/login/index.php");
        System.out.println(driver.getTitle());
        WebElement csspathapp=driver.findElement(By.cssSelector("#logoimage"));
        highlight(driver,csspathapp);
    }

    public static void locate_xpath(){
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new ChromeDriver();
        driver.get(LMS_URL);
        System.out.println(driver.getTitle());
        WebElement xpathapp=driver.findElement(By.xpath("//div[@id='wrapper']"));
        highlight(driver,xpathapp);
    }

    public static void html_control() throws InterruptedException {
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.hyrtutorials.com/p/html-dropdown-elements-practice.html");
        Thread.sleep(2000);
        WebElement ideElement = driver.findElement(By.id("ide"));
        Select ideDropDown = new Select(ideElement);
        List<WebElement> ideDropDownOptions = ideDropDown.getOptions();
        for (WebElement option : ideDropDownOptions) {
            System.out.println(option.getText());
        }
        ideDropDown.selectByIndex(0);
        ideDropDown.selectByValue("ij");
        ideDropDown.selectByVisibleText("NetBeans");
        ideDropDown.deselectByVisibleText("IntelliJ IDEA");
        List<WebElement> selectedOptions = ideDropDown.getAllSelectedOptions();
        for (WebElement selectedOption : selectedOptions) {
            System.out.println("selected visible text---" + selectedOption.getText());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner s=new Scanner(System.in);
        while(true){
            System.out.println("Select One option");
            System.out.println("1.opening the browser\n" +
                    "2.opening the given url\n" +
                    "3.open the browser if the credentials are correct\n" +
                    "4.open the url and give responses of the invalid credentials\n" +
                    "5.Implement all locators (static)\n" +
                    "6.Implement CSSSelector(dynamic)\n" +
                    "7.Implement Xpath(dynamic)\n" +
                    "8.Develop all Html Controls including checkbox radio button drop downs(single and multiple) separately\n" +
                    "9.Implement all Alerts(simple alert,confirm,prompt alert)separate programs\n" +
                    "10.Implement window/screenshot after pushing the code into your github account\n"+
                    "11.Close\n");
            System.out.println("Select an Option");
            int t=s.nextInt();
            if(t==1){
               open_browser();
            }
            else if(t==2){
                System.out.println("Enter the  URL you want to open!!");
                String url=s.next();
                open_url(url);
            }
            else if(t==3){
               lms_login();
            }
            else if(t==4){
                WebDriver driver;
                WebDriverManager.edgedriver().setup();
                driver = new ChromeDriver();
                driver.get(LMS_URL);
                String title = driver.getTitle();
                driver.findElement(name("username")).sendKeys(Credentials.LMS_USERNAME);
                driver.findElement(id("password")).sendKeys("123456789");
                driver.findElement(className("btn-lg")).submit();
                System.out.println(driver.getTitle());
                driver.quit();
            }
            else if(t==5){
               locate_all_locators();
            }
            else if(t==6){
               locate_css_selector();
            }
            else if(t==7){
                locate_xpath();
            }
            else if(t==8){
                html_control();
            }
            else if(t==9){
                WebDriver driver;
                WebDriverManager.edgedriver().setup();
                driver = new ChromeDriver();
                System.out.println("1.Simple Alert");
                System.out.println("2.Confirm Alert");
                System.out.println("3.Prompt Alert");
                System.out.println("Select an Option");
                int rr=s.nextInt();
                if(rr==1){
                    driver.manage().window().maximize();
                    driver.get("E:\\OneDrive - K L University\\Academics\\SEMESTER-6\\ST\\STLABWORKSPACE\\Main_Project\\src\\main\\java\\org\\example\\test_alert.html");

                    System.out.println(driver.findElement(By.id("output")).getText());
                    Thread.sleep(2000);
                    driver.findElement(By.id("alertBox")).click();
                    Thread.sleep(2000);
                    System.out.println(driver.switchTo().alert().getText());
                    Thread.sleep(2000);
                    driver.switchTo().alert().accept();
                    Thread.sleep(2000);
                    System.out.println(driver.findElement(By.id("output")).getText());

                }
                else if(rr==2){driver.manage().window().maximize();
                    driver.get("E:\\OneDrive - K L University\\Academics\\SEMESTER-6\\ST\\STLABWORKSPACE\\Main_Project\\src\\main\\java\\org\\example\\test_alert.html");
                    System.out.println(driver.findElement(By.id("output")).getText());
                    Thread.sleep(2000);
                    driver.findElement(By.id("confirmBox")).click();
                    Thread.sleep(2000);
                    System.out.println(driver.switchTo().alert().getText());
                    Thread.sleep(2000);
                    driver.switchTo().alert().accept();
                    Thread.sleep(2000);
                    System.out.println(driver.findElement(By.id("output")).getText());
                    System.out.println(driver.findElement(By.id("output")).getText());
                    Thread.sleep(2000);
                    driver.findElement(By.id("confirmBox")).click();
                    Thread.sleep(2000);
                    System.out.println(driver.switchTo().alert().getText());
                    Thread.sleep(2000);
                    driver.switchTo().alert().dismiss();
                    Thread.sleep(2000);
                    System.out.println(driver.findElement(By.id("output")).getText());
                }
                else{
                    driver.manage().window().maximize();
                    driver.get("E:\\OneDrive - K L University\\Academics\\SEMESTER-6\\ST\\STLABWORKSPACE\\Main_Project\\src\\main\\java\\org\\example\\test_alert.html");
                    System.out.println(driver.findElement(By.id("output")).getText());
                    Thread.sleep(2000);
                    driver.findElement(By.id("promptBox")).click();
                    Thread.sleep(2000);
                    System.out.println(driver.switchTo().alert().getText());
                    Thread.sleep(2000);
                    driver.switchTo().alert().sendKeys("dbr");
                    driver.switchTo().alert().accept();
                    Thread.sleep(2000);
                    System.out.println(driver.findElement(By.id("output")).getText());
                    driver.findElement(By.id("promptBox")).click();
                    Thread.sleep(2000);
                    driver.switchTo().alert().dismiss();
                    Thread.sleep(2000);
                    System.out.println(driver.findElement(By.id("output")).getText());
                    Thread.sleep(2000);

                }
                driver.quit();

            }else if(t==10){
                WebDriver driver;
                WebDriverManager.edgedriver().setup();
                driver = new ChromeDriver();
                driver.get("https://github.com/login");
                TakesScreenshot ts=(TakesScreenshot)driver;
                File file=ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(file, new File("./Screenshots/Image1.png"));
                driver.quit();

            }else if(t==11){
                break;
            }
        }
    }
}