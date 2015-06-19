package bo;

import java.util.Date;

/**
 * GpGcjsTbrZzId generated by MyEclipse Persistence Tools
 */

public class GpGcjsTbrZzId implements java.io.Serializable {

	// Fields

	private String id;

	private String tbrId;

	private String tbrName;

	private String zzType;

	private String zzName;

	private String zzCode;

	private Date validityDate;

	private String note;

	private String zzTypeName;

	// Constructors

	/** default constructor */
	public GpGcjsTbrZzId() {
	}

	/** minimal constructor */
	public GpGcjsTbrZzId(String id) {
		this.id = id;
	}

	/** full constructor */
	public GpGcjsTbrZzId(String id, String tbrId, String tbrName,
			String zzType, String zzName, String zzCode, Date validityDate,
			String note, String zzTypeName) {
		this.id = id;
		this.tbrId = tbrId;
		this.tbrName = tbrName;
		this.zzType = zzType;
		this.zzName = zzName;
		this.zzCode = zzCode;
		this.validityDate = validityDate;
		this.note = note;
		this.zzTypeName = zzTypeName;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTbrId() {
		return this.tbrId;
	}

	public void setTbrId(String tbrId) {
		this.tbrId = tbrId;
	}

	public String getTbrName() {
		return this.tbrName;
	}

	public void setTbrName(String tbrName) {
		this.tbrName = tbrName;
	}

	public String getZzType() {
		return this.zzType;
	}

	public void setZzType(String zzType) {
		this.zzType = zzType;
	}

	public String getZzName() {
		return this.zzName;
	}

	public void setZzName(String zzName) {
		this.zzName = zzName;
	}

	public String getZzCode() {
		return this.zzCode;
	}

	public void setZzCode(String zzCode) {
		this.zzCode = zzCode;
	}

	public Date getValidityDate() {
		return this.validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getZzTypeName() {
		return this.zzTypeName;
	}

	public void setZzTypeName(String zzTypeName) {
		this.zzTypeName = zzTypeName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GpGcjsTbrZzId))
			return false;
		GpGcjsTbrZzId castOther = (GpGcjsTbrZzId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTbrId() == castOther.getTbrId()) || (this
						.getTbrId() != null
						&& castOther.getTbrId() != null && this.getTbrId()
						.equals(castOther.getTbrId())))
				&& ((this.getTbrName() == castOther.getTbrName()) || (this
						.getTbrName() != null
						&& castOther.getTbrName() != null && this.getTbrName()
						.equals(castOther.getTbrName())))
				&& ((this.getZzType() == castOther.getZzType()) || (this
						.getZzType() != null
						&& castOther.getZzType() != null && this.getZzType()
						.equals(castOther.getZzType())))
				&& ((this.getZzName() == castOther.getZzName()) || (this
						.getZzName() != null
						&& castOther.getZzName() != null && this.getZzName()
						.equals(castOther.getZzName())))
				&& ((this.getZzCode() == castOther.getZzCode()) || (this
						.getZzCode() != null
						&& castOther.getZzCode() != null && this.getZzCode()
						.equals(castOther.getZzCode())))
				&& ((this.getValidityDate() == castOther.getValidityDate()) || (this
						.getValidityDate() != null
						&& castOther.getValidityDate() != null && this
						.getValidityDate().equals(castOther.getValidityDate())))
				&& ((this.getNote() == castOther.getNote()) || (this.getNote() != null
						&& castOther.getNote() != null && this.getNote()
						.equals(castOther.getNote())))
				&& ((this.getZzTypeName() == castOther.getZzTypeName()) || (this
						.getZzTypeName() != null
						&& castOther.getZzTypeName() != null && this
						.getZzTypeName().equals(castOther.getZzTypeName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTbrId() == null ? 0 : this.getTbrId().hashCode());
		result = 37 * result
				+ (getTbrName() == null ? 0 : this.getTbrName().hashCode());
		result = 37 * result
				+ (getZzType() == null ? 0 : this.getZzType().hashCode());
		result = 37 * result
				+ (getZzName() == null ? 0 : this.getZzName().hashCode());
		result = 37 * result
				+ (getZzCode() == null ? 0 : this.getZzCode().hashCode());
		result = 37
				* result
				+ (getValidityDate() == null ? 0 : this.getValidityDate()
						.hashCode());
		result = 37 * result
				+ (getNote() == null ? 0 : this.getNote().hashCode());
		result = 37
				* result
				+ (getZzTypeName() == null ? 0 : this.getZzTypeName()
						.hashCode());
		return result;
	}

}