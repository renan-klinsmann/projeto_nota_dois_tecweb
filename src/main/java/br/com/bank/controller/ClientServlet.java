package br.com.bank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bank.model.Client;
import br.com.bank.service.ClientServiceImpl;

@WebServlet("/clientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Client client;
	private ClientServiceImpl service;

	public ClientServlet() {
		this.service = new ClientServiceImpl();
		this.client = new Client();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String acao = request.getParameter("acao");
	
	switch (acao) {
	
	case "remover":
		String id = request.getParameter("id");
		try {
			service.deleteById(Long.parseLong(id));
			RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/contatos/list_client.jsp");
			request.setAttribute("sucesso", "Cadastro excluido com sucesso");
			request.setAttribute("clients", this.service.getAll());
			rd.forward(request, response);
		} catch (Exception e) {
			e.toString();
		}
		response.setStatus(HttpServletResponse.SC_OK);
		break;
		
	case "editar":
		
	case "listar":
		try {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/contatos/list_client.jsp");
		request.setAttribute("clients", this.service.getAll());
		rd.forward(request, response);
			} catch (Exception e) {
			e.toString();
			}
		break;
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		// PEGA OS DADOS DO REQUEST
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String senha = request.getParameter("senha");
		
		 if (name == null || email == null || senha == null || phone == null) {
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
             return;
         }

		// MONTEI O MEU OBJETO CLIENT
		client.setName(name);
		client.setEmail(email);
		client.setPhone(phone);
		client.setSenha(senha);

		// SALVAR O MEU CLIENT
			if (this.service.save(client)) {
	            response.setStatus(HttpServletResponse.SC_OK);
	            response.sendRedirect("admin/login.jsp");
			} 
		} catch (Exception e){
			System.out.println(e.toString());
			response.sendRedirect("admin/primeiroAcesso.jsp");
		}
	}

}
