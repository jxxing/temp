package bo;

import java.util.Date;

/**
 * GpJsgcExpSignId generated by MyEclipse Persistence Tools
 */

public class GpJsgcExpSignId implements java.io.Serializable {

	// Fields

	private String id;

	private String expId;

	private String cardId;

	private String expName;

	private String projId;

	private Date signTime;

	// Constructors

	/** default constructor */
	public GpJsgcExpSignId() {
	}

	/** minimal constructor */
	public GpJsgcExpSignId(String id) {
		this.id = id;
	}

	/** full constructor */
	public GpJsgcExpSignId(String id, String expId, String cardId,
			String expName, String projId, Date signTime) {
		this.id = id;
		this.expId = expId;
		this.cardId = cardId;
		this.expName = expName;
		this.projId = projId;
		this.signTime = signTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExpId() {
		return this.expId;
	}

	public void setExpId(String expId) {
		this.expId = expId;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getExpName() {
		return this.expName;
	}

	public void setExpName(String expName) {
		this.expName = expName;
	}

	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public Date getSignTime() {
		return this.signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GpJsgcExpSignId))
			return false;
		GpJsgcExpSignId castOther = (GpJsgcExpSignId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getExpId() == castOther.getExpId()) || (this
						.getExpId() != null
						&& castOther.getExpId() != null && this.getExpId()
						.equals(castOther.getExpId())))
				&& ((this.getCardId() == castOther.getCardId()) || (this
						.getCardId() != null
						&& castOther.getCardId() != null && this.getCardId()
						.equals(castOther.getCardId())))
				&& ((this.getExpName() == castOther.getExpName()) || (this
						.getExpName() != null
						&& castOther.getExpName() != null && this.getExpName()
						.equals(castOther.getExpName())))
				&& ((this.getProjId() == castOther.getProjId()) || (this
						.getProjId() != null
						&& castOther.getProjId() != null && this.getProjId()
						.equals(castOther.getProjId())))
				&& ((this.getSignTime() == castOther.getSignTime()) || (this
						.getSignTime() != null
						&& castOther.getSignTime() != null && this
						.getSignTime().equals(castOther.getSignTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getExpId() == null ? 0 : this.getExpId().hashCode());
		result = 37 * result
				+ (getCardId() == null ? 0 : this.getCardId().hashCode());
		result = 37 * result
				+ (getExpName() == null ? 0 : this.getExpName().hashCode());
		result = 37 * result
				+ (getProjId() == null ? 0 : this.getProjId().hashCode());
		result = 37 * result
				+ (getSignTime() == null ? 0 : this.getSignTime().hashCode());
		return result;
	}

}