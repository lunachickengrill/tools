package eu.vrtime.springbootwicket.web;

import org.apache.wicket.markup.html.basic.Label;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

//@WicketHomePage
@MountPath("/home")
public class HomePage extends AbstractBasePage {

	public HomePage() {
		super();
		add(new Label("homePageLabel", "home page text"));
		add(new HomePageForm("homePageForm"));

	}

}
