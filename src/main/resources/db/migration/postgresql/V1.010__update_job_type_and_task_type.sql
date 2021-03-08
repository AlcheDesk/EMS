-- add job type preloaded
ALTER TABLE job_type ADD COLUMN preloaded BOOLEAN;
UPDATE job_type SET preloaded = false WHERE id IS NOT NULL;
UPDATE job_type SET preloaded = true WHERE name = 'ATM';
ALTER TABLE job_type ALTER COLUMN preloaded SET NOT NULL;
ALTER TABLE job_type ALTER COLUMN preloaded SET DEFAULT false;
-- add job type uuid
ALTER TABLE job_type ADD COLUMN uuid uuid;
UPDATE job_type SET uuid = uuid_generate_v4() WHERE id IS NOT NULL;
ALTER TABLE job_type ALTER COLUMN uuid SET NOT NULL;
ALTER TABLE job_type ALTER COLUMN uuid SET DEFAULT uuid_generate_v4();
-- insert new preloaded job type
INSERT INTO job_type (name,preloaded) VALUES ('EXTERNAL',true) ON CONFLICT DO NOTHING;



-- add task type preloaded column
ALTER TABLE task_type ADD COLUMN preloaded BOOLEAN;
ALTER TABLE task_type ALTER COLUMN preloaded SET NOT NULL;
UPDATE task_type SET preloaded = false WHERE id IS NOT NULL;
ALTER TABLE task_type ALTER COLUMN preloaded SET DEFAULT false;
--add task type uuid column
ALTER TABLE task_type ADD COLUMN uuid uuid;
UPDATE task_type SET uuid = uuid_generate_v4() WHERE id IS NOT NULL;
ALTER TABLE task_type ALTER COLUMN uuid SET NOT NULL;
ALTER TABLE task_type ALTER COLUMN uuid SET DEFAULT uuid_generate_v4();
-- insert preloaed task type
INSERT INTO task_type (name,preloaded) VALUES ('Web UI Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('Load Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('SOAP API Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('REST API Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('Script',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('Android Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('iOS Test',true)  ON CONFLICT DO NOTHING;
INSERT INTO task_type (name,preloaded) VALUES ('Mixed Test',true)  ON CONFLICT DO NOTHING;