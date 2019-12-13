package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Dsl {

	WebDriver driver;

	public Dsl(WebDriver driver) {
		this.driver = driver;
	}

	public void digitar(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public String obterCampoValue(String id) {

		return driver.findElement(By.id(id)).getAttribute("value");

	}

	public void clicar(By by) {
		driver.findElement(by).click();
	}

	public boolean validarClick(By by) {
		return driver.findElement(by).isSelected();

	}

	public void selecionarCombo(By by, String texto) {
		Select selecao = new Select(driver.findElement(by));
		selecao.selectByVisibleText(texto);

	}

	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}

	public String alertAceita() {
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		alert.accept();
		return texto;

	}

	public String alertNaoAceita() {
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		alert.dismiss();
		;
		return texto;

	}

	public void alertEscreverAceita(String texto) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(texto);
		alert.accept();

	}
}
