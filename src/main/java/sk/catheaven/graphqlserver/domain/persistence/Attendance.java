package sk.catheaven.graphqlserver.domain.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @SequenceGenerator(name = "att_seq",
            sequenceName = "attendance_sequence_generator",
            initialValue = 50, allocationSize = 11)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "att_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name="fishing_ground_id")
    private FishingGround fishingGround;

    private Integer numberOfVisits;

    @Builder.Default
    @OneToMany(mappedBy = "attendance", cascade = CascadeType.ALL)
    private List<Catch> catches = new ArrayList<>();

    public void setCatches(List<Catch> catches) {
        this.catches.addAll(catches);
    }

    public Long getId() {
        return id;
    }
}
