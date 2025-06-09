SET SERVEROUTPUT ON;

DECLARE
    -- Cursor explícito para abrigos com vagas críticas
    CURSOR c_critical_shelters IS
        SELECT
            s.name,
            s.total_capacity,
            s.number_occupied,
            (s.total_capacity - s.number_occupied) AS available_slots,
            s.location
        FROM
            shelters s
        WHERE
            s.status = 1 AND (s.total_capacity - s.number_occupied) < 20
        ORDER BY
            available_slots ASC;

    v_shelter_name          VARCHAR2(100);
    v_total_capacity        NUMBER;
    v_number_occupied       NUMBER;
    v_available_slots       NUMBER;
    v_location              VARCHAR2(255);
    v_incident_type_count   NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Análise de Incidentes Ativos e Abrigos Críticos (' || TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') || ') ---');

    -- Contagem de incidentes ativos por tipo (usando GROUP BY, HAVING)
    DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Contagem de Incidentes Ativos por Tipo:');
    FOR incident_rec IN (
        SELECT
            incident_type,
            COUNT(*) AS total_active
        FROM
            incident
        WHERE
            status = 'Ativo'
        GROUP BY
            incident_type
        HAVING
            COUNT(*) > 0
        ORDER BY
            total_active DESC
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('  Tipo: ' || incident_rec.incident_type || ' - Ativos: ' || incident_rec.total_active);
    END LOOP;

    -- Processamento de abrigos com vagas críticas usando cursor explícito
    DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Abrigos com Vagas Críticas (menos de 20 disponíveis):');
    OPEN c_critical_shelters;
    LOOP
        FETCH c_critical_shelters INTO v_shelter_name, v_total_capacity, v_number_occupied, v_available_slots, v_location;
        EXIT WHEN c_critical_shelters%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('  Abrigo: ' || v_shelter_name);
        DBMS_OUTPUT.PUT_LINE('    Local: ' || v_location);
        DBMS_OUTPUT.PUT_LINE('    Capacidade Total: ' || v_total_capacity || ', Ocupado: ' || v_number_occupied);
        DBMS_OUTPUT.PUT_LINE('    Vagas Restantes: ' || v_available_slots);

        -- Lógica condicional (IF/ELSE)
        IF v_available_slots = 0 THEN
            DBMS_OUTPUT.PUT_LINE('    *** URGENTE: Abrigo ' || v_shelter_name || ' está lotado! ***');
        ELSE
            DBMS_OUTPUT.PUT_LINE('    Alerta: Capacidade muito baixa, atenção necessária.');
        END IF;
    END LOOP;
    CLOSE c_critical_shelters;

    IF c_critical_shelters%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('  Nenhum abrigo com vagas críticas encontrado.');
    END IF;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Fim do Relatório ---');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocorreu um erro: ' || SQLERRM);
        IF c_critical_shelters%ISOPEN THEN
            CLOSE c_critical_shelters;
        END IF;
END;
/




DECLARE
    v_recent_alerts_count NUMBER;
    v_high_threat_no_danger_areas NUMBER;
    v_total_active_incidents_linked NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Análise de Alertas Recentes e Áreas de Perigo (' || TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') || ') ---');

    -- Contar alertas emitidos na última semana (subquery)
    SELECT COUNT(*)
    INTO v_recent_alerts_count
    FROM alerts
    WHERE alert_date >= SYSDATE - INTERVAL '7' DAY;

    DBMS_OUTPUT.PUT_LINE('Total de Alertas Emitidos na Última Semana: ' || v_recent_alerts_count);

    -- Agregação e agrupamento de áreas de perigo por nível de ameaça para alertas recentes
    DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Áreas de Perigo por Nível de Ameaça (Alertas Recentes):');
    FOR danger_rec IN (
        SELECT
            da.threat_level,
            COUNT(da.id) AS total_areas,
            MAX(a.title) AS example_alert_title -- Apenas um exemplo de alerta
        FROM
            danger_areas da
        JOIN
            alerts a ON da.id_alert = a.id_alert
        WHERE
            a.alert_date >= SYSDATE - INTERVAL '7' DAY
        GROUP BY
            da.threat_level
        ORDER BY
            CASE da.threat_level
                WHEN 'Alto' THEN 1
                WHEN 'Médio' THEN 2
                WHEN 'Baixo' THEN 3
                ELSE 4
            END
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('  Nível: ' || danger_rec.threat_level || ' - Total de Áreas: ' || danger_rec.total_areas || ' (Ex: ' || danger_rec.example_alert_title || ')');
    END LOOP;

    -- Verificar se há alertas de "Alto" risco sem áreas de perigo associadas (usando subquery e NOT EXISTS)
    SELECT COUNT(*)
    INTO v_high_threat_no_danger_areas
    FROM alerts a
    WHERE a.alert_type = 'Geológico' -- Exemplo: Considerar alertas geológicos de alto risco
      AND a.status = 'Emitido'
      AND NOT EXISTS (
          SELECT 1 FROM danger_areas da WHERE da.id_alert = a.id_alert
      );

    -- Lógica condicional (IF/ELSE)
    IF v_high_threat_no_danger_areas > 0 THEN
        DBMS_OUTPUT.PUT_LINE(CHR(10) || '*** ALERTA: ' || v_high_threat_no_danger_areas || ' Alerta(s) de alto risco sem área de perigo mapeada! ***');
    ELSE
        DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Nenhum alerta de alto risco sem área de perigo mapeada encontrado.');
    END IF;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Fim da Análise ---');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocorreu um erro: ' || SQLERRM);
END;
/
