package eu.vrtime.bootwicketappthree.web;

import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanelLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class FirstBreadCrumbPanel extends BreadCrumbPanel{

	private static final long serialVersionUID = 140106078496447101L;
	private static final String BREADCRUMBPANELLINK_ID = "linkToSecondPanel";
	
	public FirstBreadCrumbPanel(final String id, final IBreadCrumbModel breadCrumbModel) {
		super(id, breadCrumbModel);
		add(new BreadCrumbPanelLink(BREADCRUMBPANELLINK_ID, this, SecondBreadCrumbPanel.class));
	}

	@Override
	public IModel<String> getTitle() {
		return Model.of("firstPanel");
	}
	
	

}
