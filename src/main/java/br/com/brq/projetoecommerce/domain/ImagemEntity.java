package br.com.brq.projetoecommerce.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.brq.projetoecommerce.dto.ImagemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imagem")
public class ImagemEntity implements Serializable {

//	private static final long serialVersionUID = -7273751607202792337L;
//	@Id
//	@GeneratedValue (strategy = GenerationType.SEQUENCE, 
//	generator = "IMAGEM_GEN_SEQ")
//	@SequenceGenerator (sequenceName = "IMAGEM_SEQ", allocationSize = 1, 
//	name = "IMAGEM_GEN_SEQ")
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private Integer idImagem;
	private String imagemProduto;
	
	public ImagemDTO toDTO() {
		var mapper = new ModelMapper();
		return mapper.map(this, ImagemDTO.class);

	}
}
