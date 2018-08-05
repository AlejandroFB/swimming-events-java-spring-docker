package com.zwemmen.psv.swimmer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * Profile info for a swimmer.
 *
 * @author afernandez
 */
@Embeddable
public class SwimmerProfile {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String emailAddress;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String mobilePhone;
    @Column(nullable = false)
    private LocalDate birthday;

    public SwimmerProfile() {
    }

    public SwimmerProfile(Builder builder) {
        this.name = builder.name;
        this.emailAddress = builder.emailAddress;
        this.city = builder.city;
        this.address = builder.address;
        this.phone = builder.phone;
        this.mobilePhone = builder.mobilePhone;
        this.birthday = builder.birthday;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public static class Builder {
        private String name;
        private String emailAddress;
        private String city;
        private String address;
        private String phone;
        private String mobilePhone;
        private LocalDate birthday;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder mobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
            return this;
        }

        public Builder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public SwimmerProfile build() { return new SwimmerProfile(this); }
    }
}