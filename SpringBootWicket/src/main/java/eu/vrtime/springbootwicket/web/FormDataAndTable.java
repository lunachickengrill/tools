package eu.vrtime.springbootwicket.web;

import org.apache.wicket.markup.html.basic.Label;

public class FormDataAndTable extends AbstractBasePage {
	
	public static final String PAGENAME = "query form";
	
	public FormDataAndTable() {
		super();
		add(new Label("pageLable",PAGENAME));
	}
	


}
