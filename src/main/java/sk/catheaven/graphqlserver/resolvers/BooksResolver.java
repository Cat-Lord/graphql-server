package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import sk.catheaven.graphqlserver.pojo.Book;

import java.util.List;

@Component
public class BooksResolver implements GraphQLQueryResolver {
    public List<Book> allBooks() {
        return List.of(
                new Book(1L, "A", 10, 1L),
                new Book(2L, "B", 100, 2L),
                new Book(3L, "C", 11, 1L),
                new Book(4L, "D", 111, 1L),
                new Book(5L, "E", 1234, 2L)

        );
    }
}
