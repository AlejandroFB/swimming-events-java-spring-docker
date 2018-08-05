package com.zwemmen.psv.api.swimmer;

import com.zwemmen.psv.api.generic.ApiInputView;

import java.time.LocalDate;

/**
 * Swimmer input view object including the data sent from the client to create a swimmer.
 *
 * @author afernandez
 */
public class ApiSwimmerInputView implements ApiInputView {
    private Integer id;
    private String userName;
    private String password;
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

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
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
}