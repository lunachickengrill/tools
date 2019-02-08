package eu.vrtime.springbootwicket.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;



public class SearchForm extends Form<SearchForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -856773829750348940L;
	private TextField<String> customerId;
	private TextField<String> emailAddress;
	private Label displayText;
	RepeatingView listItems = new RepeatingView("listItems");
	

	public SearchForm(String id) {
		super(id);

		customerId = new TextField<String>("customerId");
		emailAddress = new TextField<String>("emailAddress");
		displayText = new Label("displayText");

		setDefaultModel(new CompoundPropertyModel<>(this));

		add(customerId);
		add(emailAddress);

		add(createSearchButton());

	}

	public Button createSearchButton() {
		Button searchButton = new Button("searchButton") {
			@Override
			public void onSubmit() {
				displayText.setDefaultModel(new Model<String>("search submitted"));
				
				listItems.add(new Label(listItems.newChildId(),customerId.getDefaultModel()));
				listItems.add(new Label(listItems.newChildId(), emailAddress.getDefaultModel()));
				
			}
		};
		return searchButton;
	}
	
	

}
