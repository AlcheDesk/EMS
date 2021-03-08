ALTER TABLE operating_system ADD COLUMN preloaded BOOLEAN;
ALTER TABLE operating_system ALTER COLUMN preloaded SET DEFAULT false;

INSERT INTO operating_system (name,preloaded) VALUES ('*',true);
INSERT INTO operating_system (name,preloaded) VALUES ('Windows 7',true);
INSERT INTO operating_system (name,preloaded) VALUES ('Windows 8',true);
INSERT INTO operating_system (name,preloaded) VALUES ('Windows 8.1',true);
INSERT INTO operating_system (name,preloaded) VALUES ('Windows 10',true);
INSERT INTO operating_system (name,preloaded) VALUES ('Mac OS X',true);
INSERT INTO operating_system (name,preloaded) VALUES ('Linux',true);
