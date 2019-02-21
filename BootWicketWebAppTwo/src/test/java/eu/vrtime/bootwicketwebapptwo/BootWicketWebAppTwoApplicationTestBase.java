package eu.vrtime.bootwicketwebapptwo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootWicketWebAppTwoApplication.class)
public class BootWicketWebAppTwoApplicationTestBase {

	public static final Long CUST1_CUSTOMERID = new Long("123");
	public static final String CUST1_FIRSTNAME = "Hansi";
	public static final String CUST1_LASTNAME = "Mueller";

	public static final Long CUST2_CUSTOMERID = new Long("456");
	public static final String CUST2_FIRSTNAME = "Freddi";
	public static final String CUST2_LASTNAME = "Meier";

	public static final String SERVICEID_1 = "AB0001";
	public static final String SERVICENAME_1 = "asd";

	public static final String SERVICEID_2 = "AB0002";
	public static final String SERVICENAME_2 = "qwe";

	public static final String SERVICEID_3 = "AB003";
	public static final String SERVICENAME_3 = " rtz";

}
