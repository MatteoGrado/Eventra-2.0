package de.grado.userservice.service;

import de.grado.userservice.exceptions.EmailAlreadyExistsException;
import de.grado.userservice.exceptions.UserNotFoundException;
import de.grado.userservice.exceptions.WrongPasswordException;
import de.grado.userservice.form.RegisterUserForm;
import de.grado.userservice.model.CustomerProfile;
import de.grado.userservice.model.OrganizerProfile;
import de.grado.userservice.repository.CustomerProfileRepository;
import de.grado.userservice.repository.OrganizerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final OrganizerProfileRepository organizerProfileRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public void loginAsOrganizer(String email, String password)
    {
        OrganizerProfile organizer = organizerProfileRepository.findByEmail(email);

        if (organizer == null) {
            throw new UserNotFoundException("User {}, not Found!", email);
        }

        if (!passwordEncoder.matches(password, organizer.getPassword())) {
            throw new WrongPasswordException("This password is wrong");
        }
    }

    public void loginAsCustomer(String email, String password)
    {
        CustomerProfile customerProfile = customerProfileRepository.findByEmail(email);

        if (customerProfile == null) {
            throw new UserNotFoundException("User {}, not Found!", email);
        }

        if (!passwordEncoder.matches(password, customerProfile.getPassword())) {
            throw new WrongPasswordException("This password is wrong");
        }
    }

    public void register(RegisterUserForm registerUserForm)
    {
        if ("customer".equals(registerUserForm.getRole())) {
            if (customerProfileRepository.existsByEmail(registerUserForm.getEmail())) {
                throw new EmailAlreadyExistsException("Diese E-Mail-Adresse ist bereits registriert.");
            }

            CustomerProfile customer = new CustomerProfile();

            customer.setFirstName(registerUserForm.getFirstName());
            customer.setLastName(registerUserForm.getLastName());
            customer.setEmail(registerUserForm.getEmail());
            customer.setPassword(passwordEncoder.encode(registerUserForm.getPassword()));

            customerProfileRepository.save(customer);

        } else if ("organizer".equals(registerUserForm.getRole())) {
            if (organizerProfileRepository.existsByEmail(registerUserForm.getEmail())) {
                throw new EmailAlreadyExistsException("Diese E-Mail-Adresse ist bereits registriert.");
            }

            OrganizerProfile organizer = new OrganizerProfile();

            organizer.setOrganizationName(registerUserForm.getOrganizationName());
            organizer.setEmail(registerUserForm.getEmail());
            organizer.setPassword(passwordEncoder.encode(registerUserForm.getPassword()));

            organizerProfileRepository.save(organizer);
        }
    }
}
