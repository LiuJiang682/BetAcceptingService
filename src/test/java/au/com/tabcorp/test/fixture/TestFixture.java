package au.com.tabcorp.test.fixture;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetType;

public class TestFixture {

	public static final Bet getBet() {
		Bet bet = new Bet();
		bet.setBetType(BetType.WIN);
		return bet;
	}
}
