package com.zwemmen.psv.swimmer;

import com.zwemmen.psv.generic.BaseEntity;
import com.zwemmen.psv.result.Result;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity that represents a swimmer.
 *
 * @author afernandez
 */
@Entity
@SequenceGenerator(name = "sq_swimmer", sequenceName = "sq_swimmer", allocationSize = 1)
public class Swimmer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_swimmer")
    private Integer id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = false, unique = true)
    private Integer number;
    @Column(nullable = false, unique = true)
    private Long registrationId;
    @Column(nullable = false)
    private String club;
    @Column
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column
    private LocalDateTime tokenValidAfter;

    @NotNull
    @Embedded
    private SwimmerProfile swimmerProfile;

    @OneToMany(
            mappedBy = "swimmer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Result> results = new ArrayList<>();

    public Swimmer() {
    }

    public Swimmer(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.encryptedPassword = builder.encryptedPassword;
        this.number = builder.number;
        this.registrationId = builder.registrationId;
        this.club = builder.club;
        this.level = builder.level;
        this.swimmerProfile = builder.swimmerProfile;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public Integer getNumber() {
        return number;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public String getClub() {
        return club;
    }

    public Level getLevel() {
        return level;
    }

    public LocalDateTime getTokenValidAfter() {
        return tokenValidAfter;
    }

    public SwimmerProfile getSwimmerProfile() {
        return swimmerProfile;
    }

    public List<Result> getResults() {
        return results;
    }

    /**
     * Updates the token valid after date field. Update this date in the next situations:
     *
     * - After user logs out
     * - Set up a date after the user's token was issued to revoke that specific user's token
     *
     * @param time The date time to update
     */
    public void updateTokenValidAfter(LocalDateTime time) {
        this.tokenValidAfter = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Swimmer swimmer = (Swimmer) o;
        return id.equals(swimmer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static class Builder {
        private Integer id;
        private String userName;
        private String encryptedPassword;
        private Integer number;
        private Long registrationId;
        private String club;
        private Level level;
        private SwimmerProfile swimmerProfile;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder encryptedPassword(String encryptedPassword) {
            this.encryptedPassword = encryptedPassword;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder registrationId(Long registrationId) {
            this.registrationId = registrationId;
            return this;
        }

        public Builder club(String club) {
            this.club = club;
            return this;
        }

        public Builder level(Level level) {
            this.level = level;
            return this;
        }

        public Builder swimmerProfile(SwimmerProfile swimmerProfile) {
            this.swimmerProfile = swimmerProfile;
            return this;
        }

        public Swimmer build() {
            return new Swimmer(this);
        }
    }
}