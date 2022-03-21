package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserTelefone;
import model.Telefone;
import model.Userposjava;

public class UserposDAO {
	
	@SuppressWarnings("unused")
	private Connection connection;
	
	public UserposDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean salvar(Userposjava user) {
		boolean salvo = false;
		String sql = "insert into userposjava (nome, email) values (?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getEmail());
			
			ps.execute();
			connection.commit();  //salva no banco
			salvo = true;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return salvo;
	}
	
	public boolean salvarTelefone(Telefone fone) {
		boolean salvo = false;
		String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, fone.getNumero());
			ps.setString(2, fone.getTipo());
			ps.setLong(3, fone.getUsuario());
			ps.execute();
			connection.commit();
			salvo = true;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return salvo;
	}
	
	public List<BeanUserTelefone> listaUserTelefone(Long idUser){
		List<BeanUserTelefone> userFones = new ArrayList<BeanUserTelefone>();
		String sql = "select t.id, t.numero, t.tipo, u.id, u.nome, u.email, u.excluido "
				+ "from telefoneuser t "
				+ "inner join userposjava u on u.id = t.usuariopessoa "
				+ "where u.id = ?"
				+ "and u.excluido is null";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, idUser);
			rs = ps.executeQuery();
			while(rs.next()) {
				BeanUserTelefone ut = new BeanUserTelefone();
				ut.setIdFone(rs.getLong(1));
				ut.setNumero(rs.getString(2));
				ut.setTipo(rs.getString(3));
				ut.setIdUser(rs.getLong(4));
				ut.setNome(rs.getString(5));
				ut.setEmail(rs.getString(6));
				ut.setExcluido(rs.getString(7));
				userFones.add(ut);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return userFones;
	}
	
	public List<Userposjava> listar(){
		List<Userposjava> list = new ArrayList<>();
		String sql = "select id, nome, email from userposjava where excluido is null";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				//setando objeto com a coluna index
				Userposjava user = new Userposjava(rs.getLong(1), rs.getString(2), rs.getString(3));
				list.add(user);
			}
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return list;
	}
	
	//só uma sobreposição caso seja fornecido como inteiro.
	public Userposjava buscar(Integer id) {
		return buscar(id.longValue());
	}
	
	/**
	 * 
	 * @param id
	 * @return somente usuários ativos. Os inativos devem ser ativados para aparecerem aqui.
	 */
	public Userposjava buscar(Long id) {
		Userposjava user = new Userposjava();
		String sql = "select id, nome, email from userposjava where id = ? and excluido is null";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				user.setId(rs.getLong(1));
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if (ps != null) { try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
		return user;
	}
	
	public boolean atualizar(Userposjava user) {
		Boolean atualizou = false;
		String sql = "update userposjava set nome=?, email=? where id=?";
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getEmail());
			ps.setLong(3, user.getId());
			ps.execute();
			connection.commit();
			atualizou = true;			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return atualizou;
	}
	
	public boolean deletar(Long id) {
		boolean removido = false;
		PreparedStatement ps = null;
		String sql = "update userposjava set excluido = '*' where id = ?";	//não farei remoção em tabelas
		
		if (!jaRemovido(id)) {
			try {
				ps = connection.prepareStatement(sql);
				ps.setLong(1, id);
				ps.execute();
				connection.commit();
				removido = true;
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return removido;
	}
	
	//apenas exclusão lógica
	public boolean deletarTelefoneUser(Long idUser) {
		boolean removido = false;
		PreparedStatement ps = null;
		String sql = "update telefoneuser set excluido = '*' where usuariopessoa = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, idUser);
			ps.execute();
			connection.commit();
			removido = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return removido;
	}
	
	public boolean jaRemovido(Long id) {
		boolean removido = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id, excluido from userposjava where id = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String excluido = rs.getString("excluido");
				if (excluido != null) {
					removido = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return removido;
	}
	
	//TODO: Tem que fazer um para nr de telefone
	public boolean reverterExcluido(Long id) {
		boolean ativado = false;
		PreparedStatement ps = null;
		String sql = "update userposjava set excluido = null where id = ?";
		
		if (jaRemovido(id)) {
			try {
				ps = connection.prepareStatement(sql);
				ps.setLong(1, id);
				ps.execute();
				connection.commit();
				ativado = true;
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return ativado;
	}
}
