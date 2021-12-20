package br.com.brq.projetoecommerce.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imagem")
public class ImagemEntity implements Serializable {

	private static final long serialVersionUID = 6631136729674277993L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idImagem;
	private String nome;
	

}
