CREATE OR REPLACE FUNCTION update_created_at_trigger()
    RETURNS trigger
    LANGUAGE 'plpgsql'
AS $$
BEGIN
    NEW.created_at = OLD.created_at;
    RETURN NEW;   
END;
$$;

CREATE TRIGGER created_at_before_update BEFORE UPDATE ON ems_log FOR EACH ROW EXECUTE PROCEDURE update_created_at_trigger()