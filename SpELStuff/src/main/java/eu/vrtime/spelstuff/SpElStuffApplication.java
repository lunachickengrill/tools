package eu.vrtime.spelstuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpElStuffApplication implements CommandLineRunner {

	public static final String TEMPLATE = "<root>" + "\n"
			+ "<firstname>#{[Person].firstname}</firstname>" + "\n"
			+ "<lastname>#{[Person].lastname}</lastname>" + "\n"
			+ "<value>#{[Person].value}</value>" + "\n"
			+ "<age>#{[Person].property.age}</age>" + "\n"
			+ "<mail>#{[Person].property.mail}</mail>" + "\n"
			+ "</root>";

	@Autowired
	private TemplateSpelParser spelParser;

	private Map<String, Object> objectMap = new HashMap<String, Object>();

	public static void main(String[] args) {

		SpringApplication.run(SpElStuffApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
			
		objectMap.put(Person.class.getSimpleName(), new Person("Tom", "Turbo", "is supa", 41, "asdf@asdf.com"));
		String evaluatedTemplate = spelParser.evaluateTemplate(objectMap, TEMPLATE);

		
	}

}
