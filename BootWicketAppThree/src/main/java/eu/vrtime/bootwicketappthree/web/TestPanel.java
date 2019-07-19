package eu.vrtime.bootwicketappthree.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.IGenericComponent;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.SetModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.vrtime.bootwicketappthree.model.Device;
import eu.vrtime.bootwicketappthree.repositories.DeviceRepository;

public class TestPanel extends Panel {

	@SpringBean
	private DeviceRepository repo;

	private static final String FEEDBACK_ID = "feedback";
	private static final String FORM_ID = "testForm";
	private static final String DDC_ID = "type";
	private static final String BUTTON_ID = "button";
	private static final String TIMELABEL_ID = "timeLabel";
	private static final String REFRESHLINK_ID = "refreshLink";

	private String type = new String();
	private CompoundPropertyModel model;

	private SimpleDateFormat dateFormat;
	private String currentTime;

	private FeedbackPanel feedback;

	public TestPanel(final String id) {
		super(id);
		feedback = new FeedbackPanel(FEEDBACK_ID);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(feedback);
		add(createForm(FORM_ID));
		
		// adding a date/time display label that is refreshed via link
		add(createDateLabel(TIMELABEL_ID));
		add(createRefreshLink(REFRESHLINK_ID));

	}

	private Link<Void> createRefreshLink(final String id) {		
		return new Link<Void>(id) {
			private static final long serialVersionUID = 8796317657283576757L;
			@Override
			public void onClick() {
				currentTime = dateFormat.format(new Date());
			}		
		};
	}


	
	private Label createDateLabel(final String id) {
		dateFormat = new SimpleDateFormat("hh:mm:ss");
		currentTime = dateFormat.format(new Date());
		PropertyModel model = new PropertyModel<>(this, "currentTime");
		return new Label(id, model);
	}

	private Form createForm(final String id) {
		Form form = new Form(id);
		model = new CompoundPropertyModel(this);
		setDefaultModel(model);

		form.add(createDDC(DDC_ID));
		form.add(new Button(BUTTON_ID) {

			private static final long serialVersionUID = 2254855346692561735L;

			@Override
			public void onSubmit() {

				super.onSubmit();
				feedback.info(type);
			}

		});

		return form;

	}

	private Component createDDC(final String id) {
		IModel<List<String>> model = new LoadableDetachableModel<List<String>>() {

			private static final long serialVersionUID = 3454599356864208250L;

			@Override
			protected List<String> load() {
				return repo.listDeviceTypes();
			}

		};

		return new DropDownChoice<>(id, model);
	}

}
