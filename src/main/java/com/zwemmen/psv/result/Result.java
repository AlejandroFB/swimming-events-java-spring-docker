package com.zwemmen.psv.result;

import com.zwemmen.psv.generic.BaseEntity;
import com.zwemmen.psv.meet.Meet;
import com.zwemmen.psv.swimmer.Swimmer;

import javax.persistence.*;

/**
 * Represents the entry times achieved for swimmers in meets they have already participated.
 * Is a Many To Many relationship between Swimmer and Meet with extra data.
 *
 * @author afernandez
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"swimmer_id", "meet_id"}))
@SequenceGenerator(name = "sq_entry_time", sequenceName = "sq_entry_time", allocationSize = 1)
public class Result extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_entry_time")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "swimmer_id")
    private Swimmer swimmer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_id")
    private Meet meet;
    @Column
    private String time;
    @Column
    private boolean approvedByCoach;

    public Result() {
    }

    public Result(Builder builder) {
        this.swimmer = builder.swimmer;
        this.meet = builder.meet;
        this.time = builder.time;
    }

    public Integer getId() {
        return id;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }

    public Meet getMeet() {
        return meet;
    }

    public String getTime() {
        return time;
    }

    public boolean isApprovedByCoach() {
        return approvedByCoach;
    }

    /**
     * Approves the swimmer to participate in the associated meet.
     * This should be normally called from an entity that can approve swimmers.
     */
    public void approveSwimmerInMeet() { approvedByCoach = true; }

    public static class Builder {
        private Swimmer swimmer;
        private Meet meet;
        private String time;

        public Builder swimmer(Swimmer swimmer) {
            this.swimmer = swimmer;
            return this;
        }

        public Builder meet(Meet meet) {
            this.meet = meet;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public Result build() { return new Result(this); }
    }
}