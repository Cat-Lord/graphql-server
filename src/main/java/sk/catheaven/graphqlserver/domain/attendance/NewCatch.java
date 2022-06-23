package sk.catheaven.graphqlserver.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCatch {
    Long fishID;
    Integer totalAmount;
    Double totalWeight;
}
