package br.sp.mdenys.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import br.sp.mdenys.core.BasePage;
import br.sp.mdenys.core.DriverFactory;

public class ShoestockPage extends BasePage{
	
	protected static String nomeCorrigido;
	protected static String nomeProdutoCarrinho;


	/**
	 * metodo para preencher campo de busca  
	 */
	public static void searchProduct() {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		write(By.xpath("//*[@id=\"search-input\"]"), "sapato social masculino");
	}
	/**
	 * metodo para clicar no icone de busca 
	 */
	public static void searchclick() {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		click(By.xpath("//*[@id=\"header-content\"]/header/div/div/section[2]/section/form/div/button"));
	}
	
	/**
	 * metodo para clicar no primeiro sapato da busca buscar o nome, corrigir tirando o Nome: para usar de comparacao
	 */
	public static void clicaPrimeiroSapato() {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		click(By.xpath("//*[@id=\"item-list\"]/div[1]/div[1]/div[1]/a/img"));
		String nomeProduto = coletaNomeProduto("//*[@id=\"features\"]/ul/li[1]");
		nomeCorrigido = replaceString(nomeProduto, "Nome: ", "");
		System.out.println(nomeCorrigido);
		
		
	}
	/**
	 * metodo para coletar o nome do produto no carrinho e utiliza o metodo para comparacao  
	 */
	public static void validarProdutoCarrinho() {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		nomeProdutoCarrinho = coletaNomeProduto("//html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div[1]/div/div/h3");
		System.out.println(nomeProdutoCarrinho);
		comparaNomeProduto();
		
		
	}
	/**
	 * metodo para comparar o nome do produto antes e depois de entrar no carrinho
	 */
	public static void comparaNomeProduto() {
		if (nomeCorrigido.equals(nomeProdutoCarrinho)) {
			System.out.println("Nome validado com sucesso");
		} else {
			System.out.println("Nome n�o validado com sucesso");
		}
	}
	
	/**
	 * metodo para coletar o nome 
	 */
	public static String coletaNomeProduto(String nomeProduto) {
		//return obterTexto(By.cssSelector(".short-description"));
		return obterTexto(By.xpath(nomeProduto));

	}
	
	/**
	 * metodo para clicar no botao comprar
	 */
	public static void clicaBotaoComprar() {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		click(By.xpath("//*[@id=\"buy-button-now\"]"));
		
	}
	/**
	 * metodo com hash para armazenar os xpaths de cada tamanho de sapato
	 */
	public static String comboTamanho(String tamanho) {
			Map<String, String> xpaths = new HashMap<String, String>();
			xpaths.put("38", "//*[@id=\"buy-box\"]/section[2]/div/ul/li[1]/a");
			xpaths.put("39", "//*[@id=\"buy-box\"]/section[2]/div/ul/li[2]/a");
			xpaths.put("40", "//*[@id=\"buy-box\"]/section[2]/div/ul/li[3]/a");
			xpaths.put("41", "//*[@id=\"buy-box\"]/section[2]/div/ul/li[4]/a");
			xpaths.put("42", "//*[@id=\"buy-box\"]/section[2]/div/ul/li[5]/a");
	
			return xpaths.get(tamanho.toLowerCase()).toString();
		}
	/**
	 * metodo que seleciona o tamanho do sapato
	 */
	public static void selecionaTamanhoSapato() {
		String xpathTamanho = comboTamanho("40");
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		click(By.xpath(xpathTamanho));
	}
	
	
	
}
