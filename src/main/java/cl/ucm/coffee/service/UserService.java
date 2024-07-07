package cl.ucm.coffee.service;

import cl.ucm.coffee.persitence.entity.UserEntity;
import cl.ucm.coffee.persitence.entity.UserRoleEntity;
import cl.ucm.coffee.persitence.repository.UserRepository;
import cl.ucm.coffee.persitence.repository.UserRoleRepository;
import cl.ucm.coffee.service.dto.UserDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setUsername(userEntity.getUsername());
        userDto.setEmail(userEntity.getEmail());
        userDto.setLocked(userEntity.getLocked());
        userDto.setDisabled(userEntity.getDisabled());
        // Puedes incluir más campos según sea necesario

        return userDto;
    }

    public UserDto getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));
        return convertToDto(userEntity);
    }


}