package eu.vrtime.restapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.restapi.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByTitle(String title);

}
