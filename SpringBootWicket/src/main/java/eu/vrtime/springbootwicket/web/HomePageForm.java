package eu.vrtime.springbootwicket.web;

import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.digester.ArrayStack;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import eu.vrtime.springbootwicket.model.InputModel;

public class HomePageForm extends Form {

	private TextField<String> fieldOne;
	private TextField<String> fieldTwo;
	private TextField<String> fieldThree;

	private Label labelFieldOne;
	private Label labelFieldTwo;
	private Label labelFieldThree;

	private Label displayLabel = new Label("displayLabel");
	private List<String> dropDownChoices = Arrays.asList("choice A", "choice B", "choice C");
	

	public HomePageForm(String id) {
		super(id);

		add(new Label("formLabel", "this is a form"));
		


//		comment in this block to see the strange behaviour
		
//		setDefaultModel(new CompoundPropertyModel(this));
//		
//		labelFieldOne = new Label("labelFieldOne");
//		labelFieldTwo = new Label("labelFieldTwo");
//		labelFieldThree = new Label("labelFieldThree");
//		
//		fieldOne = new TextField("fieldOne");
//		fieldTwo = new TextField("fieldTwo");
//		fieldThree = new TextField("fieldThree");

		
//		comment out and enable block aboove
		
		fieldOne = new TextField("fieldOne", Model.of(""));
		fieldTwo = new TextField("fieldTwo", Model.of(""));
		fieldThree = new TextField("fieldThree", Model.of(""));

		labelFieldOne = new Label("labelFieldOne", Model.of(""));
		labelFieldTwo = new Label("labelFieldTwo", Model.of(""));
		labelFieldThree = new Label("labelFieldThree", Model.of(""));
			

		add(displayLabel);
		add(new DropDownChoice<String>("dropDownChoices", new Model<>(),dropDownChoices ));
		add(fieldOne);
		add(fieldTwo);
		add(fieldThree);

		add(labelFieldOne);
		add(labelFieldTwo);
		add(labelFieldThree);

		add(new Button("submitButton") {
			@Override
			public void onSubmit() {
				displayLabel.setDefaultModel(new Model<String>("submitted"));
				
				labelFieldOne.setDefaultModelObject(fieldOne.getModelObject());
				labelFieldTwo.setDefaultModelObject(fieldTwo.getModelObject());
				labelFieldThree.setDefaultModelObject(fieldThree.getDefaultModelObject());

			}
		});
	}

}
