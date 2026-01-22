package com.Banco.CajerosService.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class Result {

    public boolean correct;
    public String message;
    public Object object;
    public List<Object> objects;

    @JsonIgnore
    public int status;

}
