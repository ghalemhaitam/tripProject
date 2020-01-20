package org.sid.tripuserservice.web;

import lombok.Data;
import org.sid.tripuserservice.entities.AppUser;
import org.sid.tripuserservice.service.AccountService;
import org.sid.tripuserservice.entities.AppUser;
import org.sid.tripuserservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm) {
        return accountService.saveUser(
                userForm.getUsername(), userForm.getPassword(), userForm.getGenre(),
                userForm.getPhone(), userForm.getConfirmerdPassword(), " ", userForm.getLastname(),
                userForm.getFirstname(), userForm.getCountry(),
                userForm.getEmail(), " ");
    }
}

@Data
class UserForm {
    private String username;
    private String password;
    private String confirmerdPassword;
    private int phone;
    private String bio;
    private String email;
    private String country;
    private String firstname;
    private String PhotoProfilSrc;
    private String lastname;
    private String genre;

}
