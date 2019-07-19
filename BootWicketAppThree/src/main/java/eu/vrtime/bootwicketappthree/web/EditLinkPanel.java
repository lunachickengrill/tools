package eu.vrtime.bootwicketappthree.web;

import java.util.LinkedHashMap;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class EditLinkPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3716268701155912873L;

	private FeedbackPanel feedback = new FeedbackPanel("feedback");

	public EditLinkPanel(final String id) {
		super(id);
		add(feedback);

		setOutputMarkupId(true);
		AjaxLink<Void> link = new AjaxLink<Void>("link") {

			private static final long serialVersionUID = -6261776607009230556L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				System.out.println("Edit Link clicked");
			}

		};

		link.add(new Label("label", "edit"));
		add(link);
	}

}
