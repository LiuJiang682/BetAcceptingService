package au.com.tabcorp.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BetCount implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -802371885815290440L;

	private final BetType betType;
	private final Long betCount;
	
	public BetCount(final BetType betType, final Long betCount) {
		this.betType = betType;
		this.betCount = betCount;
	}

	public BetType getBetType() {
		return betType;
	}

	public Long getBetCount() {
		return betCount;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
