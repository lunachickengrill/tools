package eu.vrtime.bootwicketappthree;

import org.junit.Test;

import eu.vrtime.bootwicketappthree.model.BankAccount;

public class BuilderTest extends AbstractTestBase {

	@Test
	public void builderTest() {
		BankAccount account = new BankAccount.Builder(new Long("12345")).withOwner("asdf").atBranch("qwer")
				.openingBalance(10000).atRate(2.5).build();
	}

}
