package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;

/*
 * @Path: Define o caminho para acessar. Ex.: "http://servidor/projetos"; 
 * @Produces: Define o resultado, no caso, um XML;
 * @Consumes: Define o que o m�todo consome;
 * @GET: Define que ser� acessado via get;
 * @POST: Envia dados para o servidor;
 * @PathParam: Define que o par�metro ser� passado por path. Ex.: "http://servidor/projetos/1";
 * @QueryParam: Diz que � um par�metro recebido por get. Ex.: "http://servidor/projetos?id=1";
 * */

@Path("projetos") // localhost/projetos
public class ProjetoResource
{
	@Path("{id}") // Parametro ID � passado pela URI 
	@GET // � acessado por get
	@Produces(MediaType.APPLICATION_XML) // Produz um XML
	public String busca(@PathParam("id") long id) // Recebe o ID como par�metro passado pela URL
	{
		// Instancia o objeto do projeto de ID recebido por par�metro 
		Projeto projeto = new ProjetoDAO().busca(id);
		
		// Retorna o XML
		return projeto.toXML();
		
	}
	
	/* M�todo que envia um XML de projeto para o servidor */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo)
	{
		// Cria um onjeto de projeto a partir do xml 
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		
		// Adiciona o projeto no array/banco
		new ProjetoDAO().adiciona(projeto);
		
		// Retorna mensagem de sucesso no xml 
		//return "<status>Success</status>";
		
		// Define a URI
		URI uri = URI.create("projetos/" + projeto.getId());
		
		// Retorna status code de created (sucesso)
		return Response.created(uri).build();
		
		
	}
}

