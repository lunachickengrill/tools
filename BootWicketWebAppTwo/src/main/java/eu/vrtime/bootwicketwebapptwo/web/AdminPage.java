package eu.vrtime.bootwicketwebapptwo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.bootwicketwebapptwo.model.Customer;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerRepository;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerServiceRepository;

public class AdminPage extends AbstractBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9076868135610334317L;
	private static final String LABEL_ADMINPAGE_ID = "labelAdminPage";
	private static final String LABLE_ADMINPAGE_MODEL = "Admin Page Label";
	private static final String FORMPANEL_ID = "formPanel";
	private static final String LISTVIEWPANEL_ID = "listViewPanel";
	private static final String LISTVIEW_ID = "listView";
	private static final String FORM_ID = "form";
	private static final String FORM_CUSTOMERID = "customerId";
	private static final String FORM_SUBMIT = "submit";
	private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
	Customer customer = new Customer();

	List<Customer> customers;

	@SpringBean
	private CustomerRepository customerRepository;

	@SpringBean
	private CustomerServiceRepository serviceRepository;

	public AdminPage() {
		super();
		add(new Label(LABEL_ADMINPAGE_ID, LABLE_ADMINPAGE_MODEL));
		add(feedbackPanel);

		Form form = new Form(FORM_ID);
		CompoundPropertyModel<Customer> model = new CompoundPropertyModel<Customer>(customer);

		form.setDefaultModel(model);
		form.add(new TextField<>(FORM_CUSTOMERID));
		form.add(new Button(FORM_SUBMIT) {

			private static final long serialVersionUID = -2644986353744688237L;

			@Override
			public void onSubmit() {

				if (model.getObject().getCustomerId() != null) {
					Long customerId = model.getObject().getCustomerId();

					info(">>> submit with value " + model.getObject().getCustomerId() + " <<<");

					customers = customerRepository.findByCustomerId(customerId).get();
					for (Customer cust : customers) {
						System.out.println(cust.toString());
					}

				} else {
					info(">>> clicked without value <<<");
				}
			}

		});
		add(form);

		PageableListView listView = new PageableListView(LISTVIEW_ID, customers, 5) {

			@Override
			protected void populateItem(ListItem item) {
				Customer cust = (Customer) item.getModelObject();
				item.add(new Label("customerId", Model.of(cust.getCustomerId())));
				item.add(new Label("firstName", Model.of(cust.getFirstName())));
				item.add(new Label("lastName", Model.of(cust.getLastName())));

			}

		};
		add(listView);
	}

}
