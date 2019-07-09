package eu.vrtime.bootwicketappthree.web;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class LinkPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8814587385870154347L;
	private static final String CUSTOMERLINK_ID = "customerLink";
	private static final String DEVICELINK_ID = "deviceLink";
	private static final String TESTLINK_ID = "testLink";
	private AdminPage parentPage;

	public LinkPanel(String id, AdminPage parent) {
		super(id);
		this.parentPage = parent;

	}

	@Override
	protected void onInitialize() {
		// TODO Auto-generated method stub
		super.onInitialize();
		add(createLink(CUSTOMERLINK_ID, parentPage.getCustomerPanel()));
		add(createLink(DEVICELINK_ID, parentPage.getDevicePanel()));
		add(createLink(TESTLINK_ID, parentPage.getTestPanel()));


	}

	public AdminPage getParentPage() {
		return parentPage;
	}

	public void setParentPage(AdminPage parentPage) {
		this.parentPage = parentPage;
	}

	private Link<Void> createLink(String id, Panel panel) {

		Link<Void> link = new Link<Void>(id) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6135494048935602523L;
			
			

			@Override
			public void onClick() {
				parentPage.getCurrent().replaceWith(panel);
				parentPage.setCurrent(panel);

			}

			@Override
			public boolean isEnabled() {
				return parentPage.getCurrent() != panel;
			}

		
			

		};
		

		
		return link;
	}

}
