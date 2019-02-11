package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.WebPage;

public abstract class AbstractBasePage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2244641676425667756L;

	public AbstractBasePage() {
		super();
		add(new HeaderPanel("headerPanel"));

	}

}
