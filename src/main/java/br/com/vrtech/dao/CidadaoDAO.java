package br.com.vrtech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private int pegarCodigoLogradouro(String municipio, String estado) {
		try {
			int cod = 0;
			String querySelect = "SELECT COD FROM LOGRADOUROS WHERE MUNICIPIO = ? AND ESTADO = ?";

			try (PreparedStatement pstmSelect = connection.prepareStatement(querySelect)) {
				pstmSelect.setString(1, municipio);
				pstmSelect.setString(2, estado);
				pstmSelect.execute();

				try (ResultSet rstSelect = pstmSelect.getResultSet()) {
					if (rstSelect.next()) {
						cod = rstSelect.getInt(1);
					} else {
						cod = gravaLogradouro(municipio, estado);
					}
				}
			}
			return cod;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private int gravaLogradouro(String municipio, String estado) {
		try {
			int cod = 0;
			String queryInsert = "INSERT INTO LOGRADOUROS (MUNICIPIO, ESTADO) VALUES (?, ?)";
			
			try (PreparedStatement pstmInsert = connection.prepareStatement(queryInsert,
					Statement.RETURN_GENERATED_KEYS)) {
				pstmInsert.setString(1, municipio);
				pstmInsert.setString(2, estado);
				pstmInsert.execute();

				try (ResultSet rstInsert = pstmInsert.getGeneratedKeys()) {
					if (rstInsert.next()) {
						cod = rstInsert.getInt(1);
					}
				}
			}
			return cod;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cidadao> buscarSomenteOsDaRegiaoSudeste() {
		return null;
	}

	public List<Cidadao> buscarSomenteOsMaioresDe30() {
		return null;
	}

}
