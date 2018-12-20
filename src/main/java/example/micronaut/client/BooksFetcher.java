package example.micronaut.client;

import example.micronaut.domain.Book;
import io.reactivex.Flowable;

public interface BooksFetcher {

    Flowable<Book> fetchBooks();
}
