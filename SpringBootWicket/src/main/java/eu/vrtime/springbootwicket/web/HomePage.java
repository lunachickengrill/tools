package eu.vrtime.springbootwicket.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

@WicketHomePage
public class HomePage extends AbstractBasePage {

	public HomePage() {
		super();
		add(new Label("homePageLabel", "home page text"));
		initForm();

	}

	private void initForm() {
		Form<HomePage> form = new Form<HomePage>("form");
		add(form);
		TextField<String> textField1 = new TextField<>("textField1", Model.of(""));
		TextField<String> textField2 = new TextField<>("textField2", Model.of(""));
		form.add(textField1);
		form.add(textField2);
		Button submitBtn = new Button("submitBtn") {
			@Override
			public void onSubmit() {
				System.out.println("onSubmit, value =" + textField1);
			}
		};
		form.add(submitBtn);
	}

}
