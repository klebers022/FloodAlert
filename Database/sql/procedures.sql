--Procedures DML por Tabela

--Tabela: incident

CREATE OR REPLACE PROCEDURE insert_incident (
    p_description      IN VARCHAR2,
    p_incident_type    IN VARCHAR2,
    p_coordinates      IN VARCHAR2,
    p_date_occurred    IN DATE,
    p_status           IN VARCHAR2
) AS
BEGIN
    INSERT INTO incident (id, description, incident_type, coordinates, date_occurred, status)
    VALUES (SYS_GUID(), p_description, p_incident_type, p_coordinates, p_date_occurred, p_status);
END;
/


CREATE OR REPLACE PROCEDURE update_incident_status (
    p_id       IN RAW,
    p_status   IN VARCHAR2
) AS
BEGIN
    UPDATE incident
    SET status = p_status
    WHERE id = p_id;
END;
/


CREATE OR REPLACE PROCEDURE delete_incident (
    p_id IN RAW
) AS
BEGIN
    DELETE FROM incident
    WHERE id = p_id;
END;
/


--Tabela: shelters

CREATE OR REPLACE PROCEDURE insert_shelter (
    p_name            IN VARCHAR2,
    p_total_capacity  IN NUMERIC,
    p_number_occupied IN NUMERIC,
    p_location        IN VARCHAR2,
    p_status          IN NUMBER
) AS
BEGIN
    INSERT INTO shelters (id, name, total_capacity, number_occupied, location, status)
    VALUES (SYS_GUID(), p_name, p_total_capacity, p_number_occupied, p_location, p_status);
END;
/


CREATE OR REPLACE PROCEDURE update_shelter_capacity (
    p_id             IN RAW,
    p_number_occupied IN NUMERIC
) AS
BEGIN
    UPDATE shelters
    SET number_occupied = p_number_occupied
    WHERE id = p_id;
END;
/

CREATE OR REPLACE PROCEDURE delete_shelter (
    p_id IN RAW
) AS
BEGIN
    DELETE FROM shelters
    WHERE id = p_id;
END;
/


--Tabela: alerts

CREATE OR REPLACE PROCEDURE insert_alert (
    p_title       IN VARCHAR2,
    p_description IN VARCHAR2,
    p_alert_date  IN TIMESTAMP,
    p_alert_type  IN VARCHAR2,
    p_status      IN VARCHAR2
) AS
BEGIN
    INSERT INTO alerts (id_alert, title, description, alert_date, alert_type, status)
    VALUES (SYS_GUID(), p_title, p_description, p_alert_date, p_alert_type, p_status);
END;
/


CREATE OR REPLACE PROCEDURE update_alert_status (
    p_id_alert IN RAW,
    p_status   IN VARCHAR2
) AS
BEGIN
    UPDATE alerts
    SET status = p_status
    WHERE id_alert = p_id_alert;
END;
/


CREATE OR REPLACE PROCEDURE delete_alert (
    p_id_alert IN RAW
) AS
BEGIN
    DELETE FROM alerts
    WHERE id_alert = p_id_alert;
END;
/



--Tabela: danger_areas


CREATE OR REPLACE PROCEDURE insert_danger_area (
    p_id_alert     IN RAW,
    p_name         IN VARCHAR2,
    p_description  IN VARCHAR2,
    p_threat_level IN VARCHAR2,
    p_coordinates  IN VARCHAR2
) AS
BEGIN
    INSERT INTO danger_areas (id, id_alert, name, description, threat_level, coordinates)
    VALUES (SYS_GUID(), p_id_alert, p_name, p_description, p_threat_level, p_coordinates);
END;
/


CREATE OR REPLACE PROCEDURE update_danger_level (
    p_id           IN RAW,
    p_id_alert     IN RAW,
    p_threat_level IN VARCHAR2
) AS
BEGIN
    UPDATE danger_areas
    SET threat_level = p_threat_level
    WHERE id = p_id AND id_alert = p_id_alert;
END;
/


CREATE OR REPLACE PROCEDURE delete_danger_area (
    p_id       IN RAW,
    p_id_alert IN RAW
) AS
BEGIN
    DELETE FROM danger_areas
    WHERE id = p_id AND id_alert = p_id_alert;
END;
/


