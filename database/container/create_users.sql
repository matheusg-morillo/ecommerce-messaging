CREATE ROLE migration_role;
CREATE ROLE crud_role;

GRANT CONNECT ON DATABASE ecommerce_messaging TO migration_role;
GRANT USAGE ON SCHEMA public TO migration_role;
GRANT CREATE ON SCHEMA public TO migration_role;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO migration_role;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO migration_role;

GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO crud_role;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO crud_role;
GRANT UPDATE ON ALL TABLES IN SCHEMA public TO crud_role;
GRANT INSERT ON ALL TABLES IN SCHEMA public TO crud_role;
GRANT DELETE ON ALL TABLES IN SCHEMA public TO crud_role;

CREATE USER :migration_username WITH ENCRYPTED PASSWORD :'migration_password';
CREATE USER :database_username WITH ENCRYPTED PASSWORD :'database_password';

GRANT migration_role TO :migration_username;
GRANT crud_role TO :database_username;
