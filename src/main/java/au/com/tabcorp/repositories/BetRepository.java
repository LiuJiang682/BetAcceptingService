package au.com.tabcorp.repositories;

import org.springframework.data.repository.CrudRepository;

import au.com.tabcorp.model.Bet;

public interface BetRepository extends CrudRepository<Bet, Long> {
	
}
