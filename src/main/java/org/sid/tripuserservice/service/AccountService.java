package org.sid.tripuserservice.service;

import org.sid.tripuserservice.entities.AppRole;
import org.sid.tripuserservice.entities.AppUser;

public interface AccountService {
    public AppUser saveUser(String username, String password, String Gender, int phone, String confirmedPassword,
                            String bio, String lastname, String firstname, String country, String email, String photoprofilsrc);

    public AppUser loadUserByUsername(String username);

    public AppRole save(AppRole role);

    public void addRoleToUser(String username, String rolename);
}
