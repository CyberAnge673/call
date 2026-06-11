package com.telecom.call.model;

import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;
import com.telecom.call.enums.ContextType;
import com.telecom.call.enums.ExtensionType;
import com.telecom.call.enums.StatusType;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

@Builder
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

  @Column(name = "number", unique = true, nullable = false, length = 30)
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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "extension", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PhoneCall> phoneCalls;

  @PrePersist
  protected void onCreate() {
    lastRegister = LocalDateTime.now();
    creation_date = LocalDateTime.now();
    if (status == null) {
      status = StatusType.INACTIVE;
    }
  }

}
