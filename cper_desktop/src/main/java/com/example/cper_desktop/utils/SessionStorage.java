package com.example.cper_desktop.utils;

import com.example.cper_core.enums.JwtTipoUtilizador;

import java.util.Set;

public class SessionStorage {
    private static String token;
    private static JwtTipoUtilizador tipo;
    private static Integer setorPrincipal;
    private static Set<Integer> setoresAssociados;

    public static String getToken() { return token; }
    public static void setToken(String t) { token = t; }

    public static JwtTipoUtilizador getTipo() { return tipo; }
    public static void setTipo(JwtTipoUtilizador t) { tipo = t; }

    public static Integer getSetorPrincipal() { return setorPrincipal; }
    public static void setSetorPrincipal(Integer s) { setorPrincipal = s; }

    public static Set<Integer> getSetoresAssociados() { return setoresAssociados; }
    public static void setSetoresAssociados(Set<Integer> s) { setoresAssociados = s; }

    public static void clear() {
        token = null;
        tipo = null;
        setorPrincipal = null;
        setoresAssociados = null;
    }
}
