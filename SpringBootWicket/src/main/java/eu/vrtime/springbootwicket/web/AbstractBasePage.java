package eu.vrtime.springbootwicket.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public abstract class AbstractBasePage extends WebPage {

	public AbstractBasePage() {
		super();
		add(new HeaderPanel("headerPanel"));
		add(new FooterPanel("footerPanel"));

	}

}
