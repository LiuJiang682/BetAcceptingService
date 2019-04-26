package au.com.tabcorp.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BetTypeTotal implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 6028317107063858341L;

	private final BetType betType;
	private final BigDecimal total;
	
	public BetTypeTotal(BetType betType, double total ) {
		this.betType = betType;
		this.total = new BigDecimal(total);
	}
	
	public BetType getBetType() {
		return betType;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
