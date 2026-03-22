package com.telecom.call.Model;

import java.time.LocalDateTime;

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
@Builder
@Table(name = "call")
@AllArgsConstructor
@NoArgsConstructor
public class Call {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "call_id", unique = true, nullable = false)
  private Long id;
  @Column(name = "origin", nullable = false)
  private String origin;
  @Column(name = "destination", nullable = false)
  private String destination;
  @Column(name = "context", nullable = false)
  private String context;
  @Column(name = "start", nullable = false)
  private LocalDateTime start;
  @Column(name = "start", nullable = false)
  private int duration;
  @Column(name = "billsec", nullable = false)
  private int billsec;
  @Column(name = "chanel", nullable = false)
  private String chanel;
}
