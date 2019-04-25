package au.com.tabcorp.controller;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
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
import au.com.tabcorp.services.BetService;
import au.com.tabcorp.test.fixture.TestFixture;

//@SpringBootTest
//@ActiveProfiles("test")
public class BetControllerTest {

	@Mock
	private BetService mockBetService;
	
	@InjectMocks
	private BetController testInstance;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldPlaceBet() {
		//Given
		Bet bet = TestFixture.getBet();
		//When
		testInstance.placeBet(bet);
		//Then
		ArgumentCaptor<Bet> betCaptor = ArgumentCaptor.forClass(Bet.class);
		verify(mockBetService).placeBet(betCaptor.capture());
		List<Bet> bets = betCaptor.getAllValues();
		assertThat(bets.size(), is(equalTo(1)));	
		assertThat(bets.get(0).getBetType(), is(equalTo(BetType.WIN)));
	}
}
