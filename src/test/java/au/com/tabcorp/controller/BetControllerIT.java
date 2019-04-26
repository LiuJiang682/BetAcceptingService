package au.com.tabcorp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.com.tabcorp.BetAcceptingServiceApplication;
import au.com.tabcorp.model.Bet;
import au.com.tabcorp.test.fixture.TestFixture;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BetAcceptingServiceApplication.class, 
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BetControllerIT {

	private static final String EXPECTED_ERROR_EXCEEDED_MAX = "The investment amount exceeded maximum allows!";
	private static final String EXPECTED_ERROR_INVALID_AMOUNT = "The investment amount cannot be less than or equal to Zero!";
	private static final String EXPECTED_ERROR_NULL_DATE = "DateTime cannot be null!";
	private static final String EXPECTED_ERROR_PAST_DATE = "DateTime cannot be in the past!";
	
	@LocalServerPort
	private int port;
	
	@Test
	public void shouldPlaceTheBet() {
		//Given
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Bet bet = TestFixture.getBet();
		HttpEntity<Bet> entity = new HttpEntity<Bet>(bet, 
				headers);
		//When
		ResponseEntity<Bet> response = restTemplate.exchange(
				createURLWithPort("/"),
				HttpMethod.PUT, entity, Bet.class);
		//Then
		assertThat(response, is(notNullValue()));
		Bet retrievedBet = response.getBody();
		assertThat(retrievedBet.getBetType(), is(equalTo(bet.getBetType())));
		assertThat(retrievedBet.getCustomerId(), is(equalTo(bet.getCustomerId())));
		String retrievedBetTime = retrievedBet.getDateTime().toString();
		String betTime = bet.getDateTime().toString();
		assertThat(betTime.startsWith(retrievedBetTime), is(true));
		assertThat(retrievedBet.getInvestmentAmount(), is(equalTo(bet.getInvestmentAmount())));
		assertThat(retrievedBet.getPropNumber(), is(equalTo(bet.getPropNumber())));
	}
	
	@Test
	public void shouldRejectTheBetWhenAmountIs200000() {
		//Given
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Bet bet = TestFixture.getBet();
		bet.setInvestmentAmount(200000d);
		HttpEntity<Bet> entity = new HttpEntity<Bet>(bet, 
				headers);
		//When
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/"),
				HttpMethod.PUT, entity, String.class);
		
		//Then
		assertThat(response, is(notNullValue()));
		assertThat(response.getBody(), is(equalTo(EXPECTED_ERROR_EXCEEDED_MAX)));
	}
	
	@Test
	public void shouldRejectTheBetWhenAmountIsZERO() {
		//Given
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Bet bet = TestFixture.getBet();
		bet.setInvestmentAmount(0d);
		HttpEntity<Bet> entity = new HttpEntity<Bet>(bet, 
				headers);
		//When
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/"),
				HttpMethod.PUT, entity, String.class);
		
		//Then
		assertThat(response, is(notNullValue()));
		assertThat(response.getBody(), is(equalTo(EXPECTED_ERROR_INVALID_AMOUNT)));
	}
	
	@Test
	public void shouldRejectTheBetWhenAmountIsltZERO() {
		//Given
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Bet bet = TestFixture.getBet();
		bet.setInvestmentAmount(-2d);
		HttpEntity<Bet> entity = new HttpEntity<Bet>(bet, 
				headers);
		//When
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/"),
				HttpMethod.PUT, entity, String.class);
		
		//Then
		assertThat(response, is(notNullValue()));
		assertThat(response.getBody(), is(equalTo(EXPECTED_ERROR_INVALID_AMOUNT)));
	}
	
	@Test
	public void shouldRejectTheBetWhenTheDateTimeIsNull() {
		//Given
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Bet bet = TestFixture.getBet();
		bet.setDateTime(null);
		HttpEntity<Bet> entity = new HttpEntity<Bet>(bet, 
				headers);
		//When
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/"),
				HttpMethod.PUT, entity, String.class);
		
		//Then
		assertThat(response, is(notNullValue()));
		assertThat(response.getBody(), is(equalTo(EXPECTED_ERROR_NULL_DATE)));
	}
	
	@Test
	public void shouldRejectTheBetWhenAmountIsPast() {
		//Given
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Bet bet = TestFixture.getBet();
		bet.setDateTime(TestFixture.getPastDate());
		HttpEntity<Bet> entity = new HttpEntity<Bet>(bet, 
				headers);
		//When
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/"),
				HttpMethod.PUT, entity, String.class);
		
		//Then
		assertThat(response, is(notNullValue()));
		assertThat(response.getBody(), is(equalTo(EXPECTED_ERROR_PAST_DATE)));
	}
	
	private String createURLWithPort(final String uri) {
		return "http://localhost:" + port + uri;
	}
}
