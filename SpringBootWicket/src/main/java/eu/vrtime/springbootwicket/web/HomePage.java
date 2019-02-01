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
		
		/**
		 * Strange label and text in textboxes?!
		 */
		add(new HomePageForm("homePageForm"));

	}



}
