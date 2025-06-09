SET SERVEROUTPUT ON

CREATE OR REPLACE FUNCTION GET_AVERAGE_INCIDENT_THREAT_LEVEL RETURN NUMBER IS
    v_total_weighted_threat NUMBER := 0;
    v_total_incidents NUMBER := 0;
    v_avg_threat_level NUMBER;
BEGIN
    FOR rec IN (
        SELECT incident_type, COUNT(*) AS incident_count
        FROM incident
        GROUP BY incident_type
    ) LOOP
        v_total_incidents := v_total_incidents + rec.incident_count;

        CASE rec.incident_type
            WHEN 'Natural' THEN
                v_total_weighted_threat := v_total_weighted_threat + (rec.incident_count * 3);
            WHEN 'Tecnológico' THEN
                v_total_weighted_threat := v_total_weighted_threat + (rec.incident_count * 4);
            WHEN 'Acidental' THEN
                v_total_weighted_threat := v_total_weighted_threat + (rec.incident_count * 2);
            WHEN 'Ambiental' THEN
                v_total_weighted_threat := v_total_weighted_threat + (rec.incident_count * 1);
            ELSE
                v_total_weighted_threat := v_total_weighted_threat + (rec.incident_count * 0); -- Incidente não mapeado
        END CASE;
    END LOOP;

    IF v_total_incidents > 0 THEN
        v_avg_threat_level := v_total_weighted_threat / v_total_incidents;
    ELSE
        v_avg_threat_level := 0;
    END IF;

    RETURN ROUND(v_avg_threat_level, 2); -- Retorna a média arredondada
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro na função GET_AVERAGE_INCIDENT_THREAT_LEVEL: ' || SQLERRM);
        RETURN NULL;
END;
/

-- Como usar a função:
-- SET SERVEROUTPUT ON; -- Lembre-se de ativar para ver a saída se usar DBMS_OUTPUT em outras funções
SELECT GET_AVERAGE_INCIDENT_THREAT_LEVEL FROM DUAL;


CREATE OR REPLACE FUNCTION GET_ACTIVE_ALERT_COUNT_BY_TYPE (p_alert_type IN VARCHAR2) RETURN NUMBER IS
    v_active_alert_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_active_alert_count
    FROM alerts
    WHERE alert_type = p_alert_type
      AND status = 'Emitido'; -- Considerando 'Emitido' como um status ativo

    RETURN v_active_alert_count;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro na função GET_ACTIVE_ALERT_COUNT_BY_TYPE: ' || SQLERRM);
        RETURN NULL;
END;
/

-- Como usar a função:
-- SET SERVEROUTPUT ON;
SELECT GET_ACTIVE_ALERT_COUNT_BY_TYPE('Climático') AS ActiveClimaticAlerts FROM DUAL;
SELECT GET_ACTIVE_ALERT_COUNT_BY_TYPE('Emergência') AS ActiveEmergencyAlerts FROM DUAL;
SELECT GET_ACTIVE_ALERT_COUNT_BY_TYPE('Geológico') AS ActiveGeologicAlerts FROM DUAL;