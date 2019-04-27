package au.com.tabcorp.repositories;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;
import au.com.tabcorp.test.fixture.TestFixture;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class BetRespositoryTest {
	
	@Autowired
	private BetRepository betRepository;

	@Test
	public void ShouldReturnBetTypeWinTotal() {
		//Given
		betRepository.deleteAll();
		Bet bet1 = TestFixture.getBet();
		Bet bet2 = TestFixture.getBet();
		betRepository.save(bet1);
		betRepository.save(bet2);
		//When
		BetTypeTotal betTypeTotal = betRepository
				.getTotalInvestmentByBetType(BetType.WIN);
		//Then
		assertThat(betTypeTotal, is(notNullValue()));
		assertThat(betTypeTotal.getBetType(), is(equalTo(BetType.WIN)));
		assertThat(betTypeTotal.getTotal(), is(equalTo(new BigDecimal(400d))));
	}
	
	@Test
	public void ShouldReturnCusomter1Total() {
		//Given
		betRepository.deleteAll();
		Bet bet1 = TestFixture.getBet();
		Bet bet2 = TestFixture.getBet();
		betRepository.save(bet1);
		betRepository.save(bet2);
		long customerId = 0;
		//When
		CustomerTotal customerTotal = betRepository
				.getTotalInvestmentByCustomer(customerId);
		//Then
		assertThat(customerTotal, is(notNullValue()));
		assertThat(customerTotal.getCustomerId(), is(equalTo(customerId)));
		assertThat(customerTotal.getTotal(), is(equalTo(new BigDecimal(400d))));
	}
}
