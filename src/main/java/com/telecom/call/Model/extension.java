package com.telecom.call.Model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

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
public class extension {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_call", unique = true, nullable = false)
  private Double id;
  @Column(name = "number", unique = true, nullable = false)
  private int number;
  @Column(name = "call_status", unique = true, nullable = false)
  private String status;
  @Column(name = "host", unique = true, nullable = false)
  private String host;
  @Column(name = "host", nullable = false)
  private LocalDateTime creation_date;

  @PrePersist
  protected void onCreate() {
    creation_date = LocalDateTime.now();
  }

}
