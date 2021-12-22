package com.sweetspotrecipies.sweetspot.service;

import com.sweetspotrecipies.sweetspot.model.Book;
import com.sweetspotrecipies.sweetspot.model.User;
import com.sweetspotrecipies.sweetspot.repository.BookRepository;
import com.sweetspotrecipies.sweetspot.repository.RecipeRepository;
import com.sweetspotrecipies.sweetspot.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> listAll() {
        return userRepository.findAll();
    }


    public void save(User user) {
        if(user.getId() != null){
            User existingUser = this.find(user.getId());

            System.out.println("test");

            existingUser.setModified(new Date());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setRoles(user.getRoles());

            userRepository.save(existingUser);
        }
    }

    public User find(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        User user = this.find(id);
        userRepository.delete(user);
    }
}