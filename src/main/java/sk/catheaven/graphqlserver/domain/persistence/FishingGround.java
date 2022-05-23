package sk.catheaven.graphqlserver.domain.persistence;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Fishing Ground: Rybarsky revir
 */
@Data
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

}
