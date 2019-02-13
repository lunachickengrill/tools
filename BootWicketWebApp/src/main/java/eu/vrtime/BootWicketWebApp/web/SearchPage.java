package eu.vrtime.BootWicketWebApp.web;

import javax.inject.Inject;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import eu.vrtime.BootWicketWebApp.model.CustomerDTO;
import eu.vrtime.BootWicketWebApp.repositories.CustomerRepository;

public class SearchPage extends AbstractBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7434379495347757123L;

	@Inject
	private CustomerRepository customerRepository;

	public SearchPage() {
		super();

		add(new Label("searchPageLabel", "this is the search page"));
		add(createSearchForm("searchForm"));
//		add(createModalWithPage("createCustomerWindow"));
		add(createModalWithPanel("createCustomerWindow"));

	}

	private Form<CustomerDTO> createSearchForm(String id) {

		Form<CustomerDTO> searchForm = new Form<CustomerDTO>(id);
		CustomerDTO dto = new CustomerDTO();
		searchForm.add(new TextField<String>("customerId"));
		searchForm.add(new TextField<String>("email"));
		CompoundPropertyModel<CustomerDTO> model = new CompoundPropertyModel<CustomerDTO>(dto);
		setDefaultModel(model);
		searchForm.add(new Button("submit") {

			private static final long serialVersionUID = 6187115801164320555L;

			@Override
			public void onSubmit() {
				System.out.println(">>> searchForm submit clicked <<<");
			}

		});
		return searchForm;
	}

	private ModalWindow createModalWithPanel(String id) {
		ModalWindow modalWindow = new ModalWindow(id);
//		modalWindow.add(new ModalPanel(modalWindow.getContentId()));
		modalWindow.setContent(new ModalPanel(modalWindow.getContentId()));
		modalWindow.setTitle("modal panel");
		modalWindow.setCookieName("modal-2");

		add(new AjaxLink<Void>("createCustomer") {
			private static final long serialVersionUID = 8016610384377578300L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				modalWindow.show(target);
				System.out.println(">>> modal window panel link clicked <<<");
			}

		});
		return modalWindow;
	}

//	private ModalWindow createModalWithPage(String id) {
//		ModalWindow modalWindow = new ModalWindow(id);
//		modalWindow.setTitle("Create Customer Window");
//		modalWindow.setInitialHeight(600);
//		modalWindow.setInitialWidth(800);
//		modalWindow.setPageCreator(new PageCreator() {
//
//			private static final long serialVersionUID = 5604936321711681759L;
//
//			@Override
//			public Page createPage() {
//				return new CreateCustomerPage();
//			}
//		});
//		modalWindow.setWindowClosedCallback(new WindowClosedCallback() {
//
//			private static final long serialVersionUID = -5323526267968000929L;
//
//			@Override
//			public void onClose(AjaxRequestTarget target) {
//				System.out.println("ModalWindow closed");
//			}
//		});
//
//		add(new AjaxLink<String>("createCustomer") {
//
//			private static final long serialVersionUID = 4683399799357833525L;
//
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				modalWindow.show(target);
//				System.out.println(">>> createCustomer clicked <<<");
//			}
//
//		});
//		return modalWindow;
//	}

}
