ALTER TABLE "worker" ADD COLUMN token uuid;
ALTER TABLE "worker" RENAME COLUMN "valid" TO manageable;