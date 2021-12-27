package br.com.brq.projetoecommerce.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.brq.projetoecommerce.dto.CategoriaDTO;
import br.com.brq.projetoecommerce.dto.ImagemDTO;
import br.com.brq.projetoecommerce.dto.ItemVendaDTO;
import br.com.brq.projetoecommerce.dto.ProdutoDTO;
import br.com.brq.projetoecommerce.dto.VendaDTO;
import br.com.brq.projetoecommerce.services.CategoriaService;
import br.com.brq.projetoecommerce.services.ProdutoService;

public class MockUtil {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public CategoriaDTO categoriaMock() {
		
		CategoriaDTO categoriaMocka = CategoriaDTO.builder().nomeCategoria("Eletronicos").build();		
		return categoriaMocka;
	}
	
	public ImagemDTO imagemMock() {
		
		ImagemDTO imagemMock = ImagemDTO.builder().imagemProduto("(*-*)").build();
		
		return imagemMock;
	}
	
	public ProdutoDTO produtoMock(List<CategoriaDTO> cat, List<ImagemDTO> ima) {
		
		CategoriaDTO categoriaMock = this.categoriaMock();
		//categoriaService.salvar(categoriaMock.toEntity());
		List<CategoriaDTO> listCategoria = new ArrayList<>();
		listCategoria.add(categoriaMock);
		
		ImagemDTO imagemMock =  this.imagemMock();
		List<ImagemDTO> listImagem = new ArrayList<>();
		listImagem.add(imagemMock);
		
		ProdutoDTO produtoMock = ProdutoDTO.builder().nome("Xiaomi").preco(2000).descricao("Celular").categorias(cat).imagens(ima).build();
					
		return produtoMock;
	}
	
	public ItemVendaDTO itemMock(List<ProdutoDTO> prod) {
		
//		ProdutoDTO produtoMock = this.produtoMock();		
//		List<ProdutoDTO> listProduto = new ArrayList<>();
//		listProduto.add(produtoMock);
		
		ItemVendaDTO itemMock = ItemVendaDTO.builder().itemQuantidade(1).itemProduto(prod).build();
		
		return itemMock;
	}
	
	public VendaDTO vendaMock() {
		
		ItemVendaDTO itemMock = this.itemMock(null);
		List<ItemVendaDTO> listItem = new ArrayList<>();
		listItem.add(itemMock);
		
		VendaDTO vendaMock = VendaDTO.builder().dataVenda("24-12-2021").itens(listItem).build();
			
		return vendaMock;
	}
}
