package eu.vrtime.wicket.component;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.model.IModel;

/**
 * ModalWindow that opens on page load (without Ajax)
 * 
 * @author tschwaiger
 * @see "https://cwiki.apache.org/confluence/display/WICKET/Modal+Windows"
 *
 */

public class OpenOnLoadModalWindow extends ModalWindow implements IHeaderContributor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 877520596065954819L;

	public OpenOnLoadModalWindow(String id) {
		super(id);
	}

	public OpenOnLoadModalWindow(String id, IModel<?> model) {
		super(id, model);
	}

	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		if (shouldOpen()) {
			response.render(OnDomReadyHeaderItem.forScript(getWindowOpenJavaScript()));
		}
	}

	@Override
	protected boolean makeContentVisible() {
		return shouldOpen();
	}

	protected boolean shouldOpen() {
		return true;
	}
}
