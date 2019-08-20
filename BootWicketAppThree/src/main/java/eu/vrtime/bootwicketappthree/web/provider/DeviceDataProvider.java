package eu.vrtime.bootwicketappthree.web.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import eu.vrtime.bootwicketappthree.model.Device;
import eu.vrtime.bootwicketappthree.model.DeviceSpecification;
import eu.vrtime.bootwicketappthree.repositories.DeviceRepository;

/**
 * The device data provider.
 * Sort needs to be implemented.
 * @author tschwaiger
 *
 */

public class DeviceDataProvider extends SortableDataProvider<Device,String> {

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
		setSort("mac", SortOrder.ASCENDING);
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
		
		Collections.sort(devices, new Comparator<Device>() {
			
			public int compare(Device d1, Device d2) {
				int dir = getSort().isAscending() ? 1 : -1;
						
				return dir * (d1.getMac().compareTo(d2.getMac()));
			}
		});
		
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