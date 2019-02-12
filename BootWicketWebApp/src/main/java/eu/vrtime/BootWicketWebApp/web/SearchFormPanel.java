package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class SearchFormPanel extends Panel{
	
	public SearchFormPanel(String id) {
		super(id);
		add(new Label("searchFormLabel", "this is the searchFormPanel"));
		add(new SearchForm("searchForm"));
	}

}
