package eu.vrtime.wicket;

import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;

/**
 * A simple tab menu panel in Twitter Bootstrap style.
 * ul uses class "nav nav-tabs nav-justified".
 * 
 * @author Thomas Schwaiger
 *
 * @param <T extends ITab>
 */

public class SimpleBootstrapTabMenu<T extends ITab> extends AjaxTabbedPanel<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5414500665148430198L;

	public SimpleBootstrapTabMenu(String id, List<T> tabs) {
		super(id, tabs);

	}

	@Override
	protected String getSelectedTabCssClass() {
		return "active";
	}

	@Override
	protected String getTabContainerCssClass() {
		return "nav nav-tabs nav-justified";
	}

}
