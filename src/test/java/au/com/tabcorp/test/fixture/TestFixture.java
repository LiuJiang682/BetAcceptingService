package au.com.tabcorp.test.fixture;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetType;

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
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
}
