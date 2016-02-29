INSERT INTO "dbo"."seg_usuario" (usu_id,seg_email,seg_enable,seg_password) VALUES (1 ,'admin@admin.com',true,'21232f297a57a5a743894a0e4a801fc3');
INSERT INTO "dbo"."seg_pessoa" (pes_id,pes_cpf,pes_nome,pes_receber_noticias,pes_ultimas_atualizacoes,usu_id) VALUES (1 ,'68824017331','admin' ,false,false,1);
INSERT INTO "dbo"."seg_perfil" (per_id,seg_authority) VALUES (1 ,'ROLE_USER');
INSERT INTO "dbo"."seg_usuario_perfil" (per_id,usu_id) VALUES (1 ,1 );