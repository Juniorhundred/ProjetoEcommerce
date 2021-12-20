package br.com.brq.projetoecommerce.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "produto")
public class ProdutoEntity implements Serializable {
	
	private static final long serialVersionUID = 1581475916977624272L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduto; 
	private String nome; 
	private double preco; 
	private String descricao; 
		
	@ManyToMany
	@JoinTable(name= "REL_PRODUTO_CATEGORIA", 
		joinColumns = {@JoinColumn(name = "idProduto")},
		inverseJoinColumns = {@JoinColumn(name = "idCategoria")})
	private List<CategoriaEntity> categorias;
	
	@OneToMany
	@JoinColumn(name="idImagem")
	private List <ImagemEntity> imagens;

	
}
	