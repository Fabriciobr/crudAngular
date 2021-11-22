package com.teste.crudAngular.validation.exception;

import lombok.Data;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@Data
public class ErrorResponse
{
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    private String message;

    private List<String> details;

}