package stri.java_connect.bdd;

import java.io.File;
import java.sql.*;

/**
 * @author Thomas, Rémi
 *
 */
public class Sqlite
{
	private String nomBDD;
	private Connection connexion;
	private Statement statement;
	
	/**
	 * Creation d'un objet Sqlite
	 * 
	 * @param dbFileName le nom de la base de donnees (nom du fichier la reprensentant)
	 */
	public Sqlite(String dbFileName) throws ClassNotFoundException
	{
		nomBDD = dbFileName;
		Class.forName("org.sqlite.JDBC");
		dbConnect();
	}

	/**
	 * Connexion a la base de donnees
	 * 
	 */
	public void dbConnect()
	{
		connexion = null;
		try
		{
			connexion = DriverManager.getConnection("jdbc:sqlite:./" + nomBDD);
			statement = connexion.createStatement();
			statement.setQueryTimeout(20);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Fermeture de la base de donnees
	 * 
	 */
	public void close()
	{
		if(connexion != null)
		{
			try
			{
				connexion.close();
			}
			catch(SQLException e)
			{
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Executer une requete sur la base de donnees
	 * 
	 * @param sql la requete
	 * @return true si reussie, false si erreur
	 */
	public ResultSet executerRequete(String sql)
	{
		ResultSet rs = null;
		try
		{
			rs = statement.executeQuery(sql);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return rs;
	}

	/**
	 * Executer une mise a jour de la (structure de la) base de donnees
	 * 
	 * @param sql la requete
	 * @return true si reussie, false si erreur
	 */
	public boolean executerMaj(String sql)
	{
		boolean state = true;
		try
		{
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			state = false;
		}
		return state;
	}

	/**
	 * Reset de la base de donnees
	 * 
	 */
	public void reset()
	{
		close();
		File temp = new File("./" + nomBDD);
		temp.delete();
		dbConnect();
	}
}
