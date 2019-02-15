package eu.vrtime.cheesr.web;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import eu.vrtime.cheesr.domain.Address;
import eu.vrtime.cheesr.domain.Cart;

public class CheckOut extends CheesrPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1555034506263691887L;

	public CheckOut() {
		add(new FeedbackPanel("feedback"));
		Form form = new Form("form");
		add(form);

		Address address = getCart().getBillingAddress();

		form.add(new TextField<>("name", new PropertyModel<>(address, "name")).setRequired(true));
		form.add(new TextField<>("street", new PropertyModel<>(address, "street")).setRequired(true));
		form.add(new TextField<>("zipcode", new PropertyModel<>(address, "zipcode")).setRequired(true));
		form.add(new TextField<>("city", new PropertyModel<>(address, "city")).setRequired(true));

		form.add(new Link<Void>("cancel") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(Index.class);
			}

		});

		form.add(new Button("order") {

			@Override
			public void onSubmit() {
				Cart cart = getCart();
				cart.getCheeses().clear();
				setResponsePage(Index.class);
			}

		});
	}

}
