package eu.vrtime.wicket.behaviour;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxCallListener;

/**
 * Adds aconfirmation dialog
 * usage e.g. in AjaxLink
 * @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                attributes.getAjaxCallListeners().add(new ConfirmationAjaxDecorator(
                        "ATTENTION! This deletes all customer related data from the database! ARE YOU SURE?"));
            }
 * @author tschwaiger
 *
 */
public class ConfirmationAjaxDecorator extends AjaxCallListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2325882950880273005L;
	
	String message;

    public ConfirmationAjaxDecorator(String message) {
        this.message = message;
    }
   

    @Override
    public CharSequence getPrecondition(Component component) {

        return "if(!confirm('" + message + "')) return false;";
    }
}
