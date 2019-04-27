package au.com.tabcorp.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetCount;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;
import au.com.tabcorp.test.fixture.TestFixture;

public class BetServicesImplTest {

	@Mock
	private BetValidationService mockBetValidationService;
	@Mock
	private BetPersistenceService mockBetPersistenceService;
	
	@InjectMocks
	private BetServiceImpl testInstance;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCallValidationAndPersistenceServiceWhenPlaceBetCalled() {
		//Given
		Bet bet = TestFixture.getBet();
		//When
		testInstance.placeBet(bet);
		//Then
		ArgumentCaptor<Bet> validationBetCaptor = ArgumentCaptor.forClass(Bet.class);
		ArgumentCaptor<Bet> persistentBetCaptor = ArgumentCaptor.forClass(Bet.class);
		verify(mockBetValidationService).validate(validationBetCaptor.capture());
		verify(mockBetPersistenceService).save(persistentBetCaptor.capture());
		List<Bet> validatedBet = validationBetCaptor.getAllValues();
		assertThat(validatedBet.size(), is(equalTo(1)));
		assertThat(validatedBet.get(0), is(equalTo(bet)));
		List<Bet> persistentedBet = persistentBetCaptor.getAllValues();
		assertThat(persistentedBet.size(), is(equalTo(1)));
		assertThat(persistentedBet.get(0), is(equalTo(bet)));
	}
	
	@Test
	public void shouldCallGetTotalInvestmentByBetType() {
		//Given
		BetType betType = BetType.WIN;
		when(mockBetPersistenceService.getTotalInvestmentByBetType(
				eq(betType)))
			.thenReturn(TestFixture.getBetTypeTotal());
		//When
		BetTypeTotal betTypeTotal = testInstance.getTotalInvestmentByBetType(betType);
		assertThat(betTypeTotal, is(notNullValue()));
		assertThat(betTypeTotal.getBetType(), is(equalTo(betType)));
		assertThat(betTypeTotal.getTotal(), is(equalTo(new BigDecimal(200d))));
	}
	
	@Test
	public void shouldCallGetTotalInvestmentByCustomerId() {
		//Given
		long customerId = 1;
		when(mockBetPersistenceService.getTotalInvestmentByCustomer(
				eq(customerId)))
			.thenReturn(TestFixture.getCustomerTotal());
		//When
		CustomerTotal customerTotal = testInstance.getTotalInvestmentByCustomer(customerId);
		//Then
		assertThat(customerTotal, is(notNullValue()));
		assertThat(customerTotal.getCustomerId(), is(equalTo(customerId)));
		assertThat(customerTotal.getTotal(), is(equalTo(new BigDecimal(200d))));
	}
	
	@Test
	public void shouldCallGetBetCountByBetType() {
		//Given
		BetType betType = BetType.WIN;
		when(mockBetPersistenceService.getBetCountByBetType(
				eq(betType)))
			.thenReturn(TestFixture.getBetCount());
		//When
		BetCount betCount = testInstance.getBetCountByBetType(betType);
		assertThat(betCount, is(notNullValue()));
		assertThat(betCount.getBetType(), is(equalTo(betType)));
		assertThat(betCount.getBetCount(), is(equalTo(200l)));
	}
}
