package rest.taxopark.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(Long id){
        return userRepo.findById(id).get();
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email).get();
    }

}
