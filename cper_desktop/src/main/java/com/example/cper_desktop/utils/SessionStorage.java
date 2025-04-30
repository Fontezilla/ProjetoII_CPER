package com.example.cper_desktop.utils;

import java.util.List;

public class SessionStorage {
    private static SessionStorage instance;
    private String token;
    private String nome;
    private String tipo;
    private List<String> setores;

    private SessionStorage() {}

    public static SessionStorage getInstance() {
        if (instance == null) {
            instance = new SessionStorage();
        }
        return instance;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public List<String> getSetores() { return setores; }
    public void setSetores(List<String> setores) { this.setores = setores; }

    public void clear() {
        token = null;
        nome = null;
        tipo = null;
        setores = null;
    }
}