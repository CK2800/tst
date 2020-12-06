import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumTest
{
    static String url = "https://sqengineer.com/practice-sites/practice-tables-selenium/";
    static WebDriver webDriver;

    @BeforeAll
    public void init()
    {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        webDriver = new FirefoxDriver(options);
    }
    @BeforeEach
    public void setup()
    {
        webDriver.get(url);
    }

    @Test
    public void mustReturnFirstRowOfFirstTable() {
        // Arrange
        var expected = "Selenium,Open Source,https://www.seleniumhq.org/";

        // Act
        String actual = String.join(",", webDriver.findElement(By.xpath("//*[@id='table1']/tbody/tr[2]"))
                .findElements(By.tagName("td"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()));

        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void mustReturnEachRowOfFirstTable() {
        // Arrange
        var expected = "AUTOMATION TOOL,TYPE,LINK,Selenium,Open Source,https://www.seleniumhq.org/,UFT,Commercial,Unified Functional Tester,Ranorex,Commercial,https://www.ranorex.com/,TestComplete,Commercial,Test Complete";

        // Act
        String actual = String.join(",", webDriver.findElements(By.xpath("//*[@id='table1']/tbody/tr/*[self::td or self::th]"))
                //.findElements(By.xpath("//tr/*[self::td or self::th]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()));


        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void mustNavigateFirstURL() {
        // Arrange
        var expected = "https://www.selenium.dev/";
        webDriver.findElement(By.xpath("//*[@id='table1']/descendant::a[1]")).click();
        
        // Act
        var actual = webDriver.getCurrentUrl();

        // Assert
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("anchorIndexAndUrl")
    public void mustNavigateAllURLs(int index, String url)
    {
        // Arrange
        var expected = url;
        webDriver.findElement(By.xpath(String.format("//*[@id='table1']/descendant::a[%s]", index))).click();

        // Act
        var actual = webDriver.getCurrentUrl();

        // Assert
        assertEquals(expected, actual);
    }

    private static Stream anchorIndexAndUrl()
    {
        return Stream.of(
                Arguments.of(1, "https://www.selenium.dev/"),
                Arguments.of(2, "https://www.microfocus.com/en-us/products/uft-one/overview"),
                Arguments.of(3, "https://www.ranorex.com/"),
                Arguments.of(4, "https://smartbear.com/product/testcomplete/overview/")
        );
    }


    @ParameterizedTest
    @MethodSource("setTableCellText")
    public void mustSelectSportsCellsFromTable2(String tableCellText) {
        // Arrange
        var expected = tableCellText;


        // Act
        String actual = webDriver.findElement(By.xpath(String.format("//*[@id='table2']//td[contains(text(),'%s')]", tableCellText))).getText();

        // Assert
        assertEquals(expected, actual);
    }

    private static Stream setTableCellText()
    {
        return Stream.of(Arguments.of("Table Tennis"),
                         Arguments.of("Badminton"),
                         Arguments.of("Cricket"),
                         Arguments.of("Volley ball"));
    }

    @Test
    public void mustLoadTenUrlsOfCatVideos() throws InterruptedException
    {
        // Arrange
        var expected = 10;

        webDriver.get("https://www.youtube.com/results?search_query=cats");
        // Q&D wait for page load.
        System.out.println("Loading...");
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));
        System.out.println("...done!");

        // Act
        var result = webDriver.findElements(By.xpath("//*[@id='thumbnail']"))  //By.xpath("//*[@id='thumbnail']//@href"));
                .stream()
                .limit(10)
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // Assert
        assertEquals(expected, result.size());
    }

    @Test
    public void mustWriteALogOfTenCatVideos() throws IOException
    {
        // Arrange
        boolean expected = true;
        webDriver.get("https://www.youtube.com/results?search_query=cats");
        // Q&D wait for page load.
        System.out.println("Loading...");
        WebDriverWait wait = new WebDriverWait(webDriver, 100);
        wait.until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));
        System.out.println("...done!");
        var result = webDriver.findElements(By.xpath("//*[@id='thumbnail']"))
                .stream()
                .limit(10)
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());

        // Act.
        boolean actual = saveVideoLog(result);

        // Assert
        assertTrue(actual);

    }

    public boolean saveVideoLog(List<String> strings) throws IOException
    {
        try
        {
            FileWriter fileWriter = new FileWriter(String.format("videolog-%s.txt", System.currentTimeMillis()));
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < strings.size(); i++)
            {
                stringBuilder.append(strings.get(i));
                if (i <= strings.size()-2)
                    stringBuilder.append("\n");
            }

            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
            fileWriter.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }

}
