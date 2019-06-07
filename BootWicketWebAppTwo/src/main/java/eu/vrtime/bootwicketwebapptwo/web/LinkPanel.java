package eu.vrtime.bootwicketwebapptwo.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class LinkPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5812251846841802554L;
	
	private AbstractBasePage parent;

	public LinkPanel(String id, AbstractBasePage parent) {
		super(id);
		this.parent=parent;
		add(new Label("links","a panel to place links to other pages"));
		
	}

}
