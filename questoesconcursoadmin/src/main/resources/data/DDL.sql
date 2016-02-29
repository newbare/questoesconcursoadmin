--<ScriptOptions statementTerminator=";"/>

CREATE TABLE dbo.que_questao_comentario (
		com_id INT4 NOT NULL,
		que_id INT4 NOT NULL
	);

CREATE TABLE dbo.que_cargo_concurso_area (
		cca_id INT4 NOT NULL,
		are_id INT4 NOT NULL,
		car_id INT4 NOT NULL,
		con_id INT8 NOT NULL,
		esp_id INT4,
		ram_id INT4
	);

CREATE TABLE dbo.que_area (
		are_id INT4 NOT NULL,
		are_area VARCHAR(255)
	);

CREATE TABLE dbo.que_disciplina (
		dis_id INT4 NOT NULL,
		dis_nome VARCHAR(255)
	);

CREATE TABLE dbo.que_especialidade (
		esp_id INT4 NOT NULL,
		esp_descricao VARCHAR(255)
	);

CREATE TABLE dbo.que_orgao_instituicao (
		org_id INT8 NOT NULL,
		org_orgao VARCHAR(255),
		org_orgao_abreviacao VARCHAR(255)
	);

CREATE TABLE dbo.seg_usuario (
		usu_id INT4 NOT NULL,
		seg_email VARCHAR(255),
		seg_enable BOOL,
		seg_password VARCHAR(255)
	);

CREATE TABLE dbo.seg_pessoa (
		pes_id INT4 NOT NULL,
		pes_cpf VARCHAR(255),
		pes_nome VARCHAR(200) NOT NULL,
		pes_receber_noticias BOOL,
		pes_ultimas_atualizacoes BOOL,
		usu_id INT4
	);

CREATE TABLE dbo.seg_usuario_perfil (
		per_id INT4 NOT NULL,
		usu_id INT4 NOT NULL
	);

CREATE TABLE dbo.que_gabarito (
		alt_id INT8 NOT NULL,
		que_id INT8 NOT NULL
	);

CREATE TABLE dbo.que_questao_prova (
		pro_id INT4 NOT NULL,
		que_id INT8 NOT NULL
	);

CREATE TABLE dbo.que_forma_inclusao (
		for_id INT8 NOT NULL,
		for_nome VARCHAR(255)
	);

CREATE TABLE dbo.que_texto_descritivo (
		tex_id INT4 NOT NULL,
		tex_referencia VARCHAR(255),
		tex_texto VARCHAR(255)
	);

CREATE TABLE dbo.que_prova (
		pro_id INT4 NOT NULL,
		cca_id INT4,
		pro_concluido BOOL,
		pro_data TIMESTAMP
	);

CREATE TABLE dbo.que_alternativa (
		alt_id INT8 NOT NULL,
		alt_alternativa VARCHAR(255),
		que_id INT8
	);

CREATE TABLE dbo.seg_perfil (
		per_id INT4 NOT NULL,
		seg_authority VARCHAR(255)
	);

CREATE TABLE dbo.que_ramo (
		ram_id INT4 NOT NULL,
		ram_ramo VARCHAR(255),
		esp_id INT4
	);

CREATE TABLE dbo.que_concurso (
		con_id INT8 NOT NULL,
		con_ano VARCHAR(255),
		con_concluido BOOL,
		ban_id INT4,
		org_id INT8
	);

CREATE TABLE dbo.que_comentario (
		com_id INT4 NOT NULL,
		com_comentario VARCHAR(255),
		com_data TIMESTAMP,
		usu_id INT4
	);

CREATE TABLE dbo.que_tipo_questao (
		tip_id INT8 NOT NULL,
		tip_descricao VARCHAR(255)
	);

CREATE TABLE dbo.que_questao (
		que_id INT8 NOT NULL,
		que_data_alteracao TIMESTAMP,
		que_data_cadastro TIMESTAMP,
		for_id INT8,
		que_numero INT4,
		que_questao VARCHAR(255),
		tip_id INT8,
		cat_id INT4,
		tex_id INT4
	);

CREATE TABLE dbo.que_categoria (
		cat_id INT4 NOT NULL,
		cat_nome VARCHAR(255),
		dis_id INT4
	);

CREATE TABLE dbo.que_banca (
		ban_id INT4 NOT NULL,
		ban_abreviacao VARCHAR(255),
		ban_nome VARCHAR(255)
	);

CREATE TABLE dbo.que_cargo (
		car_id INT4 NOT NULL,
		car_nome VARCHAR(255)
	);

CREATE UNIQUE INDEX dbo.que_especialidade_pkey ON dbo.que_especialidade (esp_id ASC);

CREATE UNIQUE INDEX dbo.que_questao_prova_pkey ON dbo.que_questao_prova (pro_id ASC, que_id ASC);

