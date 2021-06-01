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
	
	private ClientServiceImpl service;

	public ClientServlet() {
		this.service = new ClientServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String acao = request.getParameter("acao");
	
	switch (acao) {
	
	case "remover":
		String id = request.getParameter("id");
		try {
			service.deleteById(Long.parseLong(id));
			RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/clients/list_client.jsp");
			request.setAttribute("sucesso", "Usuario excluido com sucesso");
			request.setAttribute("clients", this.service.getAll());
			rd.forward(request, response);
		} catch (Exception e) {
			e.toString();
		}
		response.setStatus(HttpServletResponse.SC_OK);
		break;
		
	case "editar":
		String idEdit = request.getParameter("id");
		try {
			RequestDispatcher rdEdit = request.getRequestDispatcher("/admin/pages/clients/edit_client.jsp");
			request.setAttribute("client", this.service.getClientId(Long.parseLong(idEdit)));
			rdEdit.forward(request, response);
		}catch (Exception e) {
			e.toString();
		}
		break;
		
	case "listar":
		try {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/clients/list_client.jsp");
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
		
		
		String acao = request.getParameter("acao");
		
		if(acao  != null) {
			switch (acao) {
			
			case "salvarEdicao":
			String idS = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String senha = request.getParameter("senha");
			
			Client client = new Client();
			client.setId(Long.parseLong(idS));
			client.setName(name);
			client.setEmail(email);
			client.setPhone(phone);
			client.setSenha(senha);
			
			if (this.service.update(client)) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/clients/list_client.jsp");				
				request.setAttribute("sucesso", "Edição concluida com sucesso");
				request.setAttribute("clients", this.service.getAll());
				rd.forward(request, response);
				}
			break;
			
			case "salvarUsuario":
			try {
				
				String nameU  = request.getParameter("name"); 
				String emailU = request.getParameter("email");
				String phoneU = request.getParameter("phone");
				String senhaU = request.getParameter("senha");
				
				//MONTEI O MEU OBJETO CLIENT
				Client clientU = new Client();
				clientU.setName(nameU);
				clientU.setEmail(emailU);
				clientU.setPhone(phoneU);
				clientU.setSenha(senhaU);
				
				if(this.service.save(clientU)) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/pages/clients/list_client.jsp");
				request.setAttribute("sucesso", "Usuario salvo com sucesso");
				request.setAttribute("clients", this.service.getAll());
				rd.forward(request, response);
					}
				}catch (Exception e){
					System.out.println(e.toString());
				}
				break;
					
			}
		} else {
			try {
				// PEGA OS DADOS DO REQUEST
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String senha = request.getParameter("senha");
				

				Client client = new Client();
				client.setName(name);
				client.setEmail(email);
				client.setPhone(phone);
				client.setSenha(senha);

				// SALVAR O MEU CLIENT
					if (this.service.save(client)) {
			            response.setStatus(HttpServletResponse.SC_OK);
			            response.sendRedirect("index.jsp");
					} else {
						RequestDispatcher rd = request.getRequestDispatcher("admin/primeiroAcesso.jsp");				
						request.setAttribute("erro", "Email ou telefone ja cadastrados");
						rd.forward(request, response);
						response.sendRedirect("admin/primeiroAcesso.jsp");
					}
				} catch (Exception e){
					System.out.println(e.toString());
					response.sendRedirect("admin/primeiroAcesso.jsp");
				}
		}
		
		
		
	}
	
	

}
