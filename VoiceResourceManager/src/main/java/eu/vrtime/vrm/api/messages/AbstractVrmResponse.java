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
	private String requestParam;

	private List<VoiceNumber> numbers = new ArrayList<>();

	public AbstractVrmResponse(String requestParam) {
		Validate.notNull(requestParam, "directoryNumber is null");

		this.requestParam = requestParam;
	}

	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
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
		return "AbstractVrmResponse [requestParam=" + requestParam + ", smId=" + smId + ", nic=" + nic
				+ ", switchId=" + switchId + ", numbers=" + numbers + "]";
	}


}
