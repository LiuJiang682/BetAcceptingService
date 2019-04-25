package au.com.tabcorp.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetType;
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
}
