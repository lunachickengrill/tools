package eu.vrtime.bootwicketwebapptwo.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;

public class PanelOne extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747953991824658633L;
	private static final String LABEL_ID = "label";
	private static final String LABEL_TEXT = "this is PanelOne. it will be resplaced\n"
			+ "with PanelTwo when link is clicked";

	public PanelOne(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		// TODO Auto-generated method stub
		super.onInitialize();

		add(new MultiLineLabel(LABEL_ID, LABEL_TEXT));
	}

}
