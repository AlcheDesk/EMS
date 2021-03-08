CREATE TABLE executing_object
    (
        uuid uuid DEFAULT uuid_generate_v4() NOT NULL
    );
    
CREATE INDEX executing_object_uuid_index ON executing_object (uuid);