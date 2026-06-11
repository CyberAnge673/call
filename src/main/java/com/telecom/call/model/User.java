package com.telecom.call.model;

import java.time.LocalDateTime;
import java.util.List;

import com.telecom.call.enums.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_user", unique = true, nullable = false)
  private Long id;

  @Column(name = "name_user", nullable = true)
  private String name;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "creation_date", nullable = false)
  private LocalDateTime creationDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_user", nullable = false)
  private StatusType userStatusType;
  // Relaciones
  @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
  private Extension extension;

  @ManyToOne
  @JoinColumn(name = "rol_id")
  private Rol rol;

  @OneToMany(mappedBy = "user")
  private List<PhoneCall> calls;

  @PrePersist
  protected void onDate() {
    creationDate = LocalDateTime.now();
    if (userStatusType == null) {
      userStatusType = StatusType.ACTIVE;
    }
  }

}
