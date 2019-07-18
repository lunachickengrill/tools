package eu.vrtime.bootwicketappthree.web.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import eu.vrtime.bootwicketappthree.model.Device;
import eu.vrtime.bootwicketappthree.model.DeviceSpecification;
import eu.vrtime.bootwicketappthree.repositories.DeviceRepository;

public class DeviceDataProvider extends SortableDataProvider<Device, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8128794789583192266L;

	private DeviceRepository repo;
	private DeviceSpecification filter;
	private Long rows;

	public DeviceDataProvider(DeviceRepository repo, DeviceSpecification filter) {
		this.repo = repo;
		this.filter = filter;

	}

	private List<Device> find() {

		return ((filter != null)
				&& ((filter.getMac() != null || filter.getSerial() != null || filter.getType() != null)))
						? repo.findAll(filter)
						: Collections.emptyList();
	}

	@Override
	public Iterator<Device> iterator(long first, long count) {
		List<Device> devices = find();
		return devices.iterator();
	}

	@Override
	public long size() {

		if (rows == null || rows ==0 ) {
			rows = filter != null ? repo.count(filter) : repo.count();
		}
		return rows;
	}

	@Override
	public IModel<Device> model(final Device device) {
		return new CompoundPropertyModel<Device>(device);
	}
}