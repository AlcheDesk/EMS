CREATE OR REPLACE FUNCTION "insert_updated_at_created_at_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.updated_at = now();
    NEW.created_at = now();
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "update_updated_at_created_at_worker_uuid_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.updated_at = now();
    NEW.created_at = OLD.created_at;
    NEW.worker_uuid = OLD.worker_uuid;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;


DROP TRIGGER "worker_update_created_at_updated_at_uuid" ON "worker";
DROP TRIGGER "worker_insert_created_at_updated_at_uuid" ON "worker";
DROP TRIGGER "worker_log_insert_created_at" ON "worker";
DROP TRIGGER "worker_log_update_created_at" ON "worker";
DROP TRIGGER "job_log_insert_created_at" ON "job";
DROP TRIGGER "job_log_update_created_at" ON "job";
DROP TRIGGER "task_log_insert_created_at" ON "task";
DROP TRIGGER "task_log_update_created_at" ON "task";


-- add the new triggers
-- ==========insert triggers
-- insert trigers for created_at, updated_at and uuid columns
CREATE TRIGGER "worker_insert_created_at_updated_at"
  BEFORE INSERT ON worker
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

-- insert triggers for created_at column
CREATE TRIGGER "job_log_insert_created_at"
  BEFORE INSERT ON job_log
  FOR EACH ROW
EXECUTE PROCEDURE insert_created_at_column();

CREATE TRIGGER "task_log_insert_created_at"
  BEFORE INSERT ON task_log
  FOR EACH ROW
EXECUTE PROCEDURE insert_created_at_column();

CREATE TRIGGER "worker_log_insert_created_at"
  BEFORE INSERT ON worker_log
  FOR EACH ROW
EXECUTE PROCEDURE insert_created_at_column();
-- ==========update triggers
-- update triggers for updated_at, created_at and uuid columns
CREATE TRIGGER "worker_update_created_at_updated_at_worker_uuid"
  BEFORE UPDATE ON worker
  FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_created_at_worker_uuid_column();

-- update trigger for created_at column
CREATE TRIGGER "job_log_update_created_at"
  BEFORE UPDATE ON job_log
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_column();

CREATE TRIGGER "task_log_update_created_at"
  BEFORE UPDATE ON task_log
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_column();

CREATE TRIGGER "worker_log_update_created_at"
  BEFORE UPDATE ON worker_log
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_column();