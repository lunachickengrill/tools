package eu.vrtime.wicket;

import org.apache.wicket.feedback.IFeedbackMessageFilter;

/**
 * Feedbackpanel that creates bootstrap compatible html.
 * Only visible if message exists.
 * 
 * @author tschwaiger
 */
import org.apache.wicket.markup.html.panel.FeedbackPanel;

@SuppressWarnings("serial")
public class BootstrapFeedbackPanel extends FeedbackPanel {

    public BootstrapFeedbackPanel(final String id, final IFeedbackMessageFilter filter) {
        super(id, filter);
    }

    public BootstrapFeedbackPanel(String id) {
        super(id);
    }

    @Override
    public boolean isVisible() {
        return anyMessage();
    }

}
