package br.ce;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSLTest {
	
	private WebDriver driver;

	
	
	public DSLTest(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	
	public void escrever(String id_campo, String texto) {
		
		
		driver.findElement(By.id(id_campo)).sendKeys(texto);
		
	}
	
	public void clicarBotao(String css) {
		
		driver.findElement(By.cssSelector(css)).click();
		
	}
	
	public String obterValor(String id_campo) {
		
		return driver.findElement(By.id(id_campo)).getAttribute("value");
		
	}

	public String obterTexto(String css) {
		
		return driver.findElement(By.cssSelector(css)).getText();
		
	}
	
	
	public String obterTitulo() {
		
		return driver.getTitle();
		
	}
	
	public void clicar(String x) {
		
		driver.findElement(By.cssSelector(x)).click();
		
	}
	
	

}
