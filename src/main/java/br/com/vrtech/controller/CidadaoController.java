package br.com.vrtech.controller;

import java.sql.Connection;

import br.com.vrtech.dao.CidadaoDAO;
import br.com.vrtech.factory.ConnectionFactory;
import br.com.vrtech.model.Cidadao;

public class CidadaoController {
	
	private CidadaoDAO cidadaoDao;
	
	public CidadaoController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.cidadaoDao = new CidadaoDAO(connection);
	}
	
	public void gravar(Cidadao cidadao) {
		cidadaoDao.gravar(cidadao);
	}
	
}
