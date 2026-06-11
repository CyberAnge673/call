package com.telecom.call.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtensionRequestDto {
  @NotBlank(message = "numero obligatorio")
  private String number;
  @NotBlank(message = "contraseña obligatoria")
  private String password;
  @NotBlank(message = "displayname obligatorio")
  private String displayname;
  @NotBlank(message = "userId obligatorio")
  private Long userId;
  private String status;
  private String host;
  private String extensionType;
  private String contextType;

}
