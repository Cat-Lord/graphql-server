package sk.catheaven.graphqlserver.domain.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "catch")
public class Catch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="fish_id")
    private Fish fish;

    @NotNull
    @Min(value = 0)
    private Integer totalAmount;

    @NotNull
    @DecimalMin(value = "0.0")
    private Double totalWeight;

    @ManyToOne
    private Attendance attendance;

}
