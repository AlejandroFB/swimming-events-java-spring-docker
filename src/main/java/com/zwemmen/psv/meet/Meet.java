package com.zwemmen.psv.meet;

import com.zwemmen.psv.event.competition.Competition;
import com.zwemmen.psv.generic.BaseEntity;
import com.zwemmen.psv.result.Result;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The specific meets or programs a competition has.
 * They have to be identified by a program number.
 *
 * As an example:
 *  - Pr 7: 50 Freestyle
 *  - Pr 22: 100 Butterfly
 *  - Pr 45: 400 Freestyle
 *
 * @author afernandez
 */
@Entity
@SequenceGenerator(name = "sq_meet", sequenceName = "sq_meet", allocationSize = 1)
public class Meet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_meet")
    private Integer id;
    @Column(nullable = false)
    private Integer programNumber;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Distance distance;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Stroke stroke;
    @Column(nullable = false)
    private String ageGroup;

    // TODO: Add the time (HH:mm) when the program starts. The date is in the competition.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(
            mappedBy = "meet",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Result> results = new ArrayList<>();

    public Meet() {
    }

    public Meet(Builder builder) {
        this.id = builder.id;
        this.programNumber = builder.programNumber;
        this.distance = builder.distance;
        this.stroke = builder.stroke;
        this.ageGroup = builder.ageGroup;
    }

    public Integer getId() {
        return id;
    }

    public Integer getProgramNumber() {
        return programNumber;
    }

    public Distance getDistance() {
        return distance;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public Competition getCompetition() {
        return competition;
    }

    public List<Result> getResults() {
        return results;
    }

    /**
     * Adds a competition to the meet.
     * It is used by the other side of the association to maintain the bidirectional relationship.
     *
     * @param competition The competition to join
     */
    public void addCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meet meet = (Meet) o;
        return id.equals(meet.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static class Builder {
        private Integer id;
        private Integer programNumber;
        private Distance distance;
        private Stroke stroke;
        private String ageGroup;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder programNumber(Integer programNumber) {
            this.programNumber = programNumber;
            return this;
        }

        public Builder distance(Distance distance) {
            this.distance = distance;
            return this;
        }

        public Builder stroke(Stroke stroke) {
            this.stroke = stroke;
            return this;
        }

        public Builder ageGroup(String ageGroup) {
            this.ageGroup = ageGroup;
            return this;
        }

        public Meet build() { return new Meet(this); }
    }
}