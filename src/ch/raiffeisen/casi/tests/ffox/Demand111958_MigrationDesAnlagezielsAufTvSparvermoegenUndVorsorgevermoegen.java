package ch.raiffeisen.casi.tests.ffox;

import static ch.raiffeisen.fluentcos.FluentCos.given;
import static ch.raiffeisen.fluentcos.FluentCos.nop;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.*;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Conditions of Satisfaction für Demand_111958_FFOX_ANL_Migration des
 * Anlageziels auf TV Spar- und Vorsorgevermögen
 * 
 * @author Damian Hofmann <damian.hofmann@raiffeisen.ch>
 */
public class Demand111958_MigrationDesAnlagezielsAufTvSparvermoegenUndVorsorgevermoegen {

	/**
	 * Es muss fachlich sichergestellt sein, dass ...
	 * Lorem Ipsum
	 */
	@Test
	public final void coc_1_Hierarchische_Auflistung_und_Bezeichnung_des_Default_Teilvermögens_Kontovermögen() {
			given("Kunde XY für Beratung ausgewählt", selectCustomer("Peter Müller"))
				.and("Kunde XY besitzt Produkte des TV's 'Kontovermögen'", initCustomerAssets())
			.when("Berater für Kunde XY den Menüpunkt 'Anlegen Finfox' anklickt", clickMenu())
			.then("Wird das TV 'Kontovermögen' an erster Stelle gezeigt", assertAccountFirst())
				.and("Der Titel des TV beinhaltet eine eindeutige Nummer", assertUniquePortfolioId())
				.and("Den TV-Typ 'Kontovermögen'", nop());
	}
		
	
	/**
	 * Es muss fachlich sichergestellt sein, dass ...
	 * Lorem Ipsum
	 */
	@Ignore // Future CoS. Planned for Sprint 4264324532
	@Test
	public final void cos_2_Hierarchische_Auflistung_und_Bezeichnung_des_Default_Teilvermögens_Anlagevermögen() {
			given("Kunde XY für Beratung ausgewählt")
				.and("Kunde XY besitzt Produkte des TV's 'Anlagevermögen'")
			.when("Berater für Kunde XY den Müpunkt 'Anlegen Finfox' anklickt")
			.then("Wird das TV 'Anlagevermögen' an zweiter Stelle gezeigt")
				.and("Der Titel des TV beinhaltet eine eindeutige Nummer")
				.and("Den TV-Typ 'Anlagevermögen'");
	}
	
	/*
	 * Everything below this line belongs to the developers
	 */
	
	private  boolean selectCustomer(String name) {
		customer = new Customer();
		customer.name = name;
		return true;
	}

	private boolean initCustomerAssets() {
		customerAssets = new LinkedList<Asset>();
		customerAssets.add(createAsset("Peter Müller", "Vorsorgekonto", "provision"));
		customerAssets.add(createAsset("Peter Müller", "Sparkonto", "account"));
		customerAssets.add(createAsset("Karolina Cantieni", "Fonssparkonto", "invest"));
		return true;
	}

	private Asset createAsset(String customer, String name, String type) {
		Asset asset = new Asset();
		asset.customer = customer;
		asset.name = name;
		asset.type = type;
		return asset;
	}

	private boolean clickMenu() {
		portfolios = customerAdvisoryDataServerGetPortfolios(customer);
		return true;
	}

	private boolean assertAccountFirst() {
		Portfolio portfolio = portfolios.get(0);
		assertEquals("account", portfolio.type);
		return true;
	}

	private boolean assertUniquePortfolioId() {
		int id = portfolios.get(0).id;
		for (int i=1; i<portfolios.size(); i++) {
			assertNotEquals(id, portfolios.get(i).id);
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Just a few dummy models for this showcase.
	 */
	
	private List<Portfolio> customerAdvisoryDataServerGetPortfolios(Customer customer) {
		Set<String> portfolioTypes = new HashSet<String>();
		for (Asset asset : customerAssets) {
			if (!asset.customer.equals(customer.name)) {
				continue;
			}
			
			portfolioTypes.add(asset.type);
		}
		
		List<Portfolio> portfolios = new ArrayList<Portfolio>();
		for (String portfolioType : portfolioTypes) {
			Portfolio portfolio = new Portfolio();
			portfolio.id = new Random().nextInt();
			portfolio.name = "Teilvermögen";
			portfolio.type = portfolioType;
			portfolios.add(portfolio);
		}
		return portfolios;
	}
	
	private Customer customer;
	private List<Asset> customerAssets = new ArrayList<Asset>();
	private List<Portfolio> portfolios;
	
	private static class Customer {
		private String name;
	}
	
	private static class Asset {
		private String customer;
		private String name;
		private String type;
	}
	
	private static class Portfolio {
		private int id;
		private String name;
		private String type;
	}
}
