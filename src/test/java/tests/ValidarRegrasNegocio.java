
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ValidarRegrasNegocio extends Dsl{

	public ValidarRegrasNegocio(WebDriver driver) {
		super(driver);
		
	}

	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\f799396\\Desktop\\Estudos\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get(
				"file:///" + System.getProperty("user.dir") + "/src/test/resources/paginaTreinamento/componentes.html");

		driver.manage().window().maximize();

	}

	@Test
	public void validarNomeSobrenomeRegraNegocio() {

		clicar(By.id("elementosForm:cadastrar"));
		Alert alert = driver.switchTo().alert();
		assertEquals("Nome eh obrigatorio", alert.getText());

	}

	@Test
	public void validarSobrenomeRegraNegocio() {

		digitar(By.id("elementosForm:nome"), "TestIzac");
		
		clicar(By.id("elementosForm:cadastrar"));
		Alert alert = driver.switchTo().alert();
		assertEquals("Sobrenome eh obrigatorio", alert.getText());

	}

	@Test
	public void validarSexoRegraNegocio() {

		digitar(By.id("elementosForm:nome"), "TestIzac");
		assertEquals("TestIzac", obterCampoValue("elementosForm:nome"));

		digitar(By.id("elementosForm:sobrenome"), "TestJunior");

		clicar(By.id("elementosForm:cadastrar"));
		Alert alert = driver.switchTo().alert();
		assertEquals("Sexo eh obrigatorio", alert.getText());

	}

	@Test
	public void validarComidaVegetarianaRegraNegocio() {

		digitar(By.id("elementosForm:nome"), "TestIzac");
		
		digitar(By.id("elementosForm:sobrenome"),"TestJunior");

		clicar(By.id("elementosForm:sexo:0"));
		assertTrue(validarClick(By.id("elementosForm:sexo:0")));

		clicar(By.id("elementosForm:comidaFavorita:0"));
		assertTrue(validarClick(By.id("elementosForm:comidaFavorita:0")));

		clicar(By.id("elementosForm:comidaFavorita:3"));
		assertTrue(validarClick(By.id("elementosForm:comidaFavorita:3")));

		clicar(By.id("elementosForm:cadastrar"));
		Alert alert = driver.switchTo().alert();
		assertEquals("Tem certezaque que voce eh vegetariano?", alert.getText());

	}

	@Test
	public void validarComidaEsporteRegraNegocio() {

		digitar(By.id("elementosForm:nome"), "TestIzac");
		assertEquals("TestIzac", obterCampoValue("elementosForm:nome"));

		digitar(By.id("elementosForm:sobrenome"),"TestJunior");

		clicar(By.id("elementosForm:sexo:0"));
		
		clicar(By.id("elementosForm:comidaFavorita:0"));
		
		selecionarCombo(By.id("elementosForm:esportes"), "Karate");
		selecionarCombo(By.id("elementosForm:esportes"), "O que eh esporte?");
		
		clicar(By.id("elementosForm:cadastrar"));
		Alert alert = driver.switchTo().alert();
		assertEquals("Voce faz esporte ou nao?", alert.getText());

	}

	@After
	public void tearDown() {

		driver.quit();
	}
}
