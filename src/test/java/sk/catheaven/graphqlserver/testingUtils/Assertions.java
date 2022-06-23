package sk.catheaven.graphqlserver.testingUtils;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Assertions {
    /**
     * Checks whether two collections are equal without direct comparison of elements. First checks size via
     * `Collection.size()` and then checks element containment via `Collection.containsAll()`.
     * @param expected First collection.
     * @param actual Second collection.
     * @param <T> Type of the collections to test.
     */
    public static <T> void assertCollectionEquals(Collection<T> expected, Collection<T> actual) {
        boolean areEqual = (expected.size() == actual.size())  &&
                expected.containsAll(actual) &&
                actual.containsAll(expected);

        assertTrue(areEqual);
    }
}
