package example.micronaut.client;

import example.micronaut.domain.Book;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;

@Client("example-micronaut-books")
@Requires(notEnv = Environment.TEST)
public interface BooksClient extends BooksFetcher {

    @Get("/api/books")
    @Override
    public Flowable<Book> fetchBooks();
}
