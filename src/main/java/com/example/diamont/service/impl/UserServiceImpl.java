package com.example.diamont.service.impl;

import com.example.diamont.domain.User;
import com.example.diamont.domain.dto.UserDto;
import com.example.diamont.repositories.UserRepository;
import com.example.diamont.service.UserService;
import com.example.diamont.service.execeptions.DataIntegratyViolationException;
import com.example.diamont.service.execeptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public User finById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("objeto nao encontrado"));
    }
    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public User create(UserDto obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj,User.class));
    }

    @Override
    public User update(UserDto obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {
        finById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UserDto obj){
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegratyViolationException("E-mail ja cadastrado no sistema");
        }
    }
}
