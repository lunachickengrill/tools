package eu.vrtime.bootwicketappthree.web;

import org.apache.wicket.extensions.breadcrumb.BreadCrumbBar;
import org.apache.wicket.markup.html.WebPage;

public class BreadCrumbPage extends WebPage{
	
	private static final String PANEL_ID = "panel";
	private static final String BREADCRUMBPBAR_ID = "breadCrumbBar";
	
	
	public BreadCrumbPage() {
		super();
		
		BreadCrumbBar breadCrumbBar = new BreadCrumbBar(BREADCRUMBPBAR_ID);
		add(breadCrumbBar);
		FirstBreadCrumbPanel firstpanel = new FirstBreadCrumbPanel(PANEL_ID, breadCrumbBar);
		add(firstpanel);
		breadCrumbBar.setActive(firstpanel);
		
		
	}

}
