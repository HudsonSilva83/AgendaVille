package br.ce;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgendarSalaoTest {

	private WebDriver driver;
	private DSLTest dsl;

	@BeforeEach
	public void inicializa() {

		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver.get("https://www.meucondominio.com.br/Home/Login");
		dsl = new DSLTest(driver);
		// baixar:
		// https://chromedriver.storage.googleapis.com/index.html?path=96.0.4664.45/
		// File file = new File("D:\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

	}

	@Test
	public void Teste() throws EmailException, InterruptedException {

		// org.junit.Assert.assertEquals("Acessar o site do seu condomínio",
		// dsl.obterTitulo());
		dsl.escrever("Usuario", "hu.psilva@gmail.com");
		String nome = dsl.obterValor("Usuario");
		// org.junit.Assert.assertEquals("hu.psilva@gmail.com", nome);
		dsl.escrever("Senha", "hpereira7");

		dsl.clicarBotao("button[type=\"submit\"]");
		// dsl.clicarBotao("a[href=\"/villehungria/ReservaDeEspacos\"][class=\"m-subCard
		// t-reservas\"]");
		Thread.sleep(2000);
		dsl.clicarBotao("a[class=\"m-subCard t-reservas\"]");

		// driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		Thread.sleep(2000);
		// rever esse abaixo

		dsl.clicar(
				"body > div.l-body__main > div > div > section > article > section > div > div.m-mainsection__body__wrapper--noBottom > div > div > div > a > div.m-cardA__header__title");

		driver.manage().timeouts().implicitlyWait(190, TimeUnit.SECONDS);

		String mes = dsl.obterTexto("div [class=\"m-calendarium__container\"] [class= \"m-calendarium__header\"]");

		AcentacaoTest remove = new AcentacaoTest();
		String mestratado = remove.trata(mes);

		for (int indice = 0; indice != 1;) {

			mes = dsl.obterTexto("div [class=\"m-calendarium__container\"] [class= \"m-calendarium__header\"]");
			remove = new AcentacaoTest();
			mestratado = remove.trata(mes);

			if (mestratado.equals("ABRIL 2022")) {

				indice = 1;
				mes = dsl.obterTexto("div [class=\"m-calendarium__container\"] [class= \"m-calendarium__header\"]");
				remove = new AcentacaoTest();
				mestratado = remove.trata(mes);
				System.out.println("parei aqui " + mestratado);

			} else {
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("button[class=\"m-calendarium__header__arrow--right\"]")).click();
			}

		}

//		switch (tratado) {
//
//		case "DEZEMBRO 2021":
//
//			driver.findElement(By.cssSelector("button[class=\"m-calendarium__header__arrow--right\"]")).click();
//
//		case "JANEIRO 2022":
//
//			//driver.findElement(By.cssSelector("button[class=\"m-calendarium__header__arrow--right\"]")).click();
//			break;
//
//		case "FEVEREIRO 2022":
//
//			//driver.findElement(By.cssSelector("button[class=\"m-calendarium__header__arrow--right\"]")).click();
//
//		case "MARCO 2022":
//
//			//driver.findElement(By.cssSelector("button[class=\"m-calendarium__header__arrow--right\"]")).click();
//
//			//mes = (driver
//					//.findElement(By
//						//.cssSelector("div [class=\"m-calendarium__container\"] [class= \"m-calendarium__header\"]"))
//					//.getText());
//			
//			 tratado = remove.trata(mes);
//
//			System.out.println(tratado);
//
//			// testando o projeto
//			//break;
//
//		case "ABRIL 2022":
//
////			mes = (driver
////					.findElement(By
////							.cssSelector("div [class=\"m-calendarium__container\"] [class= \"m-calendarium__header\"]"))
////					.getText());
////
////			System.out.println(mes);
//
//			//break;
//
//		}
////
//		// String c = "3";
//		// clicar na data para agendar

		mes = (driver
				.findElement(
						By.cssSelector("div [class=\"m-calendarium__container\"] [class= \"m-calendarium__header\"]"))
				.getText());

		mestratado = remove.trata(mes);

		String dia = driver.findElement(By.cssSelector(
				"div [class=\"m-calendarium__container\"] [class=\"m-calendarium__monthDays\"] li:nth-child(21)"))
				.getText();

		String motivo = driver.findElement(By.cssSelector(
				"div [class=\"m-calendarium__container\"] [class=\"m-calendarium__monthDays\"] li:nth-child(21)"))
				.getAttribute("title");

		AcentacaoTest tratandoM = new AcentacaoTest();

		String motivoT = tratandoM.trata(motivo);

		// Assertions.assertEquals("Ja existe outra reserva neste espaco!", motivoT);

		if (motivoT.equals("Ja existe outra reserva neste espaco!")) {

			System.out.println(motivoT);

			NotificarPorEmailTest enviarEmail = new NotificarPorEmailTest();

			enviarEmail.EnviarEmail(
					"Infelizmente nao foi possivel agendar para a Anna Clara o salao de festas para a data: " + dia
							+ "/04/2022, pois " + motivoT + ".");

		} else if (motivoT.equals("Data nao atinge a antecedencia maxima para reserva")) {

			System.out.println(motivoT);

			// NotificarPorEmailTest enviarEmail = new NotificarPorEmailTest();
			// enviarEmail.EnviarEmail(
			// 		"O Agendamento do salao de Festa do Ville Hungria para Anna Clara ainda nao foi possivel fazer, estamos batalhando pra conseguir de forma automatica....Vamos aguardar pois a "
			// 				+ motivoT + ("."));

		} else {

			System.out.println("VAMOS AGENDAR");
			driver.findElement(By.cssSelector(
					"div [class=\"m-calendarium__container\"] [class=\"m-calendarium__monthDays\"] li:nth-child(21)"))
					.click();
			// agendar perigoso
			driver.findElement(By.cssSelector("button[class=\"m-button--md\"]")).click();

			// desistir de agendar
			// driver.findElement(By.cssSelector("button[class=\"m-buttonlink--md
			// s-button-clean\"]")).click();

			// Enviar o email de agendado

			NotificarPorEmailTest enviarEmail = new NotificarPorEmailTest();
			enviarEmail.EnviarEmail("Agendado o salao de Festa do Ville Hungria para Anna Clara para o dia : " + dia
					+ "/04/2022, Parabens para a minha pessoa (Robo) pela persistencia que eu tive para com voce!.. agendamento realizado com sucesso para o dia 18/04/2022");

		}

//		} else if (texto.getText()==""){
//
//			

	}

	@AfterEach
	public void finalizar() {

		driver.quit();

	}
}
