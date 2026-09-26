package de.grado.userservice.exceptions;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message, String email)
    {
        super(message);
    }
}
