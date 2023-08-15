CREATE DATABASE ecommerce_messaging;

CREATE USER :migration_username WITH ENCRYPTED PASSWORD :'migration_password';
GRANT USAGE ON SCHEMA public TO :migration_username;
GRANT CONNECT ON DATABASE ecommerce_messaging TO :migration_username;
GRANT ALL PRIVILEGES ON DATABASE ecommerce_messaging TO :migration_username;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO :migration_username;

CREATE USER :database_username WITH ENCRYPTED PASSWORD :'database_password';
GRANT USAGE ON SCHEMA public TO :database_username;
GRANT CONNECT ON DATABASE ecommerce_messaging TO :migration_username;
GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO :database_username;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO :database_username;
GRANT UPDATE ON ALL TABLES IN SCHEMA public TO :database_username;
GRANT DELETE ON ALL TABLES IN SCHEMA public TO :database_username;
GRANT INSERT ON ALL TABLES IN SCHEMA public TO :database_username;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO :database_username;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT INSERT ON TABLES TO :database_username;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT UPDATE ON TABLES TO :database_username;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT DELETE ON TABLES TO :database_username;
