package com.example.SpringCURD.entityImpl;

import com.example.SpringCURD.entity.User;
import com.example.SpringCURD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserImpl {

    private UserRepository userRepository;

    @Autowired(required = true)
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setNewUser(User user){
        userRepository.save(user);
    }

    public User getUser(String email){
        try{
            User user = userRepository.findByEmail(email);
            return user;
        }catch (Exception e){}
        return null;
    }

    public boolean deleteUser(String email){
        try{
            User user = userRepository.findByEmail(email);
            userRepository.deleteById(user.getId());
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean updateUser(User user){
        User user1 = userRepository.findByEmail(user.getEmail());
        user1.setId(user.getId());
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        userRepository.save(user1);
        return false;
    }

}
