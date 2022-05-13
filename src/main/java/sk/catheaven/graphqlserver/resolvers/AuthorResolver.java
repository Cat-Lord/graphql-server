package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import sk.catheaven.graphqlserver.pojo.Author;

import java.util.List;

@Component
public class AuthorResolver implements GraphQLQueryResolver {
    public List<Author> allAuthors() {
        return List.of(
                new Author(1L, "Author A"),
                new Author(2L, "Author B")
        );
    }
}
