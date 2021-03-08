ALTER TABLE component ADD COLUMN preloaded BOOLEAN;
ALTER TABLE component ALTER COLUMN preloaded SET DEFAULT false;
ALTER TABLE component ALTER COLUMN "version" DROP NOT NULL;

ALTER TABLE "component" ADD COLUMN uuid uuid;
ALTER TABLE "component" ALTER COLUMN uuid SET NOT NULL;
ALTER TABLE "component" ALTER COLUMN uuid SET DEFAULT uuid_generate_v4();

INSERT INTO component (name,preloaded) VALUES ('Firefox',true);
INSERT INTO component (name,preloaded) VALUES ('JMeter',true);
INSERT INTO component (name,preloaded) VALUES ('Chrome',true);
INSERT INTO component (name,preloaded) VALUES ('IE',true);
INSERT INTO component (name,preloaded) VALUES ('npm',true);
INSERT INTO component (name,preloaded) VALUES ('Android SDK',true);
INSERT INTO component (name,preloaded) VALUES ('Appdium',true);
