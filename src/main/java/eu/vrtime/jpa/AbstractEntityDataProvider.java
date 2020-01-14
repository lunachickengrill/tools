package eu.vrtime.jpa;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * Abstract base class for DataProvider implementations. Sort needs to be implemented correctly.
 * @author tschwaiger
 *
 * @param <T>
 */

public abstract class AbstractEntityDataProvider<T extends AbstractBaseEntity> extends SortableDataProvider<T, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3072604613622733163L;

	protected SpecificationRepository<T, Long> repo;
	protected FilterSpecification<T> filter;
	private Long rows;

	public AbstractEntityDataProvider(SpecificationRepository<T, Long> repo, int pageSize,
			FilterSpecification<T> filter) {
		super();
		this.repo = repo;
		this.filter = filter;
	}

	protected List<T> find() {

		return filter != null ? repo.findAll(filter.createFilterSpecification()) : Collections.emptyList();
	}

	@Override
	public Iterator<? extends T> iterator(long first, long count) {

		List<T> entities = find();

		Collections.sort(entities, new Comparator<T>() {

			public int compare(T d1, T d2) {
				int dir = getSort().isAscending() ? 1 : -1;

				return dir * (d1.getId().compareTo(d2.getId()));
			}
		});

		return entities.iterator();
	}

	@Override
	public long size() {
		if (rows == null || rows == 0) {
			rows = filter != null ? repo.count(filter.createFilterSpecification()) : repo.count();
		}
		return rows;
	}

	@Override
	public IModel<T> model(T object) {
		return new CompoundPropertyModel<T>(object);
	}

}
