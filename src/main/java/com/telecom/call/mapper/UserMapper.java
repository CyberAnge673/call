package com.telecom.call.mapper;

import com.telecom.call.dto.request.UserCreateRequestDto;
import com.telecom.call.dto.response.UserResponseDto;
import com.telecom.call.enums.StatusType;
import com.telecom.call.model.User;
import com.telecom.call.model.User.UserBuilder;

/*
 * mapper para la clase user entity (conversion de clases)
 */
public class UserMapper {

    private UserMapper() {
        throw new UnsupportedOperationException(
            "Esta clase no se puede instanciar"
        );
    }

    // Entity → ResponseDto (para devolver datos)
    public static UserResponseDto toUserResponseDto(User user) {
        if (user == null) {
            return null;
        }

        return UserResponseDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .userStatus(
                user.getUserStatusType() != null
                    ? user.getUserStatusType().toString()
                    : null
            )
            .extensionId(
                user.getExtension() != null ? user.getExtension().getId() : null
            )
            .rolid(user.getRol() != null ? user.getRol().getId() : null)
            .build();
    }

    // RequestDto → Entity (para guardar)
    public static User toUser(UserCreateRequestDto userCdto) {
        if (userCdto == null) {
            return null;
        }

        User.UserBuilder builder = User.builder()
            .name(userCdto.getName())
            .email(userCdto.getEmail())
            .password(userCdto.getPassword());

        // Manejar StatusType de forma segura
        if (userCdto.getUserstatus() != null) {
            try {
                builder.userStatusType(
                    StatusType.valueOf(userCdto.getUserstatus())
                );
            } catch (IllegalArgumentException e) {
                // Valor por defecto si el status no es válido
                builder.userStatusType(StatusType.ACTIVE);
            }
        } else {
            builder.userStatusType(StatusType.ACTIVE); // Valor por defecto
        }

        return builder.build();
    }

    // Método para actualizar entidad existente (update parcial)
    public static void updateUser(
        User existingUser,
        UserCreateRequestDto userCdto
    ) {
        if (existingUser == null || userCdto == null) {
            return;
        }

        if (userCdto.getName() != null) {
            existingUser.setName(userCdto.getName());
        }
        if (userCdto.getEmail() != null) {
            existingUser.setEmail(userCdto.getEmail());
        }
        if (
            userCdto.getPassword() != null && !userCdto.getPassword().isEmpty()
        ) {
            existingUser.setPassword(userCdto.getPassword());
        }
        if (userCdto.getUserstatus() != null) {
            try {
                existingUser.setUserStatusType(
                    StatusType.valueOf(userCdto.getUserstatus())
                );
            } catch (IllegalArgumentException e) {
                // Ignorar valor inválido
            }
        }
    }
}
