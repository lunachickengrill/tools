package eu.vrtime.bootwicketwebapptwo.web;

import org.apache.wicket.markup.html.WebPage;

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
		LinkPanel panel = new LinkPanel(id);
		return panel;
	}

}
