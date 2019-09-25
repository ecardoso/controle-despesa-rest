insert into categoria (id,descricao) values (1,'Apartamento');
insert into categoria (id,descricao) values (2,'Combustível');
insert into categoria (id,descricao) values (3,'Diversos');
insert into forma_pagamento (id,descricao) values (1,'à vista');
insert into forma_pagamento (id,descricao) values (2,'crédito (itaú)');
insert into forma_pagamento (id,descricao) values (3,'crédito (nubank)');
insert into usuario (id,email,nome,tipo_login)values (1,'everton.ap.cardoso@gmail.com','Everton Cardoso',2);
insert into melhor_data_compra(id,data_melhor_compra,data_pagamento,forma_pagamento_id,usuario_id) values (1,29,10,2,1);
insert into melhor_data_compra(id,data_melhor_compra,data_pagamento,forma_pagamento_id,usuario_id) values (2,11,11,3,1);