package br.com.serasa.pi.enums;

public enum UFEnum {
	AM(1), PA(2);

	public long codigoUF;

	UFEnum(long codigoUF) {
		this.codigoUF = codigoUF;
	}

	public long getCodigoUF() {
		return this.codigoUF;
	}
}

