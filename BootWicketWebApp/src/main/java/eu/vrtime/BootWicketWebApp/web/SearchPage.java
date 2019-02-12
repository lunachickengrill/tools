package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.basic.Label;

public class SearchPage extends AbstractBasePage {

	private ModalWindow modalWindow;

	public SearchPage() {
		super();
		modalWindow = new ModalWindow("modalWindow");

		add(new Label("searchPageLabel", "this is the search page"));
		add(new SearchFormPanel("searchFormPanel"));

		modalWindow.setTitle("create Customer");
		modalWindow.setPageCreator(new PageCreator() {

			private static final long serialVersionUID = 5604936321711681759L;

			@Override
			public Page createPage() {
				return new CreateCustomerPage();
			}
		});
		modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

			private static final long serialVersionUID = -5323526267968000929L;

			@Override
			public void onClose(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				System.out.println("ModalWindow closed");
			}
		});

		add(new AjaxLink<String>("createCustomer") {

			private static final long serialVersionUID = 4683399799357833525L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				modalWindow.show(target);
			}

		});

		add(modalWindow);

	}

}
