package sk.catheaven.graphqlserver.domain.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Fishing Ground: Rybarsky revir
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fishing_ground")
public class FishingGround {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String label;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FishingGround that = (FishingGround) o;
        return id.equals(that.id) && label.equals(that.label) && code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, code);
    }
}
