-- update and cleanup the task type table
ALTER TABLE "task" DROP CONSTRAINT "task_fk_type";
DELETE FROM task_type WHERE id IS NOT NULL;

-- insert the new type for JMeter
ALTER TABLE "task_type" ALTER COLUMN "uuid" DROP DEFAULT;
ALTER TABLE "task_type" ADD CONSTRAINT task_type_ix_uuid UNIQUE ("uuid");
INSERT INTO task_type (id, name, uuid, is_preloaded, is_active ) VALUES (1, 'JSON', '1925719d-691f-4051-8a30-48f687d091c5', true, true);
INSERT INTO task_type (id, name, uuid, is_preloaded, is_active ) VALUES (2, 'JMeter', '39d4e551-ab6c-4971-a3f7-c261336582d7', true, true);
UPDATE task SET task_type_id = 1 WHERE task_type_id = 9;
ALTER TABLE "task" ADD CONSTRAINT task_fk_type FOREIGN KEY ("task_type_id") REFERENCES "task_type" ("id");