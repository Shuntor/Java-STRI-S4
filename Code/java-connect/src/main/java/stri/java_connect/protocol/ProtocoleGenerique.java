/**
 * 
 */
package stri.java_connect.protocol;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author emeric
 *
 */
public abstract class ProtocoleGenerique
{
	protected final static String code = "{\"code\" : ";
	protected final static String data =  ", \"data\" : \"";
	protected final static String fin = "\"}";

	//-------------------------------------------------------------------------
	// createurs de reponses

	/**
	 * Creation de la reponse pour une erreur d'implementation manquante
	 * 
	 * @return la reponse
	 */
	public static String erreurImplementionManquante()
	{
		return code + -2 + data + "implementation manquante" + fin;
	}
	
	/**
	 * Creation de la reponse pour une erreur serveur
	 * 
	 * @return la reponse
	 */
	public static String erreurServeur()
	{
		return code + -1 + data + "erreur du serveur" + fin;
	}
	

	/**
	 * Creation de la reponse ok de confirmation (sans donnees)
	 * 
	 * @return la reponse
	 */
	public static String ok()
	{
		return code + 0 + data + fin;
	}
	

	/**
	 * Creation de la reponse ok de confirmation (avec donnees)
	 * 
	 * @param donnees les donnees a envoyer dans la reponse
	 * @return la reponse
	 */
	public static String ok(String donnees)
	{
		return code + 0 + ", \"data\" : " + donnees + " }";
	}
	

	/**
	 * Creation de la reponse pour une erreur de requete
	 * 
	 * @return la reponse
	 */
	public static String erreurRequete()
	{
		return code + 1 + data + "mauvaise requete" + fin;
	}
	

	/**
	 * Creation de la reponse pour une erreur de droits
	 * 
	 * @return la reponse
	 */
	public static String erreurInterdit()
	{
		return code + 2 + data + "interdit" + fin;
	}
	

	/**
	 * Creation de la reponse pour une erreur de connexion perdue
	 * 
	 * @return la reponse
	 */
	public static String erreurDeconnexion()
	{
		return code + 3 + data + "connexion perdue" + fin;
	}

	//-------------------------------------------------------------------------
	// testeurs de reponses

	/**
	 * Tester si une reponse est une erreur d'implementation manquante
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurImplementionManquante(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == -2;
	}
	
	/**
	 * Tester si une reponse est une erreur serveur
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurServeur(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == -1;
	}
	

	/**
	 * Tester si une reponse est une confirmation
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est une confirmation, false sinon
	 */
	public static boolean isOk(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == 0;
	}
	

	/**
	 * Tester si une reponse est une erreur de requete
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurRequete(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == 1;
	}
	

	/**
	 * Tester si une reponse est une erreur de droits
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurInterdit(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == 2;
	}
	

	/**
	 * Tester si une reponse est une erreur de connexion perdue
	 * 
	 * @param reponse la reponse a tester
	 * @return true si c'est cette erreur, false sinon
	 */
	public static boolean isErreurDeconnexion(String reponse)
	{
		return ControlleurProtocole.reponseCode(reponse) == 3;
	}

	//-------------------------------------------------------------------------
	// reponse ok - donnees

	/**
	 * Valide les donnees d'une reponse
	 * 
	 * @param reponse la reponse dont on veut valider les donnees
	 * @return true si les donnees sont valides, false sinon
	 */
	public static boolean valideDonnees(String reponse)
	{
		// TODO voir si on peut faire mieux
		return ControlleurProtocole.reponseDonnees(reponse).length() >= 0;
	}

	/**
	 * Extrait les donnees d'une reponse
	 * 
	 * @param reponse la reponse dont on doit extraire les donnees
	 * @return les donnees de la reponse
	 */
	public static String extraireDonnees(String reponse)
	{
		return ControlleurProtocole.reponseDonnees(reponse);
	}

	/**
	 * Extrait le JSONObject d'une reponse
	 * 
	 * @param reponse la reponse dont on doit extraire le JSONObject
	 * @return le JSONObject
	 */
	public static JSONObject extraireJSONObject(String reponse)
	{
    	JSONObject js = new JSONObject(reponse);
    	return js.getJSONObject("data");
	}

	/**
	 * Extrait le JSONArray d'une reponse
	 * 
	 * @param reponse la reponse dont on doit extraire le JSONArray
	 * @return le JSONArray
	 */
	public static JSONArray extraireJSONArray(String reponse)
	{
    	JSONObject js = new JSONObject(reponse);
    	return js.getJSONArray("data");
	}
}
