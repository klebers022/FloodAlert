FROM gvenzl/oracle-free:latest

ENV ORACLE_PASSWORD=admin_fiap

COPY init.sql /container-entrypoint-initdb.d/init.sql

EXPOSE 1521 5500