package eu.vrtime.bootwicketwebapptwo.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;

public class PanelTwo extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5578558746716384702L;
	private static final String LABEL_ID = "label";
	private static final String LABEL_TEXT = "this is panelTwo. It will be replaced with\n "
			+ "panelOne when link is clicked.";

	public PanelTwo(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		// TODO Auto-generated method stub
		super.onInitialize();
		add(new MultiLineLabel(LABEL_ID, LABEL_TEXT));
	}

}
