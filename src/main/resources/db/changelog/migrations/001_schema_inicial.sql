--liquibase formatted sql

--changeset marcopollivier:1
CREATE TABLE IF NOT EXISTS product (
	id BIGINT NOT NULL CONSTRAINT product_pkey PRIMARY KEY ,
	created_at TIMESTAMP NOT NULL ,
	description VARCHAR(255),
	name VARCHAR(255),
	updated_at TIMESTAMP NOT NULL ,
	parent_product_id BIGINT CONSTRAINT product_parent_fkey references product
);

CREATE TABLE IF NOT EXISTS image (
	id BIGINT NOT NULL CONSTRAINT image_pkey PRIMARY KEY,
	created_at TIMESTAMP NOT NULL,
	type VARCHAR(255),
	updated_at TIMESTAMP NOT NULL,
	product_id BIGINT CONSTRAINT product_image_fkey REFERENCES product
);

--rollback ALTER TABLE image DROP CONSTRAINT product_image_fkey;
--rollback ALTER TABLE product DROP CONSTRAINT product_parent_fkey;
--rollback DROP TABLE image;
--rollback DROP TABLE product;