package br.com.serasa.pi.serasa.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;

import br.com.serasa.pi.common.UsuarioVO;

public class MyPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<UsuarioVO> usuarios = (List<UsuarioVO>) model.get("usuarios");

		Table table = new Table(4);
		table.addHeaderCell("Nome");
		table.addHeaderCell("Matricula");
		table.addHeaderCell("Username");
		table.addHeaderCell("Tipo Usuário");
		for (UsuarioVO usuarioVO : usuarios) {			
			table.addCell(usuarioVO.getNome());
			table.addCell(usuarioVO.getMatricula());
			table.addCell(usuarioVO.getUsername());
			table.addCell(usuarioVO.getTipoUsuario() != null ? usuarioVO.getTipoUsuario().toString() : "Inexistente");
		}
		document.add(table);		
	}
}

