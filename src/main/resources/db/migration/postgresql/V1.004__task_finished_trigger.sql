CREATE OR REPLACE FUNCTION finished_update_task_status_trigger()
    RETURNS trigger
    LANGUAGE 'plpgsql'
AS $BODY$

DECLARE
	wip_status_id bigint;
    pass_status_id bigint;

BEGIN
	select id into wip_status_id from status where name = 'WIP';
    select id into pass_status_id from status where name = 'PASS';

    IF OLD.status_id = wip_status_id and OLD.finished = false and NEW.finished = true THEN
    	NEW.status_id = pass_status_id;
    END IF;
    RETURN NEW;   
END;

$BODY$;

CREATE TRIGGER finished_status_pass_after_update
    AFTER UPDATE 
    ON job
    FOR EACH ROW
    EXECUTE PROCEDURE finished_update_task_status_trigger();