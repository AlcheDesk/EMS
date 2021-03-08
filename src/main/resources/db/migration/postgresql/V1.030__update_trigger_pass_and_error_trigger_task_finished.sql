CREATE OR REPLACE FUNCTION task_status_update_job_status_trigger()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    VOLATILE
AS $BODY$
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
    	INSERT INTO ems_log (log,job_id) VALUES ('Update status to WIP due to Task '||CAST(uuid_generate_v1() AS varchar(50)), NEW.job_id);
    ELSIF NEW.job_id IS NOT NULL AND OLD.status_id = wip_status_id and NEW.status_id = error_status_id THEN
    	UPDATE job set status_id = error_status_id WHERE id = NEW.job_id AND status_id = wip_status_id;
    	INSERT INTO ems_log (log,job_id) VALUES ('Update status to ERROR due to Task '||CAST(uuid_generate_v1() AS varchar(50)), NEW.job_id);
    END IF;    
    RETURN NEW;   
END;
$BODY$;

CREATE TRIGGER status_trigger_job_status BEFORE UPDATE ON task FOR EACH ROW EXECUTE PROCEDURE task_status_update_job_status_trigger();