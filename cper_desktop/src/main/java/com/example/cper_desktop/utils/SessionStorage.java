package com.example.cper_desktop.utils;

import com.example.cper_core.enums.JwtTipoUtilizador;
import com.example.cper_core.utils.JwtUtils;
import io.jsonwebtoken.Claims;

import java.time.OffsetDateTime;
import java.util.*;

public class SessionStorage {

    private static String token;
    private static String nome;
    private static Integer id;
    private static JwtTipoUtilizador tipo;
    private static Integer setorPrincipal;
    private static Set<Integer> setoresAssociados;

    private static final JwtUtils jwtUtils = new JwtUtils();

    public static String getToken() { return token; }
    public static String getNomeUtilizador() { return nome; }
    public static Integer getUtilizadorId() { return id; }
    public static JwtTipoUtilizador getTipoUtilizador() { return tipo; }
    public static Integer getSetorPrincipal() { return setorPrincipal; }
    public static Set<Integer> getSetoresAssociados() { return setoresAssociados; }

    public static void setToken(String t) { token = t; }
    public static void setId(Integer t) { id = t; }
    public static void setTipo(JwtTipoUtilizador t) { tipo = t; }
    public static void setSetorPrincipal(Integer s) { setorPrincipal = s; }
    public static void setSetoresAssociados(Set<Integer> s) { setoresAssociados = s; }

    public static void initializeFromToken(String jwt) {
        token = jwt;

        Claims claims = jwtUtils.getClaims(jwt);

        nome = claims.get("nome", String.class);
        id = claims.get("id", Integer.class);
        tipo = JwtTipoUtilizador.valueOf(claims.get("tipo", String.class));
        setorPrincipal = claims.get("setorPrincipal", Integer.class);

        Object raw = claims.get("setoresAssociados");
        if (raw instanceof List<?> lista) {
            Set<Integer> parsed = new HashSet<>();
            for (Object item : lista) {
                if (item instanceof Integer i) parsed.add(i);
                else if (item instanceof Number n) parsed.add(n.intValue());
            }
            setoresAssociados = parsed;
        } else {
            setoresAssociados = Collections.emptySet();
        }
    }

    public static OffsetDateTime getExpiracao() {
        return jwtUtils.getExpiracao(token);
    }

    public static boolean isTokenExpirado() {
        return jwtUtils.isTokenExpirado(token);
    }

    public static void clear() {
        token = null;
        nome = null;
        id = null;
        tipo = null;
        setorPrincipal = null;
        setoresAssociados = null;
    }
}