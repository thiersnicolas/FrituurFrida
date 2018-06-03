package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.vdab.entities.Saus;

public class SausRepository extends AbstractRepository{
	
	private final static String SELECT_ALL = "select sauzen.id as 'id', sauzen.naam as 'naam', ingredienten.naam "
			+ "from sauzen inner join sauzen_has_ingredienten on sauzen.id = sauzen_has_ingredienten.sauzen_id "
			+ "inner join ingredienten on sauzen_has_ingredienten.ingredienten_id = ingredienten.id";
	private final static String SELECT_ALL_INGREDIENTEN = "select naam from ingredienten";
	private final static String DELETE_SAUS = "delete from sauzen where sauzen.id=?";
	private final static String SELECT_SAUSNAMEN = "select naam from sauzen";

	
	
	private Map<Long, Saus> resultSetRijenNaarSauzen(ResultSet resultSet) throws SQLException {
		List<String> ingredienten = new ArrayList<>();
		Map<Long, Saus> sauzenMap = new HashMap<>();
		while (resultSet.next()) {
			Long id = resultSet.getLong(1);
			Saus saus = new Saus(id, resultSet.getString(2));
			String ingredient = resultSet.getString(3);
			
			if (!(sauzenMap.containsKey(id))){
				ingredienten.clear();
				sauzenMap.put(id, saus);
				ingredienten.add(ingredient);
			} else {
				ingredienten.add(ingredient);
				saus.setIngredienten(ingredienten);
				sauzenMap.replace(id, saus);
			}
		}
		return sauzenMap;
	}
	
	public List<Saus> findAll() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement()){
			Map<Long, Saus> sauzenMap = new HashMap<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try (ResultSet resultSet = statement.executeQuery(SELECT_ALL)){
				sauzenMap = resultSetRijenNaarSauzen(resultSet);
			}
			connection.commit();
			return new ArrayList<Saus>(sauzenMap.values());
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public List<String> findIngredients() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement()) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			List<String> ingredienten = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_INGREDIENTEN)){
				while (resultSet.next()) {
					ingredienten.add(resultSet.getString(1));
				}
			}
			connection.commit();
			return ingredienten;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public List<Saus> bevatIngredient(String ingredient) {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement()){
			Map<Long, Saus> sauzenMap = new HashMap<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try (ResultSet resultSet = statement.executeQuery(SELECT_ALL)){
				sauzenMap = resultSetRijenNaarSauzen(resultSet);
			}
			connection.commit();
			List<Saus> sauzen = new ArrayList<>(sauzenMap.values());
			List<Saus> sauzenMetIngredient = new ArrayList<>();
			for (Saus saus : sauzen) {
				if (saus.getIngredienten().indexOf(ingredient) != -1) {
					sauzenMetIngredient.add(saus);
				}
			}
			return sauzenMetIngredient;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public int deleteSauzen(String[] teVerwijderenIds) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SAUS)){
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			for (String idString:teVerwijderenIds) {
				statement.setLong(1, Long.parseLong(idString));
				statement.addBatch();
			}
			connection.commit();
			return Arrays.stream(statement.executeBatch()).sum();
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public List<String> sauzenNamen(){
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement()) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			List<String> sausNamen = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery(SELECT_SAUSNAMEN)){
				while (resultSet.next()) {
					sausNamen.add(resultSet.getString(1));
				}
			}
			connection.commit();
			return sausNamen;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}



}
