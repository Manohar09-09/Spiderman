USE codequest_db;
-- Demo login admin / admin123
INSERT INTO admins(username,password) SELECT 'admin','240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9' WHERE NOT EXISTS(SELECT 1 FROM admins WHERE username='admin');
