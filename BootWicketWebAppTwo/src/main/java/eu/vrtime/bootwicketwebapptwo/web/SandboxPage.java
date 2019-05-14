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
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class SandboxPage extends AbstractBasePage {

	private static final String FEEDBACK_ID = "feedback";
	private static final String PAGELABEL_ID = "pageLabel";
	private static final String PAGELABEL_MODEL = "SandboxPage";
	private static final String PANEL_ID = "panel";
	private static final String MARKUPCONTAINER_ID = "group";
	private static final String LINKPANEL_ONE_ID = "linkPanelOne";
	private static final String LINKPANEL_TWO_ID = "linkPanelTwo";

	private FeedbackPanel feedback;
	private Label pageLabel;
	private WebMarkupContainer group;

	private Panel panelOne = new PanelOne(PANEL_ID);
	private Panel panelTwo = new PanelTwo(PANEL_ID);
	private Panel current = panelOne;

	public SandboxPage() {
		super();

		feedback = new FeedbackPanel(FEEDBACK_ID);
		pageLabel = new Label(PAGELABEL_ID, PAGELABEL_MODEL);
		group = new WebMarkupContainer(MARKUPCONTAINER_ID);

		add(new Link<Void>(LINKPANEL_ONE_ID) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4033525299364905213L;

			@Override
			public void onClick() {
				current.replaceWith(panelOne);
				current = panelOne;

			}

			@Override
			public boolean isEnabled() {
				return current != panelOne;
			}

		});

		add(new Link<Void>(LINKPANEL_TWO_ID) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8194997222731355977L;

			@Override
			public void onClick() {
				current.replaceWith(panelTwo);
				current = panelTwo;

			}

			@Override
			public boolean isEnabled() {
				return current != panelTwo;
			}

		});

	}

	@Override
	protected void onInitialize() {

		super.onInitialize();
		add(feedback);
		add(pageLabel);

		group.add(new Label("label1", "hiding and unhiding the webmarkupcontainer"));
		group.add(new Label("label2", "dududada"));
		group.setVisible(false);
		group.setOutputMarkupPlaceholderTag(true);
		add(group);
		add(current);

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
