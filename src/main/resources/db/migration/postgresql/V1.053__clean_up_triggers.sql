-- remove triggers
-- job
DROP TRIGGER "created_at_updated_at_before_update" ON "job";
DROP TRIGGER "finished_status_pass_after_update" ON "job";
DROP TRIGGER "uuid_after_update" ON "job";
--task
DROP TRIGGER "created_at_updated_at_before_update" ON "task";
DROP TRIGGER "insert_set_retry" ON "task";
DROP TRIGGER "status_trigger_finished_exe_start_exe_end" ON "task";
DROP TRIGGER "status_trigger_job_status" ON "task";
DROP TRIGGER "uuid_after_update" ON "task";
-- task_log
DROP TRIGGER "created_at_before_update" ON "task_log";
-- worker
DROP TRIGGER "created_at_updated_at_before_update" ON "worker";
DROP TRIGGER "worker_uuid_after_update" ON "worker";


-- create new function for insert trigger
CREATE OR REPLACE FUNCTION "insert_updated_at_created_at_uuid_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.updated_at = now();
    NEW.created_at = now();
    NEW.uuid = uuid_generate_v4();
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "insert_created_at_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.created_at = now();
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "insert_retry_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.retry = 0;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

-- create new function for update trigger
CREATE OR REPLACE FUNCTION "update_updated_at_created_at_uuid_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.updated_at = now();
    NEW.created_at = OLD.created_at;
    NEW.uuid = OLD.uuid;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "update_created_at_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.created_at = OLD.created_at;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

-- add the new triggers
-- ==========insert triggers
-- insert trigers for created_at, updated_at and uuid columns
CREATE TRIGGER "job_insert_created_at_updated_at_uuid"
  BEFORE INSERT ON job
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_uuid_column();

CREATE TRIGGER "task_insert_created_at_updated_at_uuid"
  BEFORE INSERT ON task
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_uuid_column();

CREATE TRIGGER "worker_insert_created_at_updated_at_uuid"
  BEFORE INSERT ON worker
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_uuid_column();

-- insert triggers for created_at column
CREATE TRIGGER "job_log_insert_created_at"
  BEFORE INSERT ON job
  FOR EACH ROW
EXECUTE PROCEDURE insert_created_at_column();

CREATE TRIGGER "task_log_insert_created_at"
  BEFORE INSERT ON task
  FOR EACH ROW
EXECUTE PROCEDURE insert_created_at_column();

CREATE TRIGGER "worker_log_insert_created_at"
  BEFORE INSERT ON worker
  FOR EACH ROW
EXECUTE PROCEDURE insert_created_at_column();

-- insert trigger for retry column
CREATE TRIGGER "task_insert_retry"
  BEFORE INSERT ON task
  FOR EACH ROW
EXECUTE PROCEDURE insert_retry_column();

-- ==========update triggers
-- update triggers for updated_at, created_at and uuid columns
CREATE TRIGGER "job_update_created_at_updated_at_uuid"
  BEFORE UPDATE ON job
  FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_created_at_uuid_column();

CREATE TRIGGER "task_update_created_at_updated_at_uuid"
  BEFORE UPDATE ON task
  FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_created_at_uuid_column();

CREATE TRIGGER "worker_update_created_at_updated_at_uuid"
  BEFORE UPDATE ON worker
  FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_created_at_uuid_column();

-- update trigger for created_at column
CREATE TRIGGER "job_log_update_created_at"
  BEFORE UPDATE ON job
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_column();

CREATE TRIGGER "task_log_update_created_at"
  BEFORE UPDATE ON task
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_column();

CREATE TRIGGER "worker_log_update_created_at"
  BEFORE UPDATE ON worker
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_column();


