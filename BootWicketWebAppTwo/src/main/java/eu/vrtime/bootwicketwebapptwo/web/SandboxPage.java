package eu.vrtime.bootwicketwebapptwo.web;

import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;

public class SandboxPage extends AbstractBasePage {

	private static final String FEEDBACK_ID = "feedback";
	private static final String PAGELABEL_ID = "pageLabel";
	private static final String PAGELABEL_MODEL = "SandboxPage";

	private FeedbackPanel feedback;
	private Label pageLabel;
	private WebMarkupContainer group;

	public SandboxPage() {
		super();
		// TODO Auto-generated constructor stub
		feedback = new FeedbackPanel(FEEDBACK_ID);
		pageLabel = new Label(PAGELABEL_ID, PAGELABEL_MODEL);
		group = new WebMarkupContainer("group");
	}

	@Override
	protected void onInitialize() {
		// TODO Auto-generated method stub
		super.onInitialize();
		add(feedback);
		add(pageLabel);

		group.add(new Label("label1", "hiding and unhiding the webmarkupcontainer"));
		group.add(new Label("label2", "dududada"));
		group.setVisible(false);
		group.setOutputMarkupPlaceholderTag(true);
		add(group);
		
// why is this not working??? Its apparently the same but an AjaxLink instead of AjaxFallbackLink!
//		add(new AjaxLink<Void>("link") {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				group.setVisibilityAllowed(!group.isVisible());
//				if (target != null) {
//					target.add(group);
//				}
//
//			}
//
//		});
		
		add(new AjaxFallbackLink<Void>("link") {

			@Override
			public void onClick(Optional<AjaxRequestTarget> target) {
				// TODO Auto-generated method stub
				group.setVisible(!group.isVisible());
				if (target.get() != null) {
					target.get().add(group);
				}
			}
			
			
		});

	}

}
