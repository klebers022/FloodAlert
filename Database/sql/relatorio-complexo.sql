SELECT
    inc.description AS incident_description,
    inc.incident_type,
    inc.date_occurred,
    inc.status AS incident_status,
    a.title AS alert_title,
    a.alert_type,
    da.name AS danger_area_name,
    da.threat_level
FROM
    incident inc
LEFT JOIN -- LEFT JOIN para incluir incidentes mesmo que não haja alerta/área de perigo
    alerts a ON (
        (inc.incident_type = 'Natural' AND a.alert_type IN ('Climático', 'Geológico')) OR
        (inc.incident_type = 'Acidental' AND a.alert_type = 'Emergência') OR
        (inc.incident_type = 'Tecnológico' AND a.alert_type = 'Tecnológico') OR
        (inc.incident_type = 'Ambiental' AND a.alert_type = 'Climático') -- Exemplo de mapeamento
    )
    AND a.status = 'Emitido'
LEFT JOIN
    danger_areas da ON a.id_alert = da.id_alert
WHERE
    inc.status = 'Ativo'
ORDER BY
    inc.date_occurred DESC, inc.incident_type;




SELECT
    alert_type,
    status,
    COUNT(*) AS total_alerts
FROM
    alerts
GROUP BY
    alert_type, status
HAVING
    COUNT(*) > 1 AND status = 'Emitido' -- Somente tipos de alerta com mais de 1 alerta emitido
ORDER BY
    alert_type, total_alerts DESC;
    
    


SELECT
    da.name AS danger_area_name,
    da.description AS danger_area_description,
    da.threat_level,
    a.title AS alert_title,
    a.description AS alert_description,
    a.alert_date
FROM
    danger_areas da
JOIN
    alerts a ON da.id_alert = a.id_alert
WHERE
    da.threat_level = 'Alto'
ORDER BY
    a.alert_date DESC, da.name;
    
    
    
SELECT
    TRUNC(dt.report_date) AS report_date,
    NVL(incident_counts.num_incidents, 0) AS total_incidents,
    NVL(alert_counts.num_alerts, 0) AS total_alerts_issued
FROM
    (
        SELECT DISTINCT date_occurred AS report_date FROM incident
        UNION
        SELECT DISTINCT TRUNC(alert_date) AS report_date FROM alerts
    ) dt
LEFT JOIN
    (SELECT TRUNC(date_occurred) AS incident_date, COUNT(*) AS num_incidents
     FROM incident
     GROUP BY TRUNC(date_occurred)) incident_counts
ON dt.report_date = incident_counts.incident_date
LEFT JOIN
    (SELECT TRUNC(alert_date) AS alert_date, COUNT(*) AS num_alerts
     FROM alerts
     WHERE status = 'Emitido'
     GROUP BY TRUNC(alert_date)) alert_counts
ON dt.report_date = alert_counts.alert_date
ORDER BY
    report_date DESC;



SELECT
    da.name AS danger_area_name,
    da.threat_level,
    da.description AS danger_area_description,
    a.title AS associated_alert_title,
    a.alert_type,
    a.status AS alert_status,
    COUNT(CASE WHEN i.incident_type = 'Natural' THEN i.id END) AS incidents_natural,
    COUNT(CASE WHEN i.incident_type = 'Acidental' THEN i.id END) AS incidents_accidental,
    COUNT(CASE WHEN i.incident_type = 'Tecnológico' THEN i.id END) AS incidents_technological,
    COUNT(CASE WHEN i.incident_type = 'Ambiental' THEN i.id END) AS incidents_environmental,
    COUNT(DISTINCT i.id) AS total_incidents_linked,
    SUM(CASE WHEN s.status = 1 THEN s.total_capacity ELSE 0 END) AS total_shelter_capacity_active,
    SUM(CASE WHEN s.status = 1 THEN s.number_occupied ELSE 0 END) AS total_shelter_occupied_active,
    MAX(a.alert_date) AS latest_alert_date -- Using MAX for the alert date
FROM
    danger_areas da
JOIN
    alerts a ON da.id_alert = a.id_alert
LEFT JOIN -- LEFT JOIN to include danger areas even if no incidents are directly 'linked' by this simplified method
    incident i ON (
        -- IMPORTANT: This is a SIMPLIFIED JOIN for demonstration.
        -- Since coordinates are VARCHAR2 and SDO_GEOM isn't available,
        -- we're making a conceptual link based on incident_type matching alert_type relevance.
        (i.incident_type = 'Natural' AND a.alert_type IN ('Climático', 'Geológico')) OR
        (i.incident_type = 'Acidental' AND a.alert_type = 'Emergência') OR
        (i.incident_type = 'Tecnológico' AND a.alert_type = 'Tecnológico') OR
        (i.incident_type = 'Ambiental' AND a.alert_type = 'Climático')
    )
LEFT JOIN -- LEFT JOIN to include danger areas even if no shelters are considered 'nearby' by this simplified join
    shelters s ON 1=1 -- This provides a sum of ALL active shelter capacities, not geographically nearby ones.
WHERE
    da.threat_level IN ('Alto', 'Médio') -- Focus on high/medium threat level danger areas
GROUP BY
    da.name, da.threat_level, da.description, a.title, a.alert_type, a.status -- All non-aggregated columns must be here
HAVING
    COUNT(DISTINCT i.id) > 0 -- Ensure only areas with at least one linked incident (by our simplified logic) appear
ORDER BY
    CASE da.threat_level
        WHEN 'Alto' THEN 1
        WHEN 'Médio' THEN 2
        WHEN 'Baixo' THEN 3
        ELSE 4
    END ASC, -- Changed to ASC so Alto is first, then Medio, then Baixo
    total_incidents_linked DESC,
    MAX(a.alert_date) DESC; -- Ordering by the aggregated latest alert date
