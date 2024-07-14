package com.cb.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.cb.booking.constant.ApplicationConstant.EMAIL_REGEX;

@Data
@Validated
@AllArgsConstructor
public class User {

    @NotBlank(message = "User's firstName is required")
    private String firstName;

    @NotBlank(message = "User's lastName is required")
    private String lastName;

    @NotBlank(message = "User's email is required")
    @Pattern(regexp = EMAIL_REGEX, message = "Email is invalid")
    private String email;
}
