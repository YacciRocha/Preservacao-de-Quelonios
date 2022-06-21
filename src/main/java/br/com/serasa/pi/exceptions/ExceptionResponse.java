package br.com.serasa.pi.exceptions;

import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private OffsetDateTime timestamp;
	private String mensagem;
	private String descricao;
		
	
	public ExceptionResponse() {
	
}
}
