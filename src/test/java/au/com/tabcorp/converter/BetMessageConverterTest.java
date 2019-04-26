package au.com.tabcorp.converter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;

import au.com.tabcorp.model.Bet;

//@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest({ BetMessageConverter.class })
public class BetMessageConverterTest {

	private BetMessageConverter testInstance;

	@Before
	public void setUp() {
		testInstance = new BetMessageConverter();
	}

	@Test
	public void shouldReturnTrueWhenClassIsBet() {
		// Given
		Class<?> clazz = Bet.class;
		// When
		boolean flag = testInstance.supports(clazz);
		// Then
		assertThat(flag, is(true));
	}

	@Test
	public void shouldReturnFalseWhenClassIsNotBet() {
		// Given
		Class<?> clazz = Object.class;
		// When
		boolean flag = testInstance.supports(clazz);
		// Then
		assertThat(flag, is(false));
	}

	@Test
	public void shouldReadObject() throws Exception {
		// Given
		Bet bet = new Bet();
		InputStream mockInputStream = Mockito.mock(InputStream.class);
		HttpInputMessage mockHttpInputMessage = Mockito.mock(HttpInputMessage.class);
		when(mockHttpInputMessage.getBody()).thenReturn(mockInputStream);
		ObjectInputStream mockObjectInputStream = PowerMockito.mock(ObjectInputStream.class);
		PowerMockito.doReturn(bet).when(mockObjectInputStream).readObject();
		PowerMockito.whenNew(ObjectInputStream.class).withArguments(eq(mockInputStream))
				.thenReturn(mockObjectInputStream);

		// When
		Bet retrievedBet = testInstance.readInternal(Bet.class, mockHttpInputMessage);
		// Then
		assertThat(retrievedBet, is(notNullValue()));
		assertThat(retrievedBet, is(equalTo(bet)));
	}

	@Test
	public void shouldFailedToReadObject() throws Exception {
		// Given
		InputStream mockInputStream = Mockito.mock(InputStream.class);
		HttpInputMessage mockHttpInputMessage = Mockito.mock(HttpInputMessage.class);
		when(mockHttpInputMessage.getBody()).thenReturn(mockInputStream);
		ObjectInputStream mockObjectInputStream = PowerMockito.mock(ObjectInputStream.class);
		PowerMockito.doThrow(new RuntimeException("Test")).when(mockObjectInputStream).readObject();
		PowerMockito.whenNew(ObjectInputStream.class).withArguments(eq(mockInputStream))
				.thenReturn(mockObjectInputStream);

		// When
		Bet retrievedBet = testInstance.readInternal(Bet.class, mockHttpInputMessage);
		// Then
		assertThat(retrievedBet, is(nullValue()));
	}

	@Test(expected = IOException.class)
	public void shouldRaiseExceptionReadObjectWhenGetInputStream() throws Exception {
		// Given
		InputStream mockInputStream = Mockito.mock(InputStream.class);
		HttpInputMessage mockHttpInputMessage = Mockito.mock(HttpInputMessage.class);
		when(mockHttpInputMessage.getBody()).thenThrow(new IOException());
		ObjectInputStream mockObjectInputStream = PowerMockito.mock(ObjectInputStream.class);
		PowerMockito.doThrow(new RuntimeException("Test")).when(mockObjectInputStream).readObject();
		PowerMockito.whenNew(ObjectInputStream.class).withArguments(eq(mockInputStream))
				.thenReturn(mockObjectInputStream);

		// When
		testInstance.readInternal(Bet.class, mockHttpInputMessage);
		fail("Program reached  unexpected point!");
	}

