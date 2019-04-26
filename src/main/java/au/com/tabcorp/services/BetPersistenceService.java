package au.com.tabcorp.services;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;

public interface BetPersistenceService {

	void save(Bet bet);

	BetTypeTotal getTotalInvestmentByBetType(final BetType betType);

}
