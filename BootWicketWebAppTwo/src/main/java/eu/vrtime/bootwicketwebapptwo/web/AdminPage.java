package eu.vrtime.bootwicketwebapptwo.web;

import static java.util.Collections.emptyList;

import java.util.List;
import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.springframework.data.jpa.domain.Specification;

import eu.vrtime.bootwicketwebapptwo.model.Customer;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerRepository;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerServiceRepository;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerSpecification;

/**
 * The Admin page of this application. Extends AbstractBasePage for consistent look and feel.
 * This page has a form on it to query customers. For this it uses an LoadableDetachableModel. This Model uses a CustomerSpecification object as model to query the repository based on the values provided.
 * In addition the Admin page allows to create customers via a modal window.
 * @author babis
 *
 */

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
	private static final String FORM_MAIL = "emailAddress";
	private static final String FORM_SUBMIT = "submit";
	private static final String NAVIGATOR = "navigator";
	private static final String LINK_CREATE_CUSTOMER = "createCustomerLink";
	private static final String CREATECUSTOMERWINDOW_ID = "createCustomerWindow";

	private ModalWindow createCustomerWindow;
	private FeedbackPanel feedbackPanel;
	private Specification<Customer> spec;

	private IModel<List<Customer>> customerListModel = new LoadableDetachableModel<List<Customer>>() {
		@Override
		protected List<Customer> load() {

			// return customerId != null ? customerRepository.findByCustomerId(customerId) :
			// emptyList();
			
			// using a custom specification
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

		createCustomerWindow = new ModalWindow(CREATECUSTOMERWINDOW_ID);
		feedbackPanel = new FeedbackPanel("feedback");
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		/**
		 * Page Label and FeedbackPanel
		 */

		add(new Label(LABEL_ADMINPAGE_ID, LABLE_ADMINPAGE_MODEL));
		add(feedbackPanel);
		add(createForm(FORM_ID));
		PageableListView<Customer> listView = createPageableListView(LISTVIEW_ID);
		add(listView);
		add(new PagingNavigator(NAVIGATOR, listView));
		add(createModalWindow(CREATECUSTOMERWINDOW_ID));

		add(new Link<Void>("sandboxLink") {

			/**
			 * 
			 */
			private static final long serialVersionUID = -121928308648337583L;

			@Override
			public void onClick() {
				setResponsePage(SandboxPage.class);

			}

		});

	}

	@Override
	protected void onConfigure() {
		// TODO Auto-generated method stub
		super.onConfigure();
	}

	private Form createForm(final String id) {

		/**
		 * the form
		 */

		Form form = new Form(id);
		
		// Using a customer object as CompoundPropertyModel
		CompoundPropertyModel<Customer> model = new CompoundPropertyModel<Customer>(customer);

		form.setDefaultModel(model);
		form.add(new TextField<>(FORM_CUSTOMERID).setType(Long.class));
		form.add(new TextField<>(FORM_FIRSTNAME).add(StringValidator.lengthBetween(2, 18)));
		form.add(new TextField<>(FORM_LASTNAME).add(StringValidator.lengthBetween(2, 18)));
		form.add(new TextField<>(FORM_MAIL));

		form.add(new Button(FORM_SUBMIT) {

			private static final long serialVersionUID = -2644986353744688237L;

			@Override
			public void onSubmit() {

				
				// We shall not use this. Instead we should use the model of this form and get the model object.
				//spec = new CustomerSpecification(customer);			
				
				// onSubmit create a CustomerSpecification object based on the compoundPropertyModel
				Customer cust = (Customer)form.getModelObject();
				spec = new CustomerSpecification(cust);
			}

		});
		return form;
	}

	private PageableListView<Customer> createPageableListView(final String id) {

		/**
		 * the PageableListView and PagingNavigator
		 */

		PageableListView<Customer> listView = new PageableListView<Customer>(LISTVIEW_ID, customerListModel, 3) {

			private static final long serialVersionUID = 1882207820286898651L;

			@Override
			protected void populateItem(ListItem<Customer> item) {
				Customer cust = item.getModelObject();
				item.add(new Label("customerId", Model.of(cust.getCustomerId())));
				item.add(new Label("firstName", Model.of(cust.getFirstName())));
				item.add(new Label("lastName", Model.of(cust.getLastName())));
				item.add(new Label("emailAddress", Model.of(cust.getEmailAddress())));

			}

		};
		return listView;
	}

	private ModalWindow createModalWindow(final String id) {

		/**
		 * the ModalWindow
		 */

		// createCustomerWindow = new ModalWindow(CREATECUSTOMERWINDOW_ID);
		CreateCustomerPanel createCustomerPanel = new CreateCustomerPanel(createCustomerWindow.getContentId(),
				createCustomerWindow);
		createCustomerWindow.setContent(createCustomerPanel);
		createCustomerWindow.setCookieName("modal-1");
		createCustomerWindow.setTitle(Model.of("create customer"));
		setOutputMarkupId(true);

		add(new AjaxLink<Void>(LINK_CREATE_CUSTOMER) {
			private static final long serialVersionUID = 5218474796306160615L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				createCustomerWindow.show(target);

			}

		});

		return createCustomerWindow;

	}

}
