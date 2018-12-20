package example.micronaut.controller;

import example.micronaut.client.BooksFetcher;
import example.micronaut.client.InventoryFetcher;
import example.micronaut.domain.Book;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;

@Controller("/api")
public class BooksController {

    private final BooksFetcher booksFetcher;
    private final InventoryFetcher inventoryFetcher;

    public BooksController(BooksFetcher booksFetcher, InventoryFetcher inventoryFetcher) {
        this.booksFetcher = booksFetcher;
        this.inventoryFetcher = inventoryFetcher;
    }

    @Get(value = "/books", consumes = MediaType.APPLICATION_JSON)
    Flowable<Book> findAll() {
        System.out.println("Received request");
        return booksFetcher.fetchBooks()
                .flatMapMaybe(b -> inventoryFetcher.inventory(b.getIsbn())
                        .filter(stock -> stock > 0)
                        .map(stock -> {
                            b.setStock(stock);
                            return b;
                        })
                );

    }
}