CREATE UNIQUE INDEX dbo.que_concurso_pkey ON dbo.que_concurso (con_id ASC);

CREATE UNIQUE INDEX dbo.que_cargo_concurso_area_pkey ON dbo.que_cargo_concurso_area (cca_id ASC, are_id ASC, car_id ASC, con_id ASC);

CREATE UNIQUE INDEX dbo.que_forma_inclusao_pkey ON dbo.que_forma_inclusao (for_id ASC);

CREATE UNIQUE INDEX dbo.que_alternativa_pkey ON dbo.que_alternativa (alt_id ASC);

CREATE UNIQUE INDEX dbo.uk_tgwv2k2kpx6elcckbok4g6ntv ON dbo.que_cargo_concurso_area (cca_id ASC);

CREATE UNIQUE INDEX dbo.seg_usuario_pkey ON dbo.seg_usuario (usu_id ASC);

CREATE UNIQUE INDEX dbo.que_prova_pkey ON dbo.que_prova (pro_id ASC);

CREATE UNIQUE INDEX dbo.que_orgao_instituicao_pkey ON dbo.que_orgao_instituicao (org_id ASC);

CREATE UNIQUE INDEX dbo.que_categoria_pkey ON dbo.que_categoria (cat_id ASC);

CREATE UNIQUE INDEX dbo.que_gabarito_pkey ON dbo.que_gabarito (alt_id ASC, que_id ASC);

CREATE UNIQUE INDEX dbo.que_tipo_questao_pkey ON dbo.que_tipo_questao (tip_id ASC);

CREATE UNIQUE INDEX dbo.seg_usuario_perfil_pkey ON dbo.seg_usuario_perfil (per_id ASC, usu_id ASC);

CREATE UNIQUE INDEX dbo.seg_pessoa_pkey ON dbo.seg_pessoa (pes_id ASC);

CREATE UNIQUE INDEX dbo.uk_i1lwknn3pk8v6nqoqqcomu5cp ON dbo.seg_pessoa (pes_cpf ASC);

CREATE UNIQUE INDEX dbo.que_cargo_pkey ON dbo.que_cargo (car_id ASC);

CREATE UNIQUE INDEX dbo.que_disciplina_pkey ON dbo.que_disciplina (dis_id ASC);

CREATE UNIQUE INDEX dbo.que_comentario_pkey ON dbo.que_comentario (com_id ASC);

CREATE UNIQUE INDEX dbo.que_ramo_pkey ON dbo.que_ramo (ram_id ASC);

CREATE UNIQUE INDEX dbo.que_banca_pkey ON dbo.que_banca (ban_id ASC);

CREATE UNIQUE INDEX dbo.que_texto_descritivo_pkey ON dbo.que_texto_descritivo (tex_id ASC);

CREATE UNIQUE INDEX dbo.que_questao_comentario_pkey ON dbo.que_questao_comentario (com_id ASC, que_id ASC);

CREATE UNIQUE INDEX dbo.que_area_pkey ON dbo.que_area (are_id ASC);

CREATE UNIQUE INDEX dbo.que_questao_pkey ON dbo.que_questao (que_id ASC);

CREATE UNIQUE INDEX dbo.seg_perfil_pkey ON dbo.seg_perfil (per_id ASC);

ALTER TABLE dbo.que_forma_inclusao ADD CONSTRAINT que_forma_inclusao_pkey PRIMARY KEY (for_id);

ALTER TABLE dbo.que_cargo ADD CONSTRAINT que_cargo_pkey PRIMARY KEY (car_id);

ALTER TABLE dbo.que_tipo_questao ADD CONSTRAINT que_tipo_questao_pkey PRIMARY KEY (tip_id);

ALTER TABLE dbo.que_concurso ADD CONSTRAINT que_concurso_pkey PRIMARY KEY (con_id);

ALTER TABLE dbo.que_comentario ADD CONSTRAINT que_comentario_pkey PRIMARY KEY (com_id);

ALTER TABLE dbo.que_disciplina ADD CONSTRAINT que_disciplina_pkey PRIMARY KEY (dis_id);

ALTER TABLE dbo.que_especialidade ADD CONSTRAINT que_especialidade_pkey PRIMARY KEY (esp_id);

ALTER TABLE dbo.que_cargo_concurso_area ADD CONSTRAINT que_cargo_concurso_area_pkey PRIMARY KEY (cca_id, are_id, car_id, con_id);

ALTER TABLE dbo.que_banca ADD CONSTRAINT que_banca_pkey PRIMARY KEY (ban_id);

ALTER TABLE dbo.que_orgao_instituicao ADD CONSTRAINT que_orgao_instituicao_pkey PRIMARY KEY (org_id);

