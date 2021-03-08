CREATE OR REPLACE FUNCTION "update_uuid_trigger" ()  RETURNS trigger
  VOLATILE
AS $dbvis$
BEGIN
    NEW.uuid = OLD.uuid;
    RETURN NEW;   
END;
$dbvis$ LANGUAGE plpgsql;


CREATE TRIGGER uuid_after_update AFTER UPDATE ON component FOR EACH ROW EXECUTE PROCEDURE update_uuid_trigger();
CREATE TRIGGER uuid_after_update AFTER UPDATE ON job FOR EACH ROW EXECUTE PROCEDURE update_uuid_trigger();
CREATE TRIGGER uuid_after_update AFTER UPDATE ON module FOR EACH ROW EXECUTE PROCEDURE update_uuid_trigger();
CREATE TRIGGER uuid_after_update AFTER UPDATE ON task FOR EACH ROW EXECUTE PROCEDURE update_uuid_trigger();
CREATE TRIGGER uuid_after_update AFTER UPDATE ON worker FOR EACH ROW EXECUTE PROCEDURE update_uuid_trigger();