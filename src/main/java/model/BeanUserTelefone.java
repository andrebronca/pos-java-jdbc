package model;

public class BeanUserTelefone {
	private Long idFone;
	private String numero;
	private String tipo;
	private Long idUser;
	private String nome;
	private String email;
	private String excluido;
	
	public BeanUserTelefone() {
	}

	public BeanUserTelefone(Long idFone, String numero, String tipo, Long idUser, String nome, String email,
			String excluido) {
		this.idFone = idFone;
		this.numero = numero;
		this.tipo = tipo;
		this.idUser = idUser;
		this.nome = nome;
		this.email = email;
		this.excluido = excluido;
	}

	public Long getIdFone() {
		return idFone;
	}

	public void setIdFone(Long idFone) {
		this.idFone = idFone;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExcluido() {
		return excluido;
	}

	public void setExcluido(String excluido) {
		this.excluido = excluido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((excluido == null) ? 0 : excluido.hashCode());
		result = prime * result + ((idFone == null) ? 0 : idFone.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanUserTelefone other = (BeanUserTelefone) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (excluido == null) {
			if (other.excluido != null)
				return false;
		} else if (!excluido.equals(other.excluido))
			return false;
		if (idFone == null) {
			if (other.idFone != null)
				return false;
		} else if (!idFone.equals(other.idFone))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BeanUserTelefone [idFone=" + idFone + ", numero=" + numero + ", tipo=" + tipo + ", idUser=" + idUser
				+ ", nome=" + nome + ", email=" + email + ", excluido=" + excluido + "]";
	}
	
	
}
