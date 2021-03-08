CREATE OR REPLACE FUNCTION "insert_task_retry_trigger" ()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    VOLATILE
AS $BODY$
BEGIN
    NEW.retry_number = 0;
    RETURN NEW;   
END
$BODY$;

CREATE TRIGGER insert_set_retry BEFORE UPDATE ON task FOR EACH ROW EXECUTE PROCEDURE insert_task_retry_trigger();


DROP FUNCTION "task_status_update_task_finish_trigger" ( ) CASCADE;

CREATE OR REPLACE FUNCTION task_status_update_task_finish_task_execution_time_trigger()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    VOLATILE
AS $BODY$
DECLARE
	new_status_id bigint;
	wip_status_id bigint;
    done_status_id bigint;
    error_status_id bigint;
    retry_number bigint;
    max_retry bigint;
BEGIN
	select id into new_status_id from status where name = 'NEW';
	select id into wip_status_id from status where name = 'WIP';
    select id into done_status_id from status where name = 'DONE';
    select id into error_status_id from status where name = 'ERROR';
--    retry_number := OLD.retry_number;
--    max_retry := OLD.max_retry;


    ----NEW to WIP
    IF OLD.status_id = new_status_id AND NEW.status_id = wip_status_id THEN
    	IF OLD.retry_number = 0 AND OLD.execution_start_at IS NULL THEN
    		NEW.execution_start_at = now();
    	END IF;
    ----NEW to ERROR
    ELSIF OLD.status_id = new_status_id AND NEW.status_id = error_status_id THEN
    	----first start fail
        IF OLD.retry_number = 0 AND OLD.execution_start_at IS NULL THEN
    		NEW.execution_start_at = now();
    	END IF;
    	
    	----retry
        IF OLD.retry_number < OLD.max_retry THEN
    		NEW.retry_number = OLD.retry_number + 1;
    		INSERT INTO ems_log (log,task_id) VALUES ('Set task back to NEW due to retry, current retryNumber :'||NEW.retry_number::text||' maxRetry :'||OLD.max_retry::text, OLD.id);
    		NEW.status_id = new_status_id;
    	----no retry
        ELSE
			NEW.finished = true;
			NEW.execution_end_at = now();
    	END IF; 
    	
    ----WIP to ERROR
    ELSIF OLD.status_id = wip_status_id AND NEW.status_id = error_status_id THEN
    	----retry
      IF OLD.retry_number < OLD.max_retry THEN
    	    NEW.retry_number = OLD.retry_number + 1;
    		INSERT INTO ems_log (log,task_id) VALUES ('Set task back to NEW due to retry, current retryNumber:'||NEW.retry_number::text||' maxRetry :'||OLD.max_retry::text, OLD.id);
   		    NEW.status_id = new_status_id;
    	----no retry
      ELSE
			NEW.finished = true;
			NEW.execution_end_at = now();
    	END IF;   
    
    ----WIP to DONE
    ELSIF OLD.status_id = wip_status_id AND NEW.status_id = done_status_id THEN
    		NEW.finished = true;
			NEW.execution_end_at = now();
    END IF;
    RETURN NEW;   
END;
$BODY$;

CREATE TRIGGER status_trigger_finished_exe_start_exe_end BEFORE UPDATE ON task FOR EACH ROW EXECUTE PROCEDURE task_status_update_task_finish_task_execution_time_trigger();