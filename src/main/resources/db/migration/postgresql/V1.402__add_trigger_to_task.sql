CREATE OR REPLACE FUNCTION "keep_execution_timestampes" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF OLD.execution_start_at IS NOT NULL THEN
	    NEW.execution_start_at = NEW.execution_start_at;
	END IF;
	
	IF OLD.execution_end_at IS NOT NULL THEN
	    NEW.execution_end_at = NEW.execution_end_at;
	END IF;

    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "job_before_update_execution_timestamps"
  BEFORE UPDATE ON job
  FOR EACH ROW
EXECUTE PROCEDURE keep_execution_timestampes();
CREATE TRIGGER "task_before_update_execution_timestamps"
  BEFORE UPDATE ON task
  FOR EACH ROW
EXECUTE PROCEDURE keep_execution_timestampes();
--------------------------------------------------------------------------------------------------------
ALTER TABLE "task" ADD CONSTRAINT task_ck_execution_start_at_end_at CHECK 
(
   NOT (execution_start_at IS NULL AND execution_end_at IS NOT NULL)
);
ALTER TABLE "job" ADD CONSTRAINT job_ck_execution_start_at_end_at CHECK 
(
   NOT (execution_start_at IS NULL AND execution_end_at IS NOT NULL)
);
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "task_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' THEN
	    -- update finished for job
	    IF NEW.is_finished IS DISTINCT FROM OLD.is_finished AND (SELECT count(id) FROM task WHERE is_finished IS FALSE AND job_id = NEW.id) = 0 THEN
	    	UPDATE job SET is_finished = TRUE WHERE id = NEW.job_id;
	    END IF;
	    
	    -- update execution start at for job
	    IF NEW.execution_start_at IS DISTINCT FROM OLD.execution_start_at AND NEW.job_id IS NOT NULL THEN
	    	UPDATE job SET execution_start_at = NEW.execution_start_at WHERE execution_start_at IS NULL AND id = NEW.job_id;
	    END IF;
	END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "task_after_update_change_others"
  AFTER UPDATE ON task
  FOR EACH ROW
EXECUTE PROCEDURE task_update_change_others();
--------------------------------------------------------------------------------------------------------
