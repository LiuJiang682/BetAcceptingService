package au.com.tabcorp.test.fixture;

import java.time.LocalDateTime;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;

public class TestFixture {

	public static final Bet getBet() {
		Bet bet = new Bet();
		bet.setBetType(BetType.WIN);
		bet.setInvestmentAmount(200d);
		bet.setDateTime(getFutureDate());
		return bet;
	}
	
	public static final LocalDateTime getFutureDate() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.plusDays(1);
	}
	
	public static final LocalDateTime getPastDate() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.minusDays(1);
	}
	
	public static final BetTypeTotal getBetTypeTotal() {
		return new BetTypeTotal(BetType.WIN, 200d);
	}

	public static CustomerTotal getCustomerTotal() {
		return new CustomerTotal(1, 200d);
	}
	
}
