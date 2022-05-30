package sk.catheaven.graphqlserver.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCatch {
    Long fishID;
    Integer totalAmount;
    Double totalWeight;
}
