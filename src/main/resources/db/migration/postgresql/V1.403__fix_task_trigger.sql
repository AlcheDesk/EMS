CREATE OR REPLACE FUNCTION "task_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' THEN
		IF NEW.status_id IS NOT NULL AND NEW.status_id <> 1 AND OLD.execution_start_at IS NULL AND OLD.status_id = 1 THEN
		    NEW.execution_start_at = now();
		END IF;
		
		IF OLD.execution_start_at IS NOT NULL AND NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE AND OLD.execution_end_at IS NULL THEN
		    NEW.execution_end_at = now();
		END IF;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
	    NEW.status_id = 1;
        NEW.retry_number = 0;
        NEW.execution_start_at = NULL;
        NEW.execution_end_at = NULL;
        NEW.is_finished = FALSE;
	END IF;

    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
DROP TRIGGER "task_generate_columns" ON "task";
CREATE TRIGGER "task_generate_columns"
  BEFORE INSERT OR UPDATE ON task
  FOR EACH ROW
EXECUTE PROCEDURE task_generate_columns();