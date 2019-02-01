package eu.vrtime.springbootwicket.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class HomePageForm extends Form {

	private TextField<String> firstName = new TextField<>("firstName");
	private TextField<String> lastName = new TextField<>("lastName");
	private Label loginStatus = new Label("loginStatus", "login status");

	public HomePageForm(String id) {
		super(id);

		setDefaultModel(new CompoundPropertyModel(this));
		add(new Label("formLabel", "this is a form"));
		add(firstName);
		add(lastName);
		add(loginStatus);

		add(new Button("submitButton") {
			@Override
			public void onSubmit() {
				if (firstName.equals("tom") && lastName.equals("turbo")) {
					loginStatus.setDefaultModelObject("Passt");
					} else {
					loginStatus.setDefaultModelObject("Geht net");
				}
			}
		});
	}

}
