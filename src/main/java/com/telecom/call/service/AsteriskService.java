package com.telecom.call.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.ManagerConnectionState;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.response.ManagerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsteriskService {

    @Value("${asterisk.ami.host:localhost}")
    private String amiHost;

    @Value("${asterisk.ami.port:5038}")
    private int amiPort;

    @Value("${asterisk.ami.username:admin}")
    private String amiUsername;

    @Value("${asterisk.ami.password:password}")
    private String amiPassword;

    private ManagerConnection managerConnection;

    @PostConstruct
    public void connect() {
        try {
            ManagerConnectionFactory factory = new ManagerConnectionFactory(
                    amiHost, amiPort, amiUsername, amiPassword);
            managerConnection = factory.createManagerConnection();
            managerConnection.login();
            log.info("Conectado a Asterisk AMI en {}:{}", amiHost, amiPort);
        } catch (Exception e) {
            log.error("Error conectando a Asterisk AMI: {}", e.getMessage());
        }
    }

    @PreDestroy
    public void disconnect() {
        if (managerConnection != null && managerConnection.getState() == ManagerConnectionState.CONNECTED) {
            try {
                managerConnection.logoff();
                log.info("Desconectado de Asterisk AMI");
            } catch (Exception e) {
                log.error("Error al desconectar AMI: {}", e.getMessage());
            }
        }
    }

    public void reloadSip() {
        try {
            if (managerConnection.getState() != ManagerConnectionState.CONNECTED) {
                log.warn("AMI no conectado, reintentando conexion");
                connect();
            }
            CommandAction action = new CommandAction("sip reload");
            ManagerResponse response = managerConnection.sendAction(action);
            log.info("SIP reload ejecutado: {}", response.getResponse());
        } catch (Exception e) {
            log.error("Error al recargar SIP: {}", e.getMessage());
        }
    }

    public boolean isConnected() {
        return managerConnection != null && managerConnection.getState() == ManagerConnectionState.CONNECTED;
    }
}
