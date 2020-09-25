package eu.vrtime.wicket.component;

import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * Creates a IColumn with a panel containing a checkbox. Uses a {@link CheckBoxPanel.class}.
 * @author tschwaiger
 *
 */

public abstract class CheckBoxColumn extends AbstractColumn {
	
	public CheckBoxColumn(IModel displayModel) {
		super(displayModel);
	}

	@Override
	public void populateItem(Item cellItem, String componentId, IModel rowModel) {
		cellItem.add(new CheckBoxPanel(componentId, newCheckBoxModel(rowModel)));

	}

	protected CheckBox newCheckBox(String id, IModel checkModel) {
		return new CheckBox("check", checkModel);
	}

	protected abstract IModel newCheckBoxModel(IModel rowModel);


}
