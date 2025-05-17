package com.guisebastiao.api.services;

import com.guisebastiao.api.controllers.dtos.DefaultDTO;
import com.guisebastiao.api.controllers.dtos.ProfilePictureDTO;
import com.guisebastiao.api.exceptions.EntityNotFoundException;
import com.guisebastiao.api.exceptions.ServerErrorException;
import com.guisebastiao.api.models.ProfilePicture;
import com.guisebastiao.api.models.User;
import com.guisebastiao.api.repositories.ProfilePictureRepository;
import com.guisebastiao.api.repositories.UserRepository;
import com.guisebastiao.api.security.AuthProvider;
import com.guisebastiao.api.utils.UUIDConverter;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class ProfilePictureService {

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthProvider authProvider;

    @Transactional
    public DefaultDTO uploadProfilePicture(MultipartFile picture) throws Exception {
        User user = this.authProvider.getAuthUser();

        this.profilePictureRepository.findByUser(user).ifPresent(profilePicture -> {
            try {
                this.minioClient.removeObject(RemoveObjectArgs.builder()
                        .bucket("ecommerce")
                        .object(profilePicture.getObjectId().toString())
                        .build()
                );

                this.profilePictureRepository.delete(profilePicture);
            } catch (Exception e) {
                throw new ServerErrorException("Erro ao processar a foto de perfil, tente novamente mais tarde");
            }
        });

        InputStream inputStream = picture.getInputStream();
        String objectId = user.getId().toString();

        this.minioClient.putObject(PutObjectArgs.builder()
                .bucket("ecommerce")
                .object(objectId)
                .stream(inputStream, inputStream.available(), -1)
                .contentType("image/jpeg")
                .build()
        );

        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setObjectId(UUIDConverter.toUUID(objectId));
        profilePicture.setUser(user);

        this.profilePictureRepository.save(profilePicture);

        return new DefaultDTO("Foto de perfil adicionado com sucesso", Boolean.TRUE, null, null, null);
    }

    public DefaultDTO findProfilePicture(String userId) throws Exception {
        User user = this.userRepository.findById(UUIDConverter.toUUID(userId))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        ProfilePicture profilePicture = this.profilePictureRepository.findByUser(user).orElse(null);

        if(profilePicture == null) {
            return new DefaultDTO("Esse usuário não tem nenhuma foto de perfil", Boolean.TRUE, null, null, null);
        }

        String objectId = profilePicture.getObjectId().toString();

        String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("ecommerce")
                .object(objectId)
                .expiry(604800)
                .build());

        ProfilePictureDTO profilePictureDTO = new ProfilePictureDTO(url);
        return new DefaultDTO("Foto de perfil encontrada com sucesso", Boolean.TRUE, profilePictureDTO, null, null);
    }

    public DefaultDTO deleteProfilePicture() throws Exception {
        User user = this.authProvider.getAuthUser();

        ProfilePicture profilePicture = this.profilePictureRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Foto de perfil não foi encontrada"));

        UUID objectId = profilePicture.getObjectId();

        this.minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket("ecommerce")
                .object(objectId.toString())
                .build());

        this.profilePictureRepository.delete(profilePicture);

        return new DefaultDTO("Foto de perfil deletada com sucesso", Boolean.TRUE, null, null, null);
    }
}
