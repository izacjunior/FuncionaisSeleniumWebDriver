package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

	static WebDriver driver;

	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\f799396\\Desktop\\Estudos\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("http://www.google.com.br");

		driver.manage().window().maximize();

		assertEquals("Google", driver.getTitle());

	}

	@Test
	public void test() {

	}

	@After
	public void tearDown() {
		driver.quit();

	}
}
