package au.com.tabcorp.converter;

import org.apache.log4j.Logger;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import au.com.tabcorp.model.Bet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class BetMessageConverter extends AbstractHttpMessageConverter<Bet> {

    private static final Logger LOGGER = Logger.getLogger(BetMessageConverter.class);

    public BetMessageConverter() {
        super(MediaType.APPLICATION_OCTET_STREAM);
    }

    protected boolean supports(Class<?> clazz) {
        return Bet.class.equals(clazz);
    }

    @Override
    protected Bet readInternal(Class<? extends Bet> clazz,
                               HttpInputMessage inputMessage) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(inputMessage.getBody());
        Bet bet = null;
        LOGGER.info("About to read object");
        try {
            bet = (Bet)ois.readObject();
            LOGGER.info("Read in bet " + bet);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return bet;
    }

    protected void writeInternal(Bet bet, HttpOutputMessage outputMessage) throws IOException {
        outputMessage.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
        final OutputStream outputStream = outputMessage.getBody();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        LOGGER.info("About to write object " + bet);
        oos.writeObject(bet);
        outputStream.close();
    }
}
