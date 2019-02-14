package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class AbstractBasePage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2244641676425667756L;
	private static final String HEADER_PANEL_ID = "headerPanel";
	private static final String LINK_HOME_ID = "home";
	private static final String LINK_SEARCH_ID = "search";

	public AbstractBasePage() {
		super();
		add(new HeaderPanel(HEADER_PANEL_ID));
		add(new BookmarkablePageLink<>(LINK_HOME_ID, HomePage.class));
		add(new BookmarkablePageLink<>(LINK_SEARCH_ID, SearchPage.class));

	}

}
