DROP TABLE "worker_type_osgi_bundle_link" CASCADE;
DROP TABLE "osgi_bundle" CASCADE;
ALTER TABLE "worker_module_link" RENAME COLUMN "worker_id" TO worker_type_id;
ALTER TABLE "worker_module_link" DROP CONSTRAINT "worker_module_fk_worker";
ALTER TABLE "worker_module_link" ADD CONSTRAINT worker_module_link_fk_worker_type FOREIGN KEY (worker_type_id) REFERENCES "worker_type" ("id");