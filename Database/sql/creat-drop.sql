-- Tabela: incident
CREATE TABLE incident (
    id RAW(16) PRIMARY KEY,
    description VARCHAR2(255),
    incident_type VARCHAR2(100),
    coordinates VARCHAR2(255),
    date_occurred DATE,
    status VARCHAR2(50)
);

-- Tabela: shelters
CREATE TABLE shelters (
    id RAW(16) PRIMARY KEY,
    name VARCHAR2(100),
    total_capacity NUMERIC,
    number_occupied NUMERIC,
    location VARCHAR2(255),
    status NUMBER(1) -- Booleano: 1 para ativo, 0 para inativo
);

-- Tabela: alerts
CREATE TABLE alerts (
    id_alert RAW(16) PRIMARY KEY,
    title VARCHAR2(100),
    description VARCHAR2(255),
    alert_date TIMESTAMP,
    alert_type VARCHAR2(50),
    status VARCHAR2(50)
);

-- Tabela: danger_areas
CREATE TABLE danger_areas (
    id RAW(16),
    id_alert RAW(16),
    name VARCHAR2(100),
    description VARCHAR2(255),
    threat_level VARCHAR2(50),
    coordinates VARCHAR2(255),
    PRIMARY KEY (id, id_alert),
    FOREIGN KEY (id_alert) REFERENCES alerts(id_alert)
);


-- Exclui a tabela danger_areas primeiro (possui FK para alerts)
DROP TABLE danger_areas CASCADE CONSTRAINTS;

-- Exclui a tabela alerts
DROP TABLE alerts CASCADE CONSTRAINTS;

-- Exclui a tabela shelters
DROP TABLE shelters CASCADE CONSTRAINTS;

-- Exclui a tabela incident
DROP TABLE incident CASCADE CONSTRAINTS;

