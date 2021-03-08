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

CREATE TRIGGER insert_set_retry BEFORE INSERT ON task FOR EACH ROW EXECUTE PROCEDURE insert_task_retry_trigger();