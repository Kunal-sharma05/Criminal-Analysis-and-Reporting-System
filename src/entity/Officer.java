package entity;

public class Officer {
	private int officerID;
	private String firstName;
	private String lastName;
	private String badgeNumber;
	private String rank;
	private String contactInformation;
	private int agencyID;
	public Officer() {
		
	}
	public int getOfficerID() {
		return officerID;
	}
	public void setOfficerID(int officerID) {
		this.officerID = officerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBadgeNumber() {
		return badgeNumber;
	}
	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	public int getAgencyID() {
		return agencyID;
	}
	public void setAgencyID(int agencyID) {
		this.agencyID = agencyID;
	}
	public Officer(int officerID, String firstName, String lastName, String badgeNumber, String rank,
			String contactInformation, int agencyID) {
		super();
		this.officerID = officerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.badgeNumber = badgeNumber;
		this.rank = rank;
		this.contactInformation = contactInformation;
		this.agencyID = agencyID;
	}
	@Override
	public String toString() {
		return "Officer [officerID=" + officerID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", badgeNumber=" + badgeNumber + ", rank=" + rank + ", contactInformation=" + contactInformation
				+ ", agencyID=" + agencyID + "]";
	}


}
