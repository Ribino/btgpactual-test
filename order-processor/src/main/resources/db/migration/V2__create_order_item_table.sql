create table if not exists order_item (
	id BIGSERIAL primary key,
	order_id bigint not null,
	item_name varchar(30),
	quantity int not null,
	price decimal not null,
	constraint uk_order_item unique (order_id, item_name),
	constraint fk_order_item_order foreign key (order_id) references "order" (id)
);