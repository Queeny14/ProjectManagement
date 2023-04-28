package com.example.jira.service;

import com.example.jira.dao.UserRepository;
import com.example.jira.entity.User;
import com.example.jira.exception.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {


        Optional<User> result = userRepository.findById(theId);

        if(result.isEmpty()){
            throw new CustomNotFoundException("Requested User id does not exist");
        }
        User theUser = null;
        theUser = result.get();
        return  theUser;
//
//        User theUser = null;
//
//        if (result.isPresent()) {
//            theUser = result.get();
//        }
//        else {
//
//            throw new RuntimeException("Did not find user id - " + theId);
//        }
//        return theUser;
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser) ;

    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);

    }
}
