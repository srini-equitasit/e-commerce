package com.equitasit.orchestrator_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class StatusDTO implements Serializable {

    private static final long serialVersionUID = 1l;

    private String statusMsg;

    public StatusDTO(String statusMsg) {
        this.statusMsg = statusMsg;
    }

}
