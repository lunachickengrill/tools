package eu.vrtime.springbootwicket.web;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class HomepageForm extends Form{
	
	public HomepageForm(String id) {
		super(id);
		
		CompoundPropertyModel model = new CompoundPropertyModel(Model.of(""));
		this.setModel(model);
		
		this.add(new TextField("firstName"));
		this.add(new TextField("lastName"));
	}

	@Override
	protected void onSubmit() {
		// TODO Auto-generated method stub
		super.onSubmit();
	}
	

}
