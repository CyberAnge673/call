package com.telecom.call.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Rol")
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Rol_id", unique = true, nullable = false)
  private Long id;
  @Column(name = "name", unique = true, nullable = false)
  private String name;

}
