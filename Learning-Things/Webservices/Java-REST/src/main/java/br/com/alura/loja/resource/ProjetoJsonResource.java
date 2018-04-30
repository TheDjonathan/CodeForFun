package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetosJson") // localhost/projetos
public class ProjetoJsonResource
{
	@Path("{id}") // Parametro ID � passado pela URI
	@GET // � acessado por get
	@Produces(MediaType.APPLICATION_JSON) // Produz um JSON
	public String busca(@PathParam("id") long id) // Recebe o ID como par�metro passado pela URL
	{
		// Instancia o objeto do projeto de ID recebido por par�metro
		Projeto projeto = new ProjetoDAO().busca(id);

		// Retorna o Json
		return projeto.toJson();
	}
}
