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

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

/*
 * @Path: Define o caminho para acessar. Ex.: "http://servidor/carrinhos"; 
 * @Produces: Define o resultado, no caso, um XML;
 * @Consumes: Define o tipo de dado consumido pelo m�todo;
 * @GET: Define que ser� acessado via get;
 * @POST: Envia dados para o servidor;
 * @PathParam: Define que o par�metro ser� passado por path. Ex.: "http://servidor/carrinhos/1";
 * @QueryParam: Diz que � um par�metro recebido por get. Ex.: "http://servidor/carrinhos?id=1";
 * */

@Path("carrinhos")
public class CarrinhoResource
{
	/* M�todo que retorna o XML do carrinho informado por par�metro */
	@Path("{id}") // O par�metro � o id
	@GET // Acessado via GET
	@Produces(MediaType.APPLICATION_XML) // Resulta num XML
	public String busca(@PathParam("id") long id) // Recebe o par�metro id do carrinho pela url
	{
		// Instancia o carrinho 
		Carrinho carrinho = new CarrinhoDAO().busca(id);

		// Retorna o XML
		return carrinho.toXML();
	}
	
	
	/* M�todo que envia um XML com um carrinho para o servidor */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo)
	{
		// Transforma o XML de entrada num objeto de carrinho
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		
		// Adiciona o objeto de carrinho ao array/banco
		new CarrinhoDAO().adiciona(carrinho);
		
		// Retorna uma mensagem de sucesso no xml
		//return "<status>sucesso</status>";
		
		// Define a uri
		URI uri = URI.create("carrinhos/" + carrinho.getId());
		
		// Retorna o status code do POST
		return Response.created(uri).build();
	}
	
	

}
