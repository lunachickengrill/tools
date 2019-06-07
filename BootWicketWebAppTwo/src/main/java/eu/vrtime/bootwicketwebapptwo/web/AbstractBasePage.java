package eu.vrtime.bootwicketwebapptwo.web;

import org.apache.wicket.markup.html.WebPage;

/**
 * The base class of any page in this app. Adds a Panel component for links and ensures a consistent design.
 * @author babis
 *
 */

public abstract class AbstractBasePage extends WebPage {

	/**
	 *
	 */
	private static final long serialVersionUID = 2966238955891585644L;

	public AbstractBasePage() {
		super();

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(createLinkPanel("linkPanel"));
	}

	private LinkPanel createLinkPanel(String id) {
		LinkPanel panel = new LinkPanel(id, this);
		return panel;
	}

}
