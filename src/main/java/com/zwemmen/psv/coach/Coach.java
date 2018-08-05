package com.zwemmen.psv.coach;

import com.zwemmen.psv.generic.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity that represents a coach.
 *
 * @author afernandez
 */
@Entity
@SequenceGenerator(name = "sq_coach", sequenceName = "sq_coach", allocationSize = 1)
public class Coach extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_coach")
    private Integer id;
    @Column(nullable = false, unique = true)
    private String emailAddress;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String club;
    @Column
    private LocalDateTime tokenValidAfter;

    public Coach() {
    }

    public Coach(Builder builder) {
        this.id = builder.id;
        this.emailAddress = builder.emailAddress;
        this.encryptedPassword = builder.encryptedPassword;
        this.name = builder.name;
        this.club = builder.club;
    }

    public Integer getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getName() {
        return name;
    }

    public String getClub() {
        return club;
    }

    public LocalDateTime getTokenValidAfter() {
        return tokenValidAfter;
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

        Coach coach = (Coach) o;
        return id.equals(coach.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static class Builder {
        private Integer id;
        private String emailAddress;
        private String encryptedPassword;
        private String name;
        private String club;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder encryptedPassword(String encryptedPassword) {
            this.encryptedPassword = encryptedPassword;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder club(String club) {
            this.club = club;
            return this;
        }

        public Coach build() { return new Coach(this); }
    }
}