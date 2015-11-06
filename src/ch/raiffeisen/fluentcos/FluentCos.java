package ch.raiffeisen.fluentcos;

import static org.junit.Assert.fail;


public final class FluentCos {
	
	public static boolean nop() {
		return true;
	}
	
	private FluentCos() {
		// Toolsklasse
	}
	
	public static Given given(String text, boolean mapped) {
		System.out.println();
		return new Given("given: ", text, mapped);
	}
	
	public static Given given(String text) {
		return given(text, false);
	}
	
	public static class Given {
		private Given(String prefix, String text, boolean mapped) {
			System.out.println(prefix + text);
			if (!mapped) {
				fail("The statement '" + prefix + text + "' is not mapped.");
			}
		}
		
		public Given and(String text) {
			return and(text, false);
		}
		
		public Given and(String text, boolean mapped) {
			return new Given("    and: ", text, mapped);
		}
		
		public When when(String text) {
			return when(text, false);
		}
		
		public When when(String text, boolean mapped) {
			return new When("  when: ", text, mapped);
		}
	}
	
	public static class When {
		private When(String prefix, String text, boolean mapped) {
			System.out.println(prefix + text);
			if (!mapped) {
				fail("The statement '" + prefix + text + "' is not mapped.");
			}
		}
		
		public When and(String text) {
			return and(text, false);
		}
		
		public When and(String text, boolean mapped) {
			return new When("    and: ", text, mapped);
		}
		
		public Then then(String text) {
			return then(text, false);
		}
		
		public Then then(String text, boolean mapped) {
			return new Then("  then: ", text, mapped);
		}
	}
	
	public static class Then {
		private Then(String prefix, String text, boolean mapped) {
			System.out.println(prefix + text);
			if (!mapped) {
				fail("The statement '" + prefix + text + "' is not mapped.");
			}
		}
		
		public Then and(String text) {
			return and(text, false);
		}
		
		public Then and(String text, boolean mapped) {
			return new Then("    and: ", text, mapped);
		}
	}
}
