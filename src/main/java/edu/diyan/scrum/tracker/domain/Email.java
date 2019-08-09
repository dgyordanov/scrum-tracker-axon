package edu.diyan.scrum.tracker.domain;

import lombok.Value;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.Assert;

@Value
public class Email {
    private String email;

    public Email(String email) {
        Assert.isTrue(EmailValidator.getInstance().isValid(email), String.format("Invalid email: %s"));
        this.email = email;
    }

}
