package com.zwemmen.psv.event.competition;

import com.zwemmen.psv.event.Event;
import com.zwemmen.psv.generic.BaseEntity;
import com.zwemmen.psv.meet.Meet;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity that represents a competition.
 *
 * @author afernandez
 */
@Entity
@SequenceGenerator(name = "sq_competition", sequenceName = "sq_competition", allocationSize = 1)
public class Competition extends BaseEntity implements Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_competition")
    private Integer id;
    @Column(nullable = false, unique = true)
    private Integer number;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RaceCourse course;

    @OneToMany(
            mappedBy = "competition",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Meet> meets = new ArrayList<>();

    public Competition() {
    }

    public Competition(Builder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.place = builder.place;
        this.course = builder.course;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPlace() {
        return place;
    }

    public RaceCourse getCourse() {
        return course;
    }

    public List<Meet> getMeets() {
        return meets;
    }

    /**
     * Adds a meet to the list of meets the competition maintains.
     * This utility method ensures that both sides of the bidirectional association are in sync.
     *
     * @param meet The competition to join
     */
    public void addMeet(Meet meet) {
        meets.add(meet);
        meet.addCompetition(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Competition competition = (Competition) o;
        return id.equals(competition.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static class Builder {
        private Integer id;
        private Integer number;
        private LocalDate startDate;
        private LocalDate endDate;
        private String place;
        private RaceCourse course;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder place(String place) {
            this.place = place;
            return this;
        }

        public Builder course(RaceCourse course) {
            this.course = course;
            return this;
        }

        public Competition build() { return new Competition(this); }
    }
}