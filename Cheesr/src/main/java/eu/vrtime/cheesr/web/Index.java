package eu.vrtime.cheesr.web;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import eu.vrtime.cheesr.domain.Cheese;

public class Index extends CheesrPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6159103287733661538L;

	@SuppressWarnings("unchecked")
	public Index() {
		add(new ListView("cheeses", getCheeses()) {

			private static final long serialVersionUID = -5130400667578524904L;

			@Override
			protected void populateItem(ListItem item) {
				Cheese cheese = (Cheese) item.getModelObject();

				item.add(new Label("name", new Model<>(cheese.getName())));
				item.add(new Label("description", Model.of(cheese.getDescription())));
				item.add(new Label("price", Model.of(cheese.getPrice())));

				item.add(new Link<Cheese>("add", item.getModel()) {
					private static final long serialVersionUID = 6894158284512204793L;

					@Override
					public void onClick() {
						Cheese selected = (Cheese) getModelObject();
						getCart().getCheeses().add(selected);

					}

				});

			}

		});

//		Both is working but (this, "cart.cheese") feels unatural.
		add(new ListView("cart", new PropertyModel<>(getCart(), "cheeses")) {
//		add(new ListView("cart", new PropertyModel<>(this, "cart.cheeses")) {

			private static final long serialVersionUID = 7593895543854509389L;

			@Override
			protected void populateItem(ListItem item) {
				Cheese cheese = (Cheese) item.getModelObject();
				item.add(new Label("name", Model.of(cheese.getName())));
				item.add(new Label("price", "$" + cheese.getPrice()));
				item.add(new Link<Cheese>("remove", item.getModel()) {

					private static final long serialVersionUID = -5610550641969415117L;

					@Override
					public void onClick() {
						Cheese selected = (Cheese) getModelObject();
						getCart().getCheeses().remove(selected);

					}

				});

			}

		});
		// not working as model is only set once at construction time
//		add(new Label("total", "$" + getCart().getTotal()));

//		Overriding  Model.getObject is working as getObject() is called every time the lable renders
		add(new Label("total", new Model() {

			@Override
			public Serializable getObject() {
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				return nf.format(getCart().getTotal());

			}
		}));

	}

}
