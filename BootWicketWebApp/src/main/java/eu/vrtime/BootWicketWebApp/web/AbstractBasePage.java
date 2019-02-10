package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.WebPage;

public abstract class AbstractBasePage extends WebPage {

	public AbstractBasePage() {
		super();
		add(new HeaderPanel("headerPanel"));

	}

}
