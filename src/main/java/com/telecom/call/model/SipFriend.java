package com.telecom.call.model;

import com.telecom.call.enums.SipType;
import jakarta.persistence.*;
import jakarta.validation.groups.Default;

@Entity
@Table(name = "sip_friend")
public class SipFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //nombre de la extension
    private String secret;
    private String context = "default";
    private String host = "dynamic"; // dinamica
    private String ipAddress;
    private SipType sipType = SipType.FRIEND;

    @OneToOne
    @JoinColumn(name = "extension_id")
    private Extension extension;
}
