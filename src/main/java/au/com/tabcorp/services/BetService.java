package au.com.tabcorp.services;

import java.math.BigDecimal;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetCount;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;
import au.com.tabcorp.model.DateRange;

public interface BetService {

	void placeBet(final Bet bet);

	BetTypeTotal getTotalInvestmentByBetType(final BetType betType);

	CustomerTotal getTotalInvestmentByCustomer(final Long customerId);

	BetCount getBetCountByBetType(BetType betType);

	BigDecimal getAveragePerHourBetween(final DateRange dateRange);

}
