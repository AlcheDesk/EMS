CREATE OR REPLACE FUNCTION "task_status_update_task_finish_task_execution_time_trigger" ()  RETURNS trigger
  VOLATILE
AS $$
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
    	    INSERT INTO task_log (message,task_id) VALUES ('Start execution at'||to_char(now(), 'MM-DD-YYYY HH24:MI:SS'), OLD.id);
    		NEW.execution_start_at = now();
    	END IF;
    ----NEW to ERROR
    ELSIF OLD.status_id = new_status_id AND NEW.status_id = error_status_id THEN
    	----first start fail
        IF OLD.retry_number = 0 AND OLD.execution_start_at IS NULL THEN
            INSERT INTO task_log (message,task_id) VALUES ('Start execution and set to ERROR at'||to_char(now(), 'MM-DD-YYYY HH24:MI:SS')||' please contact system admin.', OLD.id);
    		NEW.execution_start_at = now();
    	END IF;
    	
    	----retry
--        IF OLD.retry_number < OLD.max_retry THEN
--    		NEW.retry_number = OLD.retry_number + 1;
--    		INSERT INTO task_log (message,task_id) VALUES ('Set task back to NEW due to retry, current retryNumber :'||NEW.retry_number::text||' maxRetry :'||OLD.max_retry::text||' at '||to_char(now(), 'MM-DD-YYYY HH24:MI:SS'), OLD.id);
--    		NEW.status_id = new_status_id;
    	----no retry
--        ELSE
        IF OLD.retry_number = OLD.max_retry THEN
            INSERT INTO task_log (message,task_id) VALUES ('Set to ERROR and finished execution at '||to_char(now(), 'MM-DD-YYYY HH24:MI:SS'), OLD.id);
			NEW.is_finished = true;
			NEW.execution_end_at = now();
--			UPDATE task_worker_link SET active = false WHERE task_id = OLD.id;
    	END IF; 
    	
    ----WIP to ERROR
    ELSIF OLD.status_id = wip_status_id AND NEW.status_id = error_status_id THEN
    	----retry
--        IF OLD.retry_number < OLD.max_retry THEN
--    	    NEW.retry_number = OLD.retry_number + 1;
--    		INSERT INTO task_log (message,task_id) VALUES ('Set task back to NEW due to retry, current retryNumber:'||NEW.retry_number::text||' maxRetry :'||OLD.max_retry::text||' at '||to_char(now(), 'MM-DD-YYYY HH24:MI:SS'), OLD.id);
--   		    NEW.status_id = new_status_id;
    	----no retry
--        ELSE
        IF OLD.retry_number = OLD.max_retry THEN
            INSERT INTO task_log (message,task_id) VALUES ('Set to ERROR and finished execution at '||to_char(now(), 'MM-DD-YYYY HH24:MI:SS'), OLD.id);
			NEW.is_finished = true;
			NEW.execution_end_at = now();
--			UPDATE task_worker_link SET active = false WHERE task_id = OLD.id;
    	END IF;   
    
    ----WIP to DONE
    ELSIF OLD.status_id = wip_status_id AND NEW.status_id = done_status_id THEN
    		INSERT INTO task_log (message,task_id) VALUES ('Set to DONE and finished execution at '||to_char(now(), 'MM-DD-YYYY HH24:MI:SS'), OLD.id);
    		NEW.is_finished = true;
			NEW.execution_end_at = now();
--			UPDATE task_worker_link SET active = false WHERE task_id = OLD.id;
    END IF;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "task_status_update_job_status_trigger" ()  RETURNS trigger
  VOLATILE
AS $$
DECLARE
	new_status_id bigint;
	wip_status_id bigint;
    done_status_id bigint;
    error_status_id bigint;
BEGIN
	select id into new_status_id from status where name = 'NEW';
	select id into wip_status_id from status where name = 'WIP';
    select id into done_status_id from status where name = 'DONE';
    select id into error_status_id from status where name = 'ERROR';

    IF NEW.job_id IS NOT NULL AND OLD.status_id = new_status_id and NEW.status_id = wip_status_id THEN
    	UPDATE job set status_id = wip_status_id WHERE id = NEW.job_id AND status_id = new_status_id;
    	INSERT INTO job_log (message,job_id) VALUES ('Update status to WIP due to Task '||CAST(OLD.uuid AS varchar(50)), NEW.job_id);
    ELSIF NEW.job_id IS NOT NULL AND OLD.status_id = new_status_id and NEW.status_id = error_status_id THEN
    	UPDATE job set status_id = error_status_id WHERE id = NEW.job_id AND status_id = new_status_id;
    	INSERT INTO job_log (message,job_id) VALUES ('Update status to ERROR due to Task '||CAST(OLD.uuid AS varchar(50)), NEW.job_id);
    ELSIF NEW.job_id IS NOT NULL AND OLD.status_id = wip_status_id and NEW.status_id = error_status_id THEN
    	UPDATE job set status_id = error_status_id WHERE id = NEW.job_id AND status_id = wip_status_id;
    	INSERT INTO job_log (message,job_id) VALUES ('Update status to ERROR due to Task '||CAST(OLD.uuid AS varchar(50)), NEW.job_id);
    END IF;    
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "finished_update_task_status_trigger" ()  RETURNS trigger
  VOLATILE
AS $$
DECLARE
	wip_status_id bigint;
    pass_status_id bigint;

BEGIN
	select id into wip_status_id from status where name = 'WIP';
    select id into pass_status_id from status where name = 'PASS';

    IF OLD.status_id = wip_status_id and OLD.is_finished = false and NEW.is_finished = true THEN
    	NEW.status_id = pass_status_id;
    END IF;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;