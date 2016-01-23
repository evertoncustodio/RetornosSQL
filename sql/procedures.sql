USE RETORNOS_SQL
GO

IF OBJECT_ID('SP_RETORNO_RESULTSET', 'P') IS NOT NULL
BEGIN
	DROP PROCEDURE SP_RETORNO_RESULTSET
END
GO

CREATE PROCEDURE SP_RETORNO_RESULTSET AS
BEGIN
	DECLARE @CLIENTES AS TABLE (
		NOME VARCHAR(100)
	)

	INSERT INTO @CLIENTES(NOME) VALUES
		('Cliente 1'),
		('Cliente 2')

	SELECT * FROM @CLIENTES
END
GO

IF OBJECT_ID('SP_RETORNO_OUTPUT', 'P') IS NOT NULL
BEGIN
	DROP PROCEDURE SP_RETORNO_OUTPUT
END
GO

CREATE PROCEDURE SP_RETORNO_OUTPUT (@NOME VARCHAR(100) OUTPUT) AS
BEGIN
	SET @NOME = 'Cliente 3'
END
GO

IF OBJECT_ID('SP_RETORNO_RETURN', 'P') IS NOT NULL
BEGIN
	DROP PROCEDURE SP_RETORNO_RETURN
END
GO

CREATE PROCEDURE SP_RETORNO_RETURN AS
BEGIN
	RETURN 80
END
GO

IF OBJECT_ID('SP_RETORNO_TODOS', 'P') IS NOT NULL
BEGIN
	DROP PROCEDURE SP_RETORNO_TODOS
END
GO

CREATE PROCEDURE SP_RETORNO_TODOS (@NOME VARCHAR(100) OUTPUT) AS
BEGIN
	SET @NOME = 'Cliente 4'

	DECLARE @CLIENTES AS TABLE (
		NOME VARCHAR(100)
	)

	INSERT INTO @CLIENTES(NOME) VALUES
		('Cliente 5'),
		('Cliente 6')

	SELECT * FROM @CLIENTES

	RETURN 80
END
GO

