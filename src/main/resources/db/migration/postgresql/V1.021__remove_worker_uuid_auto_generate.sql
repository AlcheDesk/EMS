ALTER TABLE "worker" ALTER COLUMN "uuid" DROP DEFAULT;
ALTER TABLE "worker" ADD CONSTRAINT worker_uk_uuid UNIQUE ("uuid");