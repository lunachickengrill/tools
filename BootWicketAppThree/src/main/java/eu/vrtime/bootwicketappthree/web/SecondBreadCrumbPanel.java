package eu.vrtime.bootwicketappthree.web;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class SecondBreadCrumbPanel extends BreadCrumbPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -569403269542235537L;
	private static final String FORM_ID = "form";
	private static final String FIRSTNAME_ID = "firstName";
	private static final String LASTNAME_ID = "lastName";
	private static final String BUTTON_ID = "next";
	private String firstName = new String();
	private String lastName = new String();

	public SecondBreadCrumbPanel(final String id, final IBreadCrumbModel breadCrumbModel) {
		super(id, breadCrumbModel);
		add(createForm(FORM_ID));
	}

	private Form createForm(final String id) {
		Form form = new Form(id);
		TextField firstNameTf = new TextField<>(FIRSTNAME_ID, new PropertyModel<>(this, "firstName"));
		firstNameTf.add(new AttributeModifier("placeholder", "Enter lastname"));
		form.add(firstNameTf);
	
		TextField lastNameTf = new TextField<>(LASTNAME_ID, new PropertyModel<>(this, "lastName"));
		lastNameTf.add(new AttributeModifier("placeholder", "Enter lastname"));
		form.add(lastNameTf);

		form.add(new Button(BUTTON_ID) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				activate(new IBreadCrumbPanelFactory() {

					@Override
					public BreadCrumbPanel create(String componentId, IBreadCrumbModel breadCrumbModel) {
						return new ResultPanel(componentId, breadCrumbModel, firstName, lastName);
					}
				});
			}

		});
		return form;
	}

	@Override
	public IModel<String> getTitle() {
		return Model.of("secondPanel");
	}

}
