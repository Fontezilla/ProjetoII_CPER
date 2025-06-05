package com.example.cper_desktop.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ZombieConnectionCleaner {

    private final JdbcTemplate jdbcTemplate;

    public ZombieConnectionCleaner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(fixedRate = 300000) // a cada 5 minutos
    public void checkAndCleanIdleConnections() {
        String query = """
            SELECT pid, usename, client_addr, state, now() - state_change AS idle_duration
            FROM pg_stat_activity
            WHERE state = 'idle'
              AND now() - state_change > interval '10 minutes'
              AND usename != 'supabase_admin'
              AND pid <> pg_backend_pid();
        """;

        List<Map<String, Object>> idleConnections = jdbcTemplate.queryForList(query);

        if (!idleConnections.isEmpty()) {
            System.out.println("⚠ Conexões inativas (mais de 10 min) detectadas e serão encerradas:");
            for (Map<String, Object> row : idleConnections) {
                Long pid = ((Number) row.get("pid")).longValue();
                System.out.printf("→ PID %s | User %s | Idle: %s%n",
                        pid, row.get("usename"), row.get("idle_duration"));

                try {
                    String killQuery = String.format("SELECT pg_terminate_backend(%d);", pid);
                    jdbcTemplate.execute(killQuery);
                    System.out.printf("✔ PID %d encerrado com sucesso.%n", pid);
                } catch (Exception e) {
                    System.err.printf("✖ Falha ao encerrar PID %d: %s%n", pid, e.getMessage());
                }
            }
        } else {
            System.out.println("✅ Nenhuma conexão inativa (10+ min) encontrada.");
        }
    }
}