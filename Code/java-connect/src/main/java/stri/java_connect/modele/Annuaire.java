/**
 * 
 */
package stri.java_connect.modele;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author emeric
 *
 */
public class Annuaire
{
	private HashMap<String, Utilisateur> annuaire;
	
	//
	
	/**
	 * Creation d'un Annuaire
	 * 
	 */
	public Annuaire()
	{
		annuaire = new HashMap<String, Utilisateur>();
	}

	/**
	 * Creation d'un Annuaire avec uen taille de depart
	 * 
	 * @param initialCapacity taille de depart
	 */
	public Annuaire(int initialCapacity)
	{
		annuaire = new HashMap<String, Utilisateur>(initialCapacity);
	}

	/**
	 * Creation d'un Annuaire avec chargement de données
	 * 
	 * @param pAnnuaire données a charger
	 */
	public Annuaire(HashMap<String, Utilisateur> pAnnuaire)
	{
		annuaire = pAnnuaire;
	}
	
	//
	
	/**
	 * Enleve tous les utilisateurs; vide l'annuaire.
	 * 
	 */
	public void reset()
	{
		annuaire.clear();
	}
	
	/**
	 * Verifie l'existance d'un utilisateur grace a son courriel (qui l'identifie)
	 * 
	 * @param courriel le courriel qui indentifie l'utilisateur
	 * @return true si l'utilisateur existe, false sinon
	 */
	public boolean existeUtilisateur(String courriel)
	{
		return annuaire.containsKey(courriel) && annuaire.get(courriel) != null; 
	}
	
	/**
	 * Ajoute un utilisateur
	 * 
	 * @param utilisateur l'utilisateur a ajouter
	 */
	public void ajoutUtilisateur(Utilisateur utilisateur)
	{
		annuaire.put(utilisateur.getCourriel(), utilisateur);
	}
	
	/**
	 * Supprime l'utilisateur correspondant au courriel
	 * 
	 * @param courriel le courriel qui identifie l'utilisateur
	 */
	public void suppresionUtilisateur(String courriel)
	{
		annuaire.remove(courriel);
	}
	
	public void suppresionUtilisateur(Utilisateur u)
	{
		suppresionUtilisateur(u.getCourriel());
	}
	
	/**
	 * Retourne l'utilisateur correspondant au courriel
	 * 
	 * @param courriel le courriel qui identifie l'utilisateur
	 * @return l'utilisateur correspondant
	 */
	public Utilisateur getUtilisateur(String courriel)
	{
		return annuaire.get(courriel);
	}
	
	/**
	 * Retourne le clone de l'utilisateur correspondant au courriel
	 * 
	 * @param courriel le courriel qui identifie l'utilisateur
	 * @return le clone de l'utilisateur correspondant
	 */
	public Utilisateur getSecuriseUtilisateur(String courriel)
	{
		return annuaire.get(courriel).clone();
	}
	
	/**
	 * Retourne l'ensemble des utilisateurs
	 * 
	 * @return Collection <Utilisateur> ensemble des utilisateurs
	 */
	public Collection<Utilisateur> getCollectionTousUtilisateurs()
	{
		return annuaire.values();
	}
	
	/**
	 * Retourne la liste des utilisateurs
	 * 
	 * @return ArrayDeque <Utilisateur> liste des utilisateurs
	 */
	public ArrayDeque<Utilisateur> getArrayTousUtilisateurs()
	{
		return new ArrayDeque<Utilisateur>(annuaire.values());
	}
	
	/**
	 * Retourne la liste des utilisateurs (clones)
	 * 
	 * @return ArrayDeque <Utilisateur> liste des utilisateurs (clones)
	 */
	public ArrayDeque<Utilisateur> getArraySecuriseTousUtilisateurs()
	{
		ArrayDeque<Utilisateur> l = new ArrayDeque<Utilisateur>();
		for (Utilisateur utilisateur : annuaire.values())
		{
			l.add(utilisateur.clone());
		}
		return l;
		// return getArrayTousUtilisateurs().clone(); // fonctionne ou non ???
	}
}
