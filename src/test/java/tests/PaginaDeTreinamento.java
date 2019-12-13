package tests;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PaginaDeTreinamento extends Dsl {

	public PaginaDeTreinamento(WebDriver driver) {
		super(driver);

	}

	//static WebDriver driver;

	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\f799396\\Desktop\\Estudos\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get(
				"file:///" + System.getProperty("user.dir") + "/src/test/resources/paginaTreinamento/componentes.html");

		driver.manage().window().maximize();

	}

	@Test
	public void test() {

		digitar(By.id("elementosForm:nome"), "testeIzac");

		assertEquals("TestIzac", obterCampoValue("elementosForm:nome"));

		digitar(By.id("elementosForm:sobrenome"), "TestJunior");

		clicar(By.id("elementosForm:sexo:0"));
		assertTrue(validarClick(By.id("elementosForm:sexo:0")));

		clicar(By.id("elementosForm:comidaFavorita:0"));
		assertTrue(validarClick(By.id("elementosForm:comidaFavorita:0")));

		selecionarCombo(By.id("elementosForm:escolaridade"), "Superior");
		// assertEquals(8, selecao.getOptions().size());

		selecionarCombo(By.id("elementosForm:esportes"), "Karate");
		selecionarCombo(By.id("elementosForm:esportes"), "Natacao");

		digitar(By.id("elementosForm:sugestoes"), "TESTE DE TEXTO NA CAIXA DE SUGESTÃO\n NA LINHA DE BAIXO");

		clicar(By.id("buttonSimple"));
		assertEquals("Obrigado!", obterCampoValue("buttonSimple"));

		clicar(By.id("Voltar"));

		assertTrue(obterTexto(By.id("body")).contains("Campo de Treinamento"));
		assertThat(obterTexto(By.tagName("body")), containsString("Campo de Treinamento"));
		assertEquals("Campo de Treinamento", obterTexto(By.tagName("h3")));
		assertEquals("Cuidado onde clica, muitas armadilhas...", obterTexto(By.className("facilAchar")));

		clicar(By.id("elementosForm:cadastrar"));

		assertEquals("Campo de Treinamento", obterTexto(By.tagName("h3")));
		assertEquals("Cuidado onde clica, muitas armadilhas...", obterTexto(By.className("facilAchar")));

		assertEquals("Cadastrado!", obterTexto(By.xpath("//*[@id=\"resultado\"]/span")));
		assertEquals("Nome: TestIzac", obterTexto(By.id("descNome")));
		assertEquals("Sobrenome: TestJunior", obterTexto(By.id("descSobrenome")));
		assertEquals("Sexo: Masculino", obterTexto(By.id("descSexo")));
		assertEquals("Comida: Carne", obterTexto(By.id("descComida")));
		assertEquals("Esportes: Natacao Karate", obterTexto(By.id("descEsportes")));
		assertEquals("Sugestoes: TESTE DE TEXTO NA CAIXA DE SUGESTÃO NA LINHA DE BAIXO",
				obterTexto(By.id("descSugestoes")));
		assertEquals("Escolaridade: superior", obterTexto(By.id("descEscolaridade")));

	}

	// @Test
	public void variosAlert() {

		clicar(By.id("alert"));
		alertAceita();

		clicar(By.id("confirm"));
		assertEquals("Confirm Simples", alertAceita());
		alertAceita();
		alertAceita();
		clicar(By.id("confirm"));
		alertNaoAceita();
		alertAceita();

		clicar(By.id("prompt"));
		assertEquals("Digite um numero", alertAceita());
		alertEscreverAceita("123456");
		alertAceita();
		alertAceita();
		alertAceita();

	}

	// @Test
	public void deveInteragirFrame() {

		driver.switchTo().frame("frame1");

		clicar(By.id("frameButton"));

		alertAceita();
		assertEquals("Frame OK!", alertAceita());
		alertAceita();

		// VOLTAR AO FRAME ANTERIOR(PRINCIPAL)
		// driver.switchTo().defaultContent();
	}

	// @Test
	public void abrirPoupUp() {

		clicar(By.id("buttonPopUpEasy"));

		driver.switchTo().window("Popup");

		digitar(By.tagName("textarea"), "Deu certo");

		driver.close();
		driver.switchTo().window("");
	}

	// @Test
	public void manipularJanelaSemTitulo() {

		clicar(By.id("buttonPopUpHard"));

		System.out.println(driver.getWindowHandles());

		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);

		digitar(By.tagName("textarea"), "Deu certo?");

		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

		digitar(By.tagName("textarea"), "Deu certo NOVAMENTE?");
	}

	@After
	public void tearDown() {
		driver.quit();

	}
}