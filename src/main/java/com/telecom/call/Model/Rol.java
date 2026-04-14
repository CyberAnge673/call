package com.telecom.call.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@Table(name = "rol")
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rol_id", unique = true, nullable = false)
  private Long id;
  @Enumerated(EnumType.STRING)
  @Column(name = "name", unique = true, nullable = false)
  private RolType name = RolType.STANDARD;

  @Column(name = "description")
  private String description;
  // relacion
  @OneToMany(mappedBy = "rol")
  private List<User> users = new ArrayList<>();

}
