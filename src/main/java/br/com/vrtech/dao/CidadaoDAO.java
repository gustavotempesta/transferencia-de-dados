package br.com.vrtech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.vrtech.model.Cidadao;

public class CidadaoDAO {

	private Connection connection;

	public CidadaoDAO(Connection connection) {
		this.connection = connection;
	}

	public void gravar(Cidadao cidadao) {
		int cod = pegarCodigoLogradouro(cidadao.getMunicipio(), cidadao.getEstado());

		try {
			String query = "INSERT INTO PESSOAS (NOME, IDADE, COD_LOGRADOURO) VALUES (?,?,?)";
			try (PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, cidadao.getNome());
				pstm.setInt(2, cidadao.getIdade());
				pstm.setInt(3, cod);
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					if (rst.next()) {
						cidadao.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	private int pegarCodigoLogradouro(String municipio, String estado) {
		try {
			int cod = 0;
			String query = "SELECT COD FROM LOGRADOUROS WHERE MUNICIPIO = ? AND ESTADO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(query)) {
				pstm.setString(1, municipio);
				pstm.setString(2, estado);
				pstm.execute();

				try (ResultSet rstSelect = pstm.getResultSet()) {
					if (rstSelect.next()) {
						cod = rstSelect.getInt(1);
					} else {
						cod = gravaLogradouro(municipio, estado);
					}
				}
			}
			return cod;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	private int gravaLogradouro(String municipio, String estado) {
		try {
			int cod = 0;
			String query = "INSERT INTO LOGRADOUROS (MUNICIPIO, ESTADO) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, municipio);
				pstm.setString(2, estado);
				pstm.execute();

				try (ResultSet rstInsert = pstm.getGeneratedKeys()) {
					if (rstInsert.next()) {
						cod = rstInsert.getInt(1);
					}
				}
			}
			return cod;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public List<Cidadao> buscarSomenteOsDaRegiaoSudeste() {
		try {
			List<Cidadao> cidadaos = new ArrayList<>();
			String query = "SELECT P.ID, P.NOME, P.IDADE, L.MUNICIPIO, L.ESTADO "
					+ "FROM PESSOAS P "
					+ "INNER JOIN LOGRADOUROS L ON P.COD_LOGRADOURO = L.COD "
					+ "WHERE L.ESTADO IN ('MG', 'SP', 'RJ', 'ES') "
					+ "ORDER BY L.ESTADO";
			
			try(PreparedStatement pstm = connection.prepareStatement(query)){
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					while (rst.next()) {
						Cidadao cidadao = new Cidadao(rst.getInt(1), rst.getString(2), rst.getInt(3), rst.getString(4), rst.getString(5));
						cidadaos.add(cidadao);
					}
				}
			}
			return cidadaos;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
				
	}

	public List<Cidadao> buscarSomenteOsMaioresDe30() {

//		Select p.id, p.nome, p.idade, l.municipio, l.estado
//		from pessoas p
//		inner join logradouros l ON p.COD_LOGRADOURO = l.COD
//		where p.idade > 30;

		return null;
	}

}
