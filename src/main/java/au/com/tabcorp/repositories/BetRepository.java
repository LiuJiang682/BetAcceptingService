package au.com.tabcorp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {

	@Query("SELECT new au.com.tabcorp.model.BetTypeTotal(b.betType, sum(investmentAmount)) FROM Bet b WHERE b.betType = :betType GROUP BY b.betType")
	BetTypeTotal getTotalInvestmentByBetType(final BetType betType);
	
}
