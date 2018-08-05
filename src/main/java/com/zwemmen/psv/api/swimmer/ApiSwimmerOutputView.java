package com.zwemmen.psv.api.swimmer;

import com.zwemmen.psv.api.generic.ApiOutputView;

import java.time.LocalDate;

/**
 * Swimmer output view including the data necessary send by the client.
 *
 * @author afernandez
 */
public class ApiSwimmerOutputView implements ApiOutputView {
    private Integer id;
    private String userName;
    private String encryptedPassword;
    private Integer number;
    private Long registrationId;
    private String club;
    private String level;
    private String name;
    private String emailAddress;
    private String city;
    private String address;
    private String phone;
    private String mobilePhone;
    private LocalDate birthday;

    public ApiSwimmerOutputView() {
    }

    public ApiSwimmerOutputView(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.encryptedPassword = builder.encryptedPassword;
        this.number = builder.number;
        this.registrationId = builder.registrationId;
        this.club = builder.club;
        this.level = builder.level;
        this.name = builder.name;
        this.emailAddress = builder.emailAddress;
        this.city = builder.city;
        this.address = builder.address;
        this.phone = builder.phone;
        this.mobilePhone = builder.mobilePhone;
        this.birthday = builder.birthday;
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

    public String getLevel() {
        return level;
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
        private Integer id;
        private String userName;
        private String encryptedPassword;
        private Integer number;
        private Long registrationId;
        private String club;
        private String level;
        private String name;
        private String emailAddress;
        private String city;
        private String address;
        private String phone;
        private String mobilePhone;
        private LocalDate birthday;

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

        public Builder level(String level) {
            this.level = level;
            return this;
        }

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

        public ApiSwimmerOutputView build() {
            return new ApiSwimmerOutputView(this);
        }
    }
}