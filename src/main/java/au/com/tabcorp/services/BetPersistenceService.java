package au.com.tabcorp.services;

import java.time.LocalDateTime;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetCount;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;

public interface BetPersistenceService {

	void save(Bet bet);

	BetTypeTotal getTotalInvestmentByBetType(final BetType betType);

	CustomerTotal getTotalInvestmentByCustomer(final Long customerId);

	BetCount getBetCountByBetType(final BetType betType);

	Long getBetCountByRange(final LocalDateTime from, final LocalDateTime to);

}
