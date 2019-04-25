package au.com.tabcorp.test.fixture;

import java.text.SimpleDateFormat;
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
	
	public static final String getFutureDate() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, ++day);
		return sdf.format(calendar.getTime());
	}
	
	public static final String getPastDate() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, --day);
		return sdf.format(calendar.getTime());
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
