package au.com.tabcorp.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.tabcorp.constants.Constants.Numeric;
import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetCount;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;
import au.com.tabcorp.model.DateRange;
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
		//Then
		assertThat(betCount, is(notNullValue()));
		assertThat(betCount.getBetType(), is(equalTo(betType)));
		assertThat(betCount.getBetCount(), is(equalTo(200l)));
	}
	
	@Test
	public void shouldReturnAveragePerHourBetweenWithouModifyWhenAllDatePresent() {
		//Given
		DateRange dateRange = new DateRange();
		LocalDateTime now =LocalDateTime.now();
		dateRange.setTo(now);
		LocalDateTime from = now.minusDays(Numeric.ONE);
		dateRange.setFrom(from);
		when(mockBetPersistenceService.getBetCountByRange(eq(dateRange.getFrom()),
				eq(dateRange.getTo())))
			.thenReturn(24l);
		//When
		BigDecimal average = testInstance.getAveragePerHourBetween(dateRange);
		//Then
		assertThat(average, is(equalTo(BigDecimal.ONE)));
	}
	
	@Test
	public void shouldReturnAveragePerHourBetweenWithouModifyWhenToMissing() {
		//Given
		DateRange dateRange = new DateRange();
		LocalDateTime now =LocalDateTime.now();
		LocalDateTime from = now.minusDays(Numeric.ONE);
		dateRange.setFrom(from);
		when(mockBetPersistenceService.getBetCountByRange(eq(dateRange.getFrom()),
				Matchers.any(LocalDateTime.class)))
			.thenReturn(24l);
		//When
		BigDecimal average = testInstance.getAveragePerHourBetween(dateRange);
		//Then
		assertThat(average, is(equalTo(BigDecimal.ONE)));
		ArgumentCaptor<LocalDateTime> nowCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
		ArgumentCaptor<LocalDateTime> fromCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
		verify(mockBetPersistenceService).getBetCountByRange(fromCaptor.capture(), 
				nowCaptor.capture());
		LocalDateTime capturedFrom = fromCaptor.getValue();
		assertThat(capturedFrom, is(equalTo(from)));
		LocalDateTime capturedTo = nowCaptor.getValue();
		assertThat(capturedTo.isAfter(now), is(true));
	}
	
	@Test
	public void shouldReturnAveragePerHourBetweenWithouModifyWhenFromMissing() {
		//Given
		DateRange dateRange = new DateRange();
		LocalDateTime now =LocalDateTime.now();
		dateRange.setTo(now);
		when(mockBetPersistenceService.getBetCountByRange(
				Matchers.any(LocalDateTime.class),
				eq(now)))
			.thenReturn(1l);
		//When
		BigDecimal average = testInstance.getAveragePerHourBetween(dateRange);
		//Then
		assertThat(average, is(equalTo(BigDecimal.ONE)));
		ArgumentCaptor<LocalDateTime> nowCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
		ArgumentCaptor<LocalDateTime> fromCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
		verify(mockBetPersistenceService).getBetCountByRange(fromCaptor.capture(), 
				nowCaptor.capture());
		LocalDateTime capturedFrom = fromCaptor.getValue();
		assertThat(ChronoUnit.HOURS.between(capturedFrom, now), is(equalTo(1l)));
		LocalDateTime capturedTo = nowCaptor.getValue();
		assertThat(capturedTo, is(equalTo(now)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionBetweenIsZero() {
		//Given
		DateRange dateRange = new DateRange();
		LocalDateTime now =LocalDateTime.now();
		dateRange.setTo(now);
		dateRange.setFrom(now);
		//When
		testInstance.getAveragePerHourBetween(dateRange);
		fail("Program reached unexpected point!");
	}
}
