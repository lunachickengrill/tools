package eu.vrtime.wicket.bootstrap;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;


public class BootstrapFeedbackPanel extends FeedbackPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2452677149216179964L;

	BootstrapFeedbackPanel(String id, IFeedbackMessageFilter filter){
		super(id,filter);
	}
	
	public BootstrapFeedbackPanel(String id) {
		super(id);
	}

	@Override
	public boolean isVisible() {
		return anyMessage();
	}
	
	
}
