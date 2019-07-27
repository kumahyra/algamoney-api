CREATE TABLE public.categoria(
  codigo bigserial,
  nome CHARACTER VARYING (50) COLLATE pg_catalog."default" NOT NULL,
  CONSTRAINT categoria_pkey PRIMARY KEY (codigo)
);

INSERT INTO public.categoria(nome) VALUES ('Lazer');
INSERT INTO public.categoria(nome) VALUES ('Alimentação');
INSERT INTO public.categoria(nome) VALUES ('Supermercado');
INSERT INTO public.categoria(nome) VALUES ('Farmácia');
INSERT INTO public.categoria(nome) VALUES ('Outros');