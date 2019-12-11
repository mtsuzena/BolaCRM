package back.bolaCRM.common.exception;

public enum BusinessExceptionCode {
	
	ERR000("Erro não esperado"),
	ERR001("Usuário não encontrado"),
	ERR002("Login ou senha inválidos"),
	ERR003("Email inválido"),
	ERR004("Email já cadastrado"),
	ERR005("CPF inválido"),
	ERR511("Os campos obrigatórios estão nulos"),
	ERR300("Erro não esperado"),
	ERR301("Usuário não encontrado");
	

	private final String message;

	private BusinessExceptionCode(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}