package com.telecom.call.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.telecom.call.Enums.RolType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rol")
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rol_id", unique = true, nullable = false)
  private Long id;
  @Enumerated(EnumType.STRING)
  @Column(name = "name", unique = true, nullable = false)
  private RolType name = RolType.STANDARD;
  // relacion
  @OneToMany(mappedBy = "rol")
  private List<User> users = new ArrayList<>();

}
