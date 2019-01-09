package eu.vrtime.vrm.api.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import eu.vrtime.vrm.domain.shared.VoiceNumber;

public abstract class AbstractVrmResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 684250522358313325L;
	
	private String switchId;
	private String smId;
	private String nic;
	private String directoryNumber;

	private List<VoiceNumber> numbers = new ArrayList<>();

	public AbstractVrmResponse(String directoryNumber) {
		Validate.notNull(directoryNumber, "directoryNumber is null");

		this.directoryNumber = directoryNumber;
	}

	public String getDirectoryNumber() {
		return directoryNumber;
	}

	public void setDirectoryNumber(String directoryNumber) {
		this.directoryNumber = directoryNumber;
	}

	public String getSmId() {
		return smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getSwitchId() {
		return switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public List<VoiceNumber> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<VoiceNumber> numbers) {
		this.numbers = numbers;
	}

	public void addNumber(String dn, String len) {
		numbers.add(new VoiceNumber(dn, len));
	}

	@Override
	public String toString() {
		return "AbstractVrmResponse [directoryNumber=" + directoryNumber + ", smId=" + smId + ", nic=" + nic
				+ ", switchId=" + switchId + ", numbers=" + numbers + "]";
	}


}
