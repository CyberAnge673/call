package com.telecom.call.Model;

import java.time.LocalDateTime;

import com.telecom.call.Enums.ContextType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  @Enumerated(EnumType.STRING)
  @Column(name = "context", nullable = false)
  private ContextType context;
  @Column(name = "start", nullable = false)
  private LocalDateTime start;
  @Column(name = "call_duration")
  private int duration;
  @Column(name = "billsec")
  private int billsec;
  @Column(name = "chanel", nullable = false)
  private String chanel;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
