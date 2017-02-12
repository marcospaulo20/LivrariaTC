package tc.livraria.model;

public class Produto {

	private Integer id;
	private String nome;
	private int ano;
	private boolean disponivel;

	public Produto() {
		this.disponivel = false;
	}

	private Produto(ProdutoBuilder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.ano = builder.ano;
		this.disponivel = builder.disponivel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}

	public boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.id);
		
		if(this.getNome() != null)
			stringBuilder.append(" - ").append(this.nome);
		return stringBuilder.toString();
	}
	
	public static class ProdutoBuilder {
		private Integer id;
		private String nome;
		private int ano;
		private boolean disponivel;

		public ProdutoBuilder id(Integer id) {
			this.id = id;
			return this;
		}
		
		public ProdutoBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public ProdutoBuilder ano(int ano) {
			this.ano = ano;
			return this;
		}
		
		public ProdutoBuilder disponivel(boolean disponivel) {
			this.disponivel = disponivel;
			return this;
		}
		
		public Produto builder() {
			return new Produto(this);
		}
	}
}