	@Test
	public void shouldWriteToObject() throws Exception {
		// Given
		Bet bet = new Bet();
		HttpHeaders headers = new HttpHeaders();
		OutputStream mockOutputStream = Mockito.mock(OutputStream.class);
		HttpOutputMessage mockHttpOutputMessage = Mockito.mock(HttpOutputMessage.class);
		when(mockHttpOutputMessage.getHeaders()).thenReturn(headers);
		when(mockHttpOutputMessage.getBody()).thenReturn(mockOutputStream);
		ObjectOutputStream mockObjectOutputStream = PowerMockito.mock(ObjectOutputStream.class);
		PowerMockito.whenNew(ObjectOutputStream.class).withArguments(eq(mockOutputStream))
				.thenReturn(mockObjectOutputStream);

		// When
		testInstance.writeInternal(bet, mockHttpOutputMessage);
		// Then
		ArgumentCaptor<Bet> betCaptor = ArgumentCaptor.forClass(Bet.class);
		verify(mockObjectOutputStream).writeObject(betCaptor.capture());
		List<Bet> captoredBets = betCaptor.getAllValues();
		assertThat(captoredBets, is(notNullValue()));
		assertThat(captoredBets.size(), is(equalTo(1)));
		assertThat(captoredBets.get(0), is(equalTo(bet)));
	}

	@Test(expected = IOException.class)
	public void shouldRaiseExceptionWhenGetInputStream() throws Exception {
		// Given
		Bet bet = new Bet();
		HttpHeaders headers = new HttpHeaders();
		OutputStream mockOutputStream = Mockito.mock(OutputStream.class);
		HttpOutputMessage mockHttpOutputMessage = Mockito.mock(HttpOutputMessage.class);
		when(mockHttpOutputMessage.getHeaders()).thenReturn(headers);
		when(mockHttpOutputMessage.getBody()).thenThrow(new IOException());
		ObjectOutputStream mockObjectOutputStream = PowerMockito.mock(ObjectOutputStream.class);
		PowerMockito.whenNew(ObjectOutputStream.class).withArguments(eq(mockOutputStream))
				.thenReturn(mockObjectOutputStream);

		// When
		testInstance.writeInternal(bet, mockHttpOutputMessage);
		fail("Program reached  unexpected point!");
	}
	
	@Test(expected = IOException.class)
	public void shouldRaiseExceptionWhenWriteToObject() throws Exception {
		// Given
		Bet bet = new Bet();
		HttpHeaders headers = new HttpHeaders();
		OutputStream mockOutputStream = Mockito.mock(OutputStream.class);
		HttpOutputMessage mockHttpOutputMessage = Mockito.mock(HttpOutputMessage.class);
		when(mockHttpOutputMessage.getHeaders()).thenReturn(headers);
		when(mockHttpOutputMessage.getBody()).thenReturn(mockOutputStream);
		ObjectOutputStream mockObjectOutputStream = PowerMockito.mock(ObjectOutputStream.class);
		PowerMockito.doThrow(new IOException()).when(mockObjectOutputStream)
			.writeObject(eq(bet));
		PowerMockito.whenNew(ObjectOutputStream.class).withArguments(eq(mockOutputStream))
				.thenReturn(mockObjectOutputStream);

		// When
		testInstance.writeInternal(bet, mockHttpOutputMessage);
		fail("Program reached  unexpected point!");
	}
	
	@Test(expected = IOException.class)
	public void shouldRaiseExceptionWhenConstructTheObjectOutputStream() throws Exception {
		// Given
		Bet bet = new Bet();
		HttpHeaders headers = new HttpHeaders();
		OutputStream mockOutputStream = Mockito.mock(OutputStream.class);
		HttpOutputMessage mockHttpOutputMessage = Mockito.mock(HttpOutputMessage.class);
		when(mockHttpOutputMessage.getHeaders()).thenReturn(headers);
		when(mockHttpOutputMessage.getBody()).thenReturn(mockOutputStream);
		PowerMockito.whenNew(ObjectOutputStream.class).withArguments(eq(mockOutputStream))
				.thenThrow(new IOException());

		// When
		testInstance.writeInternal(bet, mockHttpOutputMessage);
		fail("Program reached  unexpected point!");
	}
}
