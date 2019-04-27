package au.com.tabcorp.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetCount;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {

	@Query("SELECT new au.com.tabcorp.model.BetTypeTotal(b.betType, sum(investmentAmount)) FROM Bet b WHERE b.betType = :betType GROUP BY b.betType")
	BetTypeTotal getTotalInvestmentByBetType(final BetType betType);

	@Query("SELECT new au.com.tabcorp.model.CustomerTotal(b.customerId, sum(investmentAmount)) FROM Bet b WHERE b.customerId = :customerId GROUP BY b.customerId")
	CustomerTotal getTotalInvestmentByCustomer(final Long customerId);

	@Query("SELECT new au.com.tabcorp.model.BetCount(b.betType, count(b.betId)) FROM Bet b WHERE b.betType = :betType GROUP BY b.betType")
	BetCount getBetCountByBetType(BetType betType);

	@Query("SELECT COUNT(b.betId) FROM Bet b WHERE b.created BETWEEN :from AND :to")
	Long getBetCountByRange(LocalDateTime from, LocalDateTime to);
	
}
