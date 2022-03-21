package posjavajdbc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.UserposDAO;
import model.BeanUserTelefone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {
	
	@Test
	public void initBanco() {
		UserposDAO userposDAO = new UserposDAO();
		Userposjava user = new Userposjava();
		user.setNome("teste junit");
		user.setEmail("teste@teste.com.br");
		userposDAO.salvar(user);
	}
	
	@Test
	public void initListar() {
		UserposDAO dao = new UserposDAO();
		List<Userposjava> users = new ArrayList<Userposjava>();
		users = dao.listar();
		
		for(Userposjava u : users) {
			System.out.println(u);
		}
	}
	
	@Test
	public void initBuscar() {
		UserposDAO dao = new UserposDAO();
		Long id = 3L;
		Userposjava user = dao.buscar(id);
		System.out.println(user);
	}
	
	@Test
	public void initAtualizar() {
		UserposDAO dao = new UserposDAO();
		Userposjava user = dao.buscar(4L);
		user.setNome(user.getNome() +" - junit");
		user.setEmail("jnunit_user@a.com");
		boolean status = dao.atualizar(user);
		if (status) {
			System.out.println("atualizado com sucesso");
		} else {
			System.out.println("falha na atualização");
		}
	}
	
	@Test
	public void initExcluir() {
		UserposDAO dao = new UserposDAO();
		Userposjava user = dao.buscar(1);
		
		if (dao.jaRemovido(user.getId())) {
			System.out.println("Cadastro já foi excluído!");
		} else {
			if (dao.deletar(user.getId()) ) {
				System.out.println("Deletado com sucesso!");
			} else {
				System.out.println("Erro ao deletar!");
			}
		}
		
	}
	
	@Test
	public void initReverterExcluido() {
		UserposDAO dao = new UserposDAO();
		Userposjava user = new Userposjava();
		if (dao.jaRemovido(1L)){
			dao.reverterExcluido(1L);
			user = dao.buscar(1L);
		}
		System.out.println(user);
	}
	
	@Test
	public void initSalvarTelefone() {
		Telefone fone = new Telefone("(44) 99971-5166", "celular sei lá", 3L);
		UserposDAO dao = new UserposDAO();
		if (dao.salvarTelefone(fone)) {
			System.out.println("Telefone salvo!");
		} else {
			System.out.println("Erro ao salvar o telefone");
		}
	}
	
	@Test
	public void listaFonesUsers() {
		UserposDAO dao = new UserposDAO();
		List<BeanUserTelefone> listaUserTelefone = new ArrayList<BeanUserTelefone>();
		listaUserTelefone = dao.listaUserTelefone(2L);
		if (!listaUserTelefone.isEmpty()) {
			for(BeanUserTelefone but : listaUserTelefone) {
				System.out.println(but);
			}
		} else {
			System.out.println("Não há registos para listar!");
		}
	}
	
	@Test
	public void excluirUserETelefone() {
		UserposDAO dao = new UserposDAO();
		long id = 1L;
		if (dao.deletar(id) && (dao.deletarTelefoneUser(id))) {
			System.out.println("Usuario e fone excluidos com sucesso!");
		} else {
			System.err.println("Ocorreu algum erro ao tentar excluir usuario e telefone");
		}
	}
}
