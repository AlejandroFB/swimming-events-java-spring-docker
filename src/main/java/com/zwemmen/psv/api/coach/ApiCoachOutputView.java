package com.zwemmen.psv.api.coach;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zwemmen.psv.api.generic.ApiOutputView;

import java.time.LocalDateTime;

/**
 * Coach output view including the data that is going to be returned to the client.
 *
 * @author afernandez
 */
public class ApiCoachOutputView implements ApiOutputView {
    private Integer id;
    private String emailAddress;
    private String encryptedPassword;
    private String name;
    private String club;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tokenValidAfter;

    public ApiCoachOutputView() {
    }

    public ApiCoachOutputView(Builder builder) {
        this.id = builder.id;
        this.emailAddress = builder.emailAddress;
        this.encryptedPassword = builder.encryptedPassword;
        this.name = builder.name;
        this.club = builder.club;
        this.tokenValidAfter = builder.tokenValidAfter;
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

    public static class Builder {
        private Integer id;
        private String emailAddress;
        private String encryptedPassword;
        private String name;
        private String club;
        private LocalDateTime tokenValidAfter;

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

        public Builder tokenValidAfter(LocalDateTime time) {
            this.tokenValidAfter = time;
            return this;
        }

        public ApiCoachOutputView build() { return new ApiCoachOutputView(this); }
    }
}