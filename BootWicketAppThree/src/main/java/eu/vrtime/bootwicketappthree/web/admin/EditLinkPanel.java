package eu.vrtime.bootwicketappthree.web.admin;

import java.util.LinkedHashMap;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class EditLinkPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3716268701155912873L;
	private static final String EDITLINK_ID = "editLink";
	private FeedbackPanel feedback = new FeedbackPanel("feedback");

	public EditLinkPanel(final String id) {
		super(id);
		add(feedback);

		setOutputMarkupId(true);
		AjaxLink<Void> link = new AjaxLink<Void>(EDITLINK_ID) {

			private static final long serialVersionUID = -6261776607009230556L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				System.out.println("Edit Link clicked");
			}

		};

		add(link);
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		
		
	}
	
	

}
