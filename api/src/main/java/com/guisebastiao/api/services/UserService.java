package com.guisebastiao.api.services;

import com.guisebastiao.api.controllers.dtos.DefaultDTO;
import com.guisebastiao.api.controllers.dtos.UserDTO;
import com.guisebastiao.api.controllers.dtos.UserUpdateDTO;
import com.guisebastiao.api.controllers.mappers.UserMapper;
import com.guisebastiao.api.exceptions.EntityNotFoundException;
import com.guisebastiao.api.models.User;
import com.guisebastiao.api.repositories.UserRepository;
import com.guisebastiao.api.security.AuthProvider;
import com.guisebastiao.api.utils.UUIDConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private UserMapper userMapper;

    public DefaultDTO findById(String userId) {
        User user = this.userRepository.findById(UUIDConverter.toUUID(userId))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não foi encontrado"));

        UserDTO userDTO = this.userMapper.toDTO(user);

        return new DefaultDTO("Usuário encontrado com sucesso", Boolean.TRUE, userDTO, null, null);
    }

    public DefaultDTO update(UserUpdateDTO dto) {
        User user = this.authProvider.getAuthUser();

        User userUpdated = new User();
        userUpdated.setId(user.getId());
        userUpdated.setName(dto.name());
        userUpdated.setEmail(user.getEmail());
        userUpdated.setPassword(user.getPassword());
        userUpdated.setCpf(dto.cpf());
        userUpdated.setPhone(dto.phone());

        this.userRepository.save(userUpdated);

        return new DefaultDTO("Sua conta foi atualizada com sucesso", Boolean.TRUE, null, null, null);
    }

    public DefaultDTO delete() {
        User user = this.authProvider.getAuthUser();

        this.userRepository.delete(user);

        return new DefaultDTO("Sua conta foi deletada com sucesso", Boolean.TRUE, null, null, null);
    }
}
