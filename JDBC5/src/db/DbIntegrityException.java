package db;

public class DbIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DbIntegrityException(String msg) {
		super(msg);
	}
}

// Importante: Integridade referencial!
//A integridade referencial é como garantimos que todas essas informações se relacionem de maneira correta e consistente.
// Isso garante que um departamento, no caso, não seja excluído se estiver associado a um funcionário.
// Certifique-se de atualizar os funcionarios sempre que houver uma alteração nos departamentos.

