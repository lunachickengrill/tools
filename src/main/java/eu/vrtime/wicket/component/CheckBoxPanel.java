package eu.vrtime.wicket.component;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

@SuppressWarnings("serial")
public class CheckBoxPanel extends Panel {

	public static final String CHECKBOX_WICKETID = "cb";

	public CheckBoxPanel(String id, IModel<Boolean> model) {
		super(id, model);
		add(new CheckBox(CHECKBOX_WICKETID, model).setEnabled(true));
	}

}
