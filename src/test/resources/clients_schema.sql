CREATE SCHEMA IF NOT EXISTS clients AUTHORIZATION belovs;

CREATE TABLE IF NOT exists clients.cards (
	id uuid NOT NULL DEFAULT gen_random_uuid(),
	client_id uuid NOT NULL,
	sale_value numeric(3) NOT NULL,
	card_number varchar NOT NULL,
	ins_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	ins_user uuid NOT NULL,
	upd_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	upd_user uuid NOT NULL,
	CONSTRAINT cards_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT exists clients.client2addres (
	id uuid NOT NULL DEFAULT gen_random_uuid(),
	client_id uuid NOT NULL,
	addres varchar(4000) NOT NULL,
	ins_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	ins_user uuid NOT NULL,
	upd_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	upd_user uuid NOT NULL,
	CONSTRAINT client2addres_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT exists clients.h_client_promocodes (
	id uuid NOT NULL DEFAULT gen_random_uuid(),
	client_id uuid NOT NULL,
	promocode_id uuid NOT NULL,
	activate_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT h_client_promocodes_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT exists clients.clients (
	id uuid NOT NULL DEFAULT gen_random_uuid(),
	full_name varchar(200) NOT NULL,
	phone_number varchar(16) NOT NULL,
	email varchar(100) NULL,
	birthday timestamp(6) NULL,
	ins_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	ins_user uuid NOT NULL,
	upd_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	upd_user uuid NOT NULL,
	"owner" uuid NOT NULL,
	CONSTRAINT clients_pkey PRIMARY KEY (id)
);
CREATE UNIQUE INDEX "ui_clients$owner_phone" ON clients.clients USING btree (owner, phone_number);


-- clients.clients внешние включи

ALTER TABLE clients.clients ADD CONSTRAINT "fk_clients$ins_user" FOREIGN KEY (ins_user) REFERENCES users.users(id);
ALTER TABLE clients.clients ADD CONSTRAINT "fk_clients$upd_user" FOREIGN KEY (upd_user) REFERENCES users.users(id);
ALTER TABLE clients.clients ADD CONSTRAINT "fk_products$upd_user" FOREIGN KEY (upd_user) REFERENCES users.users(id);