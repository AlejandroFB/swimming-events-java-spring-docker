package com.zwemmen.psv.api.coach;

import com.zwemmen.psv.api.generic.ApiInputView;

/**
 * Coach input view including the data necessary send by the client.
 *
 * @author afernandez
 */
public class ApiCoachInputView implements ApiInputView {
    private Integer id;
    private String emailAddress;
    private String password;
    private String name;
    private String club;

    public Integer getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getClub() {
        return club;
    }
}