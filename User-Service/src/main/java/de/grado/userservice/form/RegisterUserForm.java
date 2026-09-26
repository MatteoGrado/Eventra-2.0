package de.grado.userservice.form;

import lombok.Data;

@Data
public class RegisterUserForm
{
    private String firstName;
    private String lastName;
    private String organizationName;
    private String email;
    private String password;
    private String role;
}
