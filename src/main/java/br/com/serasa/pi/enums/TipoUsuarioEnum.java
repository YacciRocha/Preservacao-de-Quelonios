package br.com.serasa.pi.enums;

public enum TipoUsuarioEnum {
	ADMIN(1), COORDENADOR(2), VOLUNTARIO(3);

	public long codigoTipoUsuario;

	TipoUsuarioEnum(long codigoTipoUsuario) {
		this.codigoTipoUsuario = codigoTipoUsuario;
	}

	public long getCodigoTipoUsuario() {
		return this.codigoTipoUsuario;
	}
}
