package com.telecom.call.Model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import com.telecom.call.Enums.ContextType;
import com.telecom.call.Enums.ExtensionType;
import com.telecom.call.Enums.StatusType;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "extension")
public class Extension {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_extension", unique = true, nullable = false)
  private Long id;

  @Column(name = "number", unique = true, nullable = false)
  private String number; // numero de la extension
  //
  @Column(name = "password_secret")
  private String password; // contraseña de SIP
  //
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private StatusType status; // estado de la llamada
  //
  @Column(name = "host")
  private String host; // direccion ip del telefono
  //
  @Column(name = "creation_date", nullable = false)
  private LocalDateTime creation_date;

  @Enumerated(EnumType.STRING)
  @Column(name = "extension_type", nullable = false)
  private ExtensionType extensionType = ExtensionType.SIP;

  @Enumerated(EnumType.STRING)
  @Column(name = "context_type", nullable = false)
  private ContextType contextType = ContextType.INTERNAL;

  @Column(name = "display_name")
  private String displayname;

  @Column(name = "last_register")
  private LocalDateTime lastRegister;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @PrePersist
  protected void onCreate() {
    lastRegister = LocalDateTime.now();
    creation_date = LocalDateTime.now();
  }

}
