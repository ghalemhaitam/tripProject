package org.sid.tripuserservice.service;

import org.sid.tripuserservice.dao.AppRoleRepository;
import org.sid.tripuserservice.dao.AppUserRepository;
import org.sid.tripuserservice.entities.AppRole;
import org.sid.tripuserservice.entities.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser saveUser(String username, String password, String Gender, int phone, String confirmedPassword, String bio,
                            String lastname, String firstname, String country, String email, String photoprofilsrc) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user != null) throw new RuntimeException("User already exists");
        if (!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setLastname(lastname);
        appUser.setFirstname(firstname);
        appUser.setBio(bio);
        appUser.setCountry(country);
        appUser.setPhone(phone);
        appUser.setGender(Gender);
        appUser.setEmail(email);
        appUser.setPhotoProfilSrc("https://i.ibb.co/g4xnd8P/avataaars.png");
        appUser.setActivated(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(username, "USER");
        return appUser;
    }



    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
        System.out.println(appRole.getRoleName());
    }
}
