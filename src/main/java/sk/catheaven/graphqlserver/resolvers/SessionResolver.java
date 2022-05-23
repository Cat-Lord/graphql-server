package sk.catheaven.graphqlserver.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Service;
import sk.catheaven.graphqlserver.domain.Session;

@Service
public class SessionResolver implements GraphQLQueryResolver {
    public Session getSession() {
        return new Session("");
    }
}
