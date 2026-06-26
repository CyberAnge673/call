package com.telecom.call.model;

import com.telecom.call.enums.SipType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sip_friend")
public class SipFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name; //nombre de la extension

    @Column(name = "secret")
    private String secret;

    @Column(name = "context")
    private String context = "default";

    @Column(name = "host")
    private String host = "dynamic"; // dinamica

    @Column(name = "ip_addr")
    private String ipAddr;

    @Column(name = "sip_type")
    private SipType sipType = SipType.FRIEND;

    @OneToOne
    @JoinColumn(name = "extension_id")
    private Extension extension;
}
