package eu.vrtime.bootwicketappthree.web;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.iterators.EmptyListIterator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.vrtime.bootwicketappthree.model.Customer;
import eu.vrtime.bootwicketappthree.model.CustomerSpecification;
import eu.vrtime.bootwicketappthree.repositories.CustomerRepository;

public class CustomerPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2565501203152150630L;

	@SpringBean
	private CustomerRepository customerRepository;

	private static final String FORM_ID = "searchCustomerForm";
	private static final String FORM_CUSTOMERID_ID = "customerId";
	private static final String FORM_FIRSTNAME_ID = "firstName";
	private static final String FORM_LASTNAME_ID = "lastName";
	private static final String FORM_BUTTON_ID = "submit";
	private static final String FEEDBACKPANEL_ID = "feedback";
	private static final String CUSTOMERLIST_ID = "customerList";

	private FeedbackPanel feedbackPanel;
	private Customer customer;
	private CustomerSpecification customerSpec = new CustomerSpecification();
	private PageableListView<Customer> listView;
//	private IModel customerModel = new CustomerModel(customerRepository, customerSpec);
	
//	private IModel<List<Customer>> customerListModel = new LoadableDetachableModel<List<Customer>>() {
//
//		@Override
//		protected List<Customer> load() {
//
//			return (customerSpec != null && ((customerSpec.getCustomerId() != null)
//					|| (customerSpec.getFirstName() != null) || (customerSpec.getLastName() != null)))
//							? customerRepository.findAll(customerSpec)
//							: Collections.emptyList();
//		}
//
//	};

	public CustomerPanel(String id) {
		super(id);
		feedbackPanel = new FeedbackPanel(FEEDBACKPANEL_ID);
		customer = new Customer();
	}

	@Override
	protected void onInitialize() {
		// TODO Auto-generated method stub
		super.onInitialize();
		add(feedbackPanel);
		add(createCustomerForm(FORM_ID));
		listView = createListView(CUSTOMERLIST_ID);
		add(listView);
	}

	private Form createCustomerForm(String id) {

		Form form = new Form(id);
		CompoundPropertyModel<CustomerSpecification> model = new CompoundPropertyModel<CustomerSpecification>(
				customerSpec);
		form.setDefaultModel(model);

		form.add(new TextField<>(FORM_CUSTOMERID_ID));
		form.add(new TextField<>(FORM_FIRSTNAME_ID));
		form.add(new TextField<>(FORM_LASTNAME_ID));

		form.add(new Button(FORM_BUTTON_ID) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {

				super.onSubmit();

			}

		});

		return form;

	}

	private PageableListView<Customer> createListView(String id) {

			
		PageableListView<Customer> listView = new PageableListView<Customer>(id, new CustomerModel(customerRepository, customerSpec),10) {

			private static final long serialVersionUID = 6795844460169152926L;

			@Override
			protected void populateItem(ListItem<Customer> item) {
				Customer cust = (Customer) item.getModelObject();
				
				item.add(new Label("customerId", Model.of(cust.getCustomerId())));
				item.add(new Label("firstName", Model.of(cust.getFirstName())));
				item.add(new Label("lastName", Model.of(cust.getLastName())));

			}

		};
		
		return listView;
	}

}
