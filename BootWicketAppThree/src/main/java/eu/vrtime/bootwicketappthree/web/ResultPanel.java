package eu.vrtime.bootwicketappthree.web;

import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.util.Assert;

public class ResultPanel extends BreadCrumbPanel {

	private static final String LABEL1_ID = "labelOne";
	private static final String LABEL2_ID = "labelTwo";

	public ResultPanel(final String id, final IBreadCrumbModel breadCrumbModel, String firstName, String lastName) {
		super(id, breadCrumbModel);
		if (firstName.isEmpty() || lastName.isEmpty()) {
			firstName = "no name";
			lastName = "no name";
		}

		add(new Label(LABEL1_ID, Model.of(firstName)));
		add(new Label(LABEL2_ID, Model.of(lastName)));
	}

	@Override
	public IModel<String> getTitle() {
		return Model.of("result");
	}

}
