package au.com.tabcorp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

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
		assertThat(response.getBody(), is(equalTo(bet)));
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
	
	private String createURLWithPort(final String uri) {
		return "http://localhost:" + port + uri;
	}
}
