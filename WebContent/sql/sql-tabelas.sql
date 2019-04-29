create table tb_login(
	id serial not null,
	login varchar(10) not null,
	senha varchar(6) not null,
	primary key(login)
);
create table tb_tipo_pagamento(
	id serial not null,
	nome varchar(30) not null,
	primary key (id)
);
create table tb_vendedor(
	id serial not null,
	nome varchar(30) not null,
	primary key(id)
);

create table tb_vendas(
	id serial not null,
	data varchar(15)not null,
	cliente varchar(40) not null,
	tipo_pagamento integer not null,
	vendedor integer not null,
	produto varchar(35) not null,
	valor double precision not null,
	quantidade integer not null,
	observacao varchar(50),
	primary key(id),
	FOREIGN KEY(tipo_pagamento) REFERENCES tb_tipo_pagamento(id) ON UPDATE CASCADE,
	FOREIGN KEY(vendedor) REFERENCES tb_vendedor(id) ON UPDATE CASCADE
);