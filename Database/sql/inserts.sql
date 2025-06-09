--Incident

INSERT INTO incident (id, description, incident_type, coordinates, date_occurred, status) VALUES(
SYS_GUID(),
'Inundação em bairro residencial',
'Natural', '-23.5610,-46.6587',
TO_DATE('2025-06-02', 'YYYY-MM-DD'),
'Resolvido'
);

INSERT INTO incident (id, description, incident_type, coordinates, date_occurred, status) VALUES
(SYS_GUID(), 'Deslizamento de terra', 'Natural', '-23.5505,-46.6333', TO_DATE('2025-06-01', 'YYYY-MM-DD'), 'Ativo');

INSERT INTO incident (id, description, incident_type, coordinates, date_occurred, status) VALUES
(SYS_GUID(), 'Incêndio em subestação', 'Acidental', '-23.5767,-46.6399', TO_DATE('2025-06-03', 'YYYY-MM-DD'), 'Ativo');

INSERT INTO incident (id, description, incident_type, coordinates, date_occurred, status) VALUES
(SYS_GUID(), 'Acidente com produto químico', 'Tecnológico', '-23.5900,-46.6250', TO_DATE('2025-06-04', 'YYYY-MM-DD'), 'Contido');

INSERT INTO incident (id, description, incident_type, coordinates, date_occurred, status) VALUES
(SYS_GUID(), 'Queda de árvore em avenida', 'Ambiental', '-23.6001,-46.6202', TO_DATE('2025-06-05', 'YYYY-MM-DD'), 'Resolvido');


--Shelters

INSERT INTO shelters (id, name, total_capacity, number_occupied, location, status) VALUES
(SYS_GUID(), 'Escola Municipal Jardim', 150, 75, 'Rua das Acácias, 300', 1);

INSERT INTO shelters (id, name, total_capacity, number_occupied, location, status) VALUES

(SYS_GUID(), 'Ginásio Central', 300, 200, 'Avenida Brasil, 1200', 1);

INSERT INTO shelters (id, name, total_capacity, number_occupied, location, status) VALUES
(SYS_GUID(), 'Centro Comunitário Esperança', 100, 90, 'Rua da Paz, 455', 1);

INSERT INTO shelters (id, name, total_capacity, number_occupied, location, status) VALUES
(SYS_GUID(), 'Igreja São Bento', 80, 80, 'Rua dos Andradas, 100', 0);

INSERT INTO shelters (id, name, total_capacity, number_occupied, location, status) VALUES
(SYS_GUID(), 'Universidade Popular', 250, 180, 'Av. Universitária, 999', 1);


--Alerts


INSERT INTO alerts (id_alert, title, description, alert_date, alert_type, status) VALUES
(SYS_GUID(), 'Alerta de Inundação', 'Chuvas fortes podem causar alagamentos', TO_TIMESTAMP('2025-06-01 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Climático', 'Emitido');

INSERT INTO alerts (id_alert, title, description, alert_date, alert_type, status) VALUES
(SYS_GUID(), 'Alerta de Incêndio', 'Foco de incêndio detectado em zona urbana', TO_TIMESTAMP('2025-06-02 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'Emergência', 'Emitido');

INSERT INTO alerts (id_alert, title, description, alert_date, alert_type, status) VALUES
(SYS_GUID(), 'Risco de Desabamento', 'Encosta instável após chuvas', TO_TIMESTAMP('2025-06-03 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 'Geológico', 'Investigação');

INSERT INTO alerts (id_alert, title, description, alert_date, alert_type, status) VALUES
(SYS_GUID(), 'Alerta de Vazamento Químico', 'Vazamento em indústria próxima', TO_TIMESTAMP('2025-06-04 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Tecnológico', 'Encerrado');

INSERT INTO alerts (id_alert, title, description, alert_date, alert_type, status) VALUES
(SYS_GUID(), 'Alerta de Vento Forte', 'Risco de queda de árvores e objetos', TO_TIMESTAMP('2025-06-05 07:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'Climático', 'Emitido');


--Shelters

INSERT INTO danger_areas (id, id_alert, name, description, threat_level, coordinates)
SELECT SYS_GUID(), id_alert, 'Favela da Serra', 'Alta incidência de deslizamentos', 'Alto', '-23.5501,-46.6340'
FROM alerts WHERE title = 'Risco de Desabamento';

INSERT INTO danger_areas (id, id_alert, name, description, threat_level, coordinates)
SELECT SYS_GUID(), id_alert, 'Baixada do Glicério', 'Área sujeita a inundações constantes', 'Médio', '-23.5560,-46.6280'
FROM alerts WHERE title = 'Alerta de Inundação';

INSERT INTO danger_areas (id, id_alert, name, description, threat_level, coordinates)
SELECT SYS_GUID(), id_alert, 'Distrito Industrial', 'Possível contaminação por vazamento químico', 'Alto', '-23.5890,-46.6400'
FROM alerts WHERE title = 'Alerta de Vazamento Químico';

INSERT INTO danger_areas (id, id_alert, name, description, threat_level, coordinates)
SELECT SYS_GUID(), id_alert, 'Região Central', 'Risco de incêndios em edificações antigas', 'Médio', '-23.5540,-46.6350'
FROM alerts WHERE title = 'Alerta de Incêndio';

INSERT INTO danger_areas (id, id_alert, name, description, threat_level, coordinates)
SELECT SYS_GUID(), id_alert, 'Parque dos Ventos', 'Região com rajadas acima de 80km/h', 'Baixo', '-23.5622,-46.6100'
FROM alerts WHERE title = 'Alerta de Vento Forte';




