package cl.ucm.coffee.service;

import cl.ucm.coffee.persitence.entity.UserEntity;
import cl.ucm.coffee.persitence.entity.UserRoleEntity;
import cl.ucm.coffee.persitence.repository.UserRepository;
import cl.ucm.coffee.persitence.repository.UserRoleRepository;
import cl.ucm.coffee.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserEntity createUser(UserDto createUserDto) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(createUserDto.getUsername());
        newUser.setPassword(createUserDto.getPassword());
        newUser.setEmail(createUserDto.getEmail());
        newUser.setLocked(createUserDto.getLocked());
        newUser.setDisabled(createUserDto.getDisabled());

        // Guarda el nuevo usuario en la base de datos
        UserEntity createdUser = userRepository.save(newUser);

        // Asigna roles al usuario
        List<String> roles = Arrays.asList("CLIENT"); // Por ejemplo, asigna el rol "ROLE_USER"
        for (String role : roles) {
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUsername(createUserDto.getUsername());
            userRole.setRole(role);
            userRole.setGrantedDate(LocalDateTime.now());
            userRoleRepository.save(userRole);
        }

        return createdUser;
    }
}