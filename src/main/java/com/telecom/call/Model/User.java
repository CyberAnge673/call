package com.telecom.call.Model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.event.spi.MergeContext;

import com.telecom.call.Enums.StatusType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
  @Column(name = "statu_user", nullable = false)
  private StatusType userStatus;
  // Relaciones
  @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
  private Extension extension;

  @ManyToOne
  @JoinColumn(name = "rol_id")
  private Rol rol;

  @OneToMany(mappedBy = "user")
  private List<Call> calls;

  @PrePersist
  protected void onDate() {
    creationDate = LocalDateTime.now();
    if (userStatus == null) {
      userStatus = StatusType.INACTIVE;
    }
  }

}
