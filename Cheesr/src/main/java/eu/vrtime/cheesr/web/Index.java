package eu.vrtime.cheesr.web;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
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

		PageableListView cheeses = new PageableListView("cheeses", getCheeses(), 5) {
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

		};
		add(cheeses);
		add(new PagingNavigator("navigator", cheeses));



//		Both is working but (this, "cart.cheese") feels unatural.
//		add(new ListView("cart", new PropertyModel<>(this, "cart.cheeses")) {
		add(new ListView("cart", new PropertyModel<>(getCart(), "cheeses")) {

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

		add(new Label("total", new Model() {

			private static final long serialVersionUID = 6188430337952318338L;

//			add(new Label("total", "$" + getCart().getTotal())); is not working as model is only set only once at construction time		
//			By overriding  Model.getObject the label displays the updated value. This is working as getObject() is called every time the lable renders.
			@Override
			public Serializable getObject() {
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				return nf.format(getCart().getTotal());

			}
		}));

		add(new Link<Void>("checkout") {

			private static final long serialVersionUID = -666399422544158889L;

			@Override
			public void onClick() {
				setResponsePage(new CheckOut());

			}

			@Override
			public boolean isVisible() {
				return !getCart().getCheeses().isEmpty();
			}
		});

	}

}
