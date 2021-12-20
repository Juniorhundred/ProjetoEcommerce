package br.com.brq.projetoecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brq.projetoecommerce.services.CategoriaService;

@Service
public class CategoriaController  {

	@Autowired
	private CategoriaService categoriaService;
	
	void test() {
		return categoriaService.listaTodasCategorias();
		return categoriaService.categoriaRepository.findAll();
	}
}