ALTER TABLE dbo.que_area ADD CONSTRAINT que_area_pkey PRIMARY KEY (are_id);

ALTER TABLE dbo.seg_perfil ADD CONSTRAINT seg_perfil_pkey PRIMARY KEY (per_id);

ALTER TABLE dbo.que_questao_prova ADD CONSTRAINT que_questao_prova_pkey PRIMARY KEY (pro_id, que_id);

ALTER TABLE dbo.que_gabarito ADD CONSTRAINT que_gabarito_pkey PRIMARY KEY (alt_id, que_id);

ALTER TABLE dbo.que_ramo ADD CONSTRAINT que_ramo_pkey PRIMARY KEY (ram_id);

ALTER TABLE dbo.que_prova ADD CONSTRAINT que_prova_pkey PRIMARY KEY (pro_id);

ALTER TABLE dbo.que_questao ADD CONSTRAINT que_questao_pkey PRIMARY KEY (que_id);

ALTER TABLE dbo.que_alternativa ADD CONSTRAINT que_alternativa_pkey PRIMARY KEY (alt_id);

ALTER TABLE dbo.que_texto_descritivo ADD CONSTRAINT que_texto_descritivo_pkey PRIMARY KEY (tex_id);

ALTER TABLE dbo.que_categoria ADD CONSTRAINT que_categoria_pkey PRIMARY KEY (cat_id);

ALTER TABLE dbo.seg_pessoa ADD CONSTRAINT seg_pessoa_pkey PRIMARY KEY (pes_id);

ALTER TABLE dbo.que_questao_comentario ADD CONSTRAINT que_questao_comentario_pkey PRIMARY KEY (com_id, que_id);

ALTER TABLE dbo.seg_usuario ADD CONSTRAINT seg_usuario_pkey PRIMARY KEY (usu_id);

ALTER TABLE dbo.seg_usuario_perfil ADD CONSTRAINT seg_usuario_perfil_pkey PRIMARY KEY (per_id, usu_id);

ALTER TABLE dbo.que_alternativa ADD CONSTRAINT fk_ikyj9d040xqfehvq2f4moj1du FOREIGN KEY (que_id)
	REFERENCES dbo.que_questao (que_id);

ALTER TABLE dbo.que_concurso ADD CONSTRAINT fk_7ib2d2r2yggsi7ekswjux8dua FOREIGN KEY (ban_id)
	REFERENCES dbo.que_banca (ban_id);

ALTER TABLE dbo.que_questao ADD CONSTRAINT fk_6goj1s0wvj1socs1cltl4gv0i FOREIGN KEY (cat_id)
	REFERENCES dbo.que_categoria (cat_id);

ALTER TABLE dbo.que_comentario ADD CONSTRAINT fk_qdjgqafec3ymcn8i36cxwlsot FOREIGN KEY (usu_id)
	REFERENCES dbo.seg_usuario (usu_id);

ALTER TABLE dbo.que_cargo_concurso_area ADD CONSTRAINT fk_8vfwr87vq7wtm4barnd5belib FOREIGN KEY (ram_id)
	REFERENCES dbo.que_ramo (ram_id);

ALTER TABLE dbo.que_ramo ADD CONSTRAINT fk_h7rh9jpa5nm1eddhiovxfas34 FOREIGN KEY (esp_id)
	REFERENCES dbo.que_especialidade (esp_id);

ALTER TABLE dbo.seg_pessoa ADD CONSTRAINT fk_tfng93btgw47qulyhqsil25jo FOREIGN KEY (usu_id)
	REFERENCES dbo.seg_usuario (usu_id);

ALTER TABLE dbo.que_concurso ADD CONSTRAINT fk_hdrnijbgjyg9uq34aqi3jhtpt FOREIGN KEY (org_id)
	REFERENCES dbo.que_orgao_instituicao (org_id);

ALTER TABLE dbo.que_questao_prova ADD CONSTRAINT fk_3v41t1pw7tj6e91ng2txrg7ym FOREIGN KEY (que_id)
	REFERENCES dbo.que_questao (que_id);

ALTER TABLE dbo.que_questao ADD CONSTRAINT fk_7tnh2xph598l90poyyv23yq4s FOREIGN KEY (tex_id)
	REFERENCES dbo.que_texto_descritivo (tex_id);

ALTER TABLE dbo.que_cargo_concurso_area ADD CONSTRAINT fk_745o77b4bne8qyo48y5tn22ln FOREIGN KEY (esp_id)
	REFERENCES dbo.que_especialidade (esp_id);

ALTER TABLE dbo.que_categoria ADD CONSTRAINT fk_23nx2d8say37jjpwdmqnl7fqc FOREIGN KEY (dis_id)
	REFERENCES dbo.que_disciplina (dis_id);

