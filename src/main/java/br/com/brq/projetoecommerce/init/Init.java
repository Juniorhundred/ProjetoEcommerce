package br.com.brq.projetoecommerce.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.brq.projetoecommerce.dto.CategoriaDTO;
import br.com.brq.projetoecommerce.dto.ImagemDTO;
import br.com.brq.projetoecommerce.dto.ItemVendaDTO;
import br.com.brq.projetoecommerce.dto.ProdutoDTO;
import br.com.brq.projetoecommerce.dto.VendaDTO;
import br.com.brq.projetoecommerce.services.CategoriaService;
import br.com.brq.projetoecommerce.services.ImagemService;
import br.com.brq.projetoecommerce.services.ItemVendaService;
import br.com.brq.projetoecommerce.services.ProdutoService;
import br.com.brq.projetoecommerce.services.VendaService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ImagemService imagemService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemVendaService itemVendaService;
	
	@Autowired
	private VendaService vendaService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

//		CategoriaDTO categoria = CategoriaDTO.builder().nomeCategoria("Eletronicos").build();
//		categoriaService.salvar(categoria.toEntity());
//		categoria.setIdCategoria(1);
//		
//		List<CategoriaDTO> listCategoria = new ArrayList<>();
//		listCategoria.add(categoria);
//		
//		ImagemDTO imagem = ImagemDTO.builder().imagemProduto("(*-*)").build();
//		imagemService.salvar(imagem.toEntity());
//		imagem.setIdImagem(1);
//		
//		List<ImagemDTO> listImagem = new ArrayList<>();
//		listImagem.add(imagem);
//		
//		ProdutoDTO produto = ProdutoDTO.builder().nome("Xiaomi").preco(2000).descricao("Celular").categorias(listCategoria).imagens(listImagem).build();
//		produtoService.salvar(produto.toEntity());
//		produto.setIdProduto(1);
//		
//		List<ProdutoDTO> listProduto = new ArrayList<>();
//		listProduto.add(produto);
//		
//		ItemVendaDTO itemVenda = ItemVendaDTO.builder().itemQuantidade(1).itemProduto(listProduto).build();
//		itemVendaService.salvar(itemVenda.toEntity());
//		itemVenda.setIdItemVenda(1);
//		
//		List<ItemVendaDTO> listItem = new ArrayList<>();
//		listItem.add(itemVenda);
//		
//		VendaDTO venda = VendaDTO.builder().dataVenda("24-12-2021").itemVenda(listItem).build();
//		vendaService.salvar(venda.toEntity());
		
		
		
	}	

}
