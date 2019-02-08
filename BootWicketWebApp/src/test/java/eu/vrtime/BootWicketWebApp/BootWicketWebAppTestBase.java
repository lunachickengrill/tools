package eu.vrtime.BootWicketWebApp;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootWicketWebAppApplication.class)
public class BootWicketWebAppTestBase {
	
	protected static final String CUSTOMERID_1 = "11111";
	protected static final String FIRSTNAME_1 = "Max";
	protected static final String LASTNAME_1 = "Mayer";
	protected static final String EMAIL_1 = "max.maier@libertyglobal.com";
	
	protected static final String CUSTOMERID_2 = "22222";
	protected static final String FIRSTNAME_2 = "Marie";
	protected static final String LASTNAME_2 = "Mueller";
	protected static final String EMAIL_2 = "marie.mueller@libertyglobal.com";

}
