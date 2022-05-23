package sk.catheaven.graphqlserver.domain;

import lombok.Value;

@Value
public class Session {
    String uid;         // user cookie
}
