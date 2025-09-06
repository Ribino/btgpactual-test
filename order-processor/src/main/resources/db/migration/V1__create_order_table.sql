create table if not exists "order" (
	id BIGSERIAL primary key,
	code bigint unique not null,
	client_code bigint not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
 );