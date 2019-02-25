package eu.vrtime.bootwicketwebapptwo.web;

import static java.util.Collections.emptyList;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.jpa.domain.Specification;

import eu.vrtime.bootwicketwebapptwo.model.Customer;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerRepository;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerServiceRepository;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerSpecification;

public class AdminPage extends AbstractBasePage {

	/**
	 *
	 */
	private static final long serialVersionUID = 9076868135610334317L;
	private static final String LABEL_ADMINPAGE_ID = "labelAdminPage";
	private static final String LABLE_ADMINPAGE_MODEL = "Admin Page Label";
	private static final String LISTVIEW_ID = "listView";
	private static final String FORM_ID = "form";
	private static final String FORM_CUSTOMERID = "customerId";
	private static final String FORM_FIRSTNAME = "firstName";
	private static final String FORM_LASTNAME = "lastName";
	private static final String FORM_SUBMIT = "submit";
	private static final String NAVIGATOR = "navigator";
	private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");

	private Long customerId;

	private Specification<Customer> spec;

	private IModel<List<Customer>> customerListModel = new LoadableDetachableModel<List<Customer>>() {
		@Override
		protected List<Customer> load() {
//			return customerId != null ? customerRepository.findByCustomerId(customerId) : emptyList();
			return spec != null ? customerRepository.findAll(spec) : emptyList();
		}
	};

	Customer customer = new Customer();

	@SpringBean
	private CustomerRepository customerRepository;

	@SpringBean
	private CustomerServiceRepository serviceRepository;

	public AdminPage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Label(LABEL_ADMINPAGE_ID, LABLE_ADMINPAGE_MODEL));
		add(feedbackPanel);

		Form form = new Form(FORM_ID);
		CompoundPropertyModel<Customer> model = new CompoundPropertyModel<Customer>(customer);

		form.setDefaultModel(model);
		form.add(new TextField<>(FORM_CUSTOMERID));
		form.add(new TextField<>(FORM_FIRSTNAME));
		form.add(new TextField<>(FORM_LASTNAME));
		form.add(new Button(FORM_SUBMIT) {

			private static final long serialVersionUID = -2644986353744688237L;

			@Override
			public void onSubmit() {

				spec = new CustomerSpecification(customer);

			}

		});
		add(form);

		PageableListView<Customer> listView = new PageableListView<Customer>(LISTVIEW_ID, customerListModel, 3) {

			@Override
			protected void populateItem(ListItem<Customer> item) {
				Customer cust = item.getModelObject();
				item.add(new Label("customerId", Model.of(cust.getCustomerId())));
				item.add(new Label("firstName", Model.of(cust.getFirstName())));
				item.add(new Label("lastName", Model.of(cust.getLastName())));

			}

		};
		add(listView);
		add(new PagingNavigator(NAVIGATOR, listView));
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
