package entity;

public class LawEnforcementAgency {
	private int agencyID;
	private String agencyName;
	private String jurisdiction;
	private String contactInformation;
	public LawEnforcementAgency() {
		
	}
	
	public int getAgencyID() {
		return agencyID;
	}
	public void setAgencyID(int agencyID) {
		this.agencyID = agencyID;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	public LawEnforcementAgency(int agencyID, String agencyName, String jurisdiction, String contactInformation) {
		super();
		this.agencyID = agencyID;
		this.agencyName = agencyName;
		this.jurisdiction = jurisdiction;
		this.contactInformation = contactInformation;
	}
	@Override
	public String toString() {
		return "LawEnforcementAgency [agencyID=" + agencyID + ", agencyName=" + agencyName + ", jurisdiction="
				+ jurisdiction + ", contactInformation=" + contactInformation + "]";
	}


}
