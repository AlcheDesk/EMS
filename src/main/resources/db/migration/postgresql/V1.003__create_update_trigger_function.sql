-- FUNCTION: public.update_created_at_updated_at_column()

-- DROP FUNCTION public.update_created_at_updated_at_column();

CREATE OR REPLACE FUNCTION update_created_at_updated_at_trigger()
    RETURNS trigger
    LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
    NEW.updated_at = now();
    NEW.created_at = OLD.created_at;
    RETURN NEW;   
END;

$BODY$;



CREATE TRIGGER created_at_updated_at_before_update
    BEFORE UPDATE 
    ON job
    FOR EACH ROW
    EXECUTE PROCEDURE update_created_at_updated_at_trigger();
    
    
CREATE TRIGGER created_at_updated_at_before_update
    BEFORE UPDATE 
    ON module
    FOR EACH ROW
    EXECUTE PROCEDURE update_created_at_updated_at_trigger();
    
CREATE TRIGGER created_at_updated_at_before_update
    BEFORE UPDATE 
    ON task
    FOR EACH ROW
    EXECUTE PROCEDURE update_created_at_updated_at_trigger();
    
    
CREATE TRIGGER created_at_updated_at_before_update
    BEFORE UPDATE 
    ON worker
    FOR EACH ROW
    EXECUTE PROCEDURE update_created_at_updated_at_trigger();

