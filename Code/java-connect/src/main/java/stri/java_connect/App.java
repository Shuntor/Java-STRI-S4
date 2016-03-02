package stri.java_connect;

import org.apache.pivot.wtk.DesktopApplicationContext;

import stri.java_connect.ihm.*;

import java.io.IOException;

import stri.java_connect.client.Client;
import stri.java_connect.client.ClientAnnuaire;
import stri.java_connect.client.ClientMessagerie;
import stri.java_connect.client.ClientMessagerieDirecte;
import stri.java_connect.modele.Utilisateur;
import stri.java_connect.protocol.ProtocoleAnnuaire;

/**
 * @author emeric, remi, thomas
 *
 */
public class App 
{
    /**
     * Main App.java
     * 
     * @param args arguments de lancement
     */
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        /*
        try {
			initAnnuaire();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
        //
		//DesktopApplicationContext.main(MenuPrincipal.class, args);
		//
		//
        System.out.println( "Test msg indirect" );
        System.out.println("\n connexion sur remi");
        ClientMessagerie indirect = new ClientMessagerie(23456);
        try {
			indirect.connexion("remi.barbaste@univ-tlse3.fr", "remimdp");
        indirect.inscription("remi.barbaste@univ-tlse3.fr", "127.0.0.1", 23456);
        indirect.envoiMessageDiffere("emeric.tosi@univ-tlse3.fr", "il faut pas dormir la nuit");
        System.out.println("liste user connectes : " + indirect.consulterListeUtilisateurConnectes());
        System.out.println("details de l'utilisateur remi qui est connecte : " + indirect.consulterDetailsUtilisateurConnecte("remi.barbaste@univ-tlse3.fr"));
        System.out.println("liste des messages manques : " + indirect.consulterListeMessagesManques());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        indirect.deconnexion();
        System.out.println("\n connexion sur emeric");
        indirect = new ClientMessagerie(23456);
        try {
        indirect.connexion("emeric.tosi@univ-tlse3.fr", "emericmdp");
        indirect.inscription("emeric.tosi@univ-tlse3.fr", "127.0.0.1", 23457);
        System.out.println("liste user connectes : " + indirect.consulterListeUtilisateurConnectes());
			System.out.println("liste des messages manques : " + indirect.consulterListeMessagesManques());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        indirect.deconnexion();
        //

		//
    }

    /**
     * Initialiser l'annuaire du serveur
     * 
     * @throws IOException exception si la communication echoue
     */
    private static void initAnnuaire() throws IOException
    {
        ClientAnnuaire client = new ClientAnnuaire();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setCourriel("remi.barbaste@univ-tlse3.fr");
        utilisateur.setMotDePasse("remimdp");
        utilisateur.setPermissionLecture("anonyme"); /* tous les utilisateurs peuvent voir ce compte */
        utilisateur.setNom("Remi BARBASTE");
        utilisateur.setTelephone("0123456798");
        utilisateur.setDateDiplome(Long.parseLong("2015"));
        utilisateur.addCompetence("Mathématiques");
        String reponse = client.inscription(utilisateur);
        utilisateur = new Utilisateur();
        utilisateur.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(reponse).toString());
       
        client = new ClientAnnuaire();
        utilisateur = new Utilisateur();
        utilisateur.setCourriel("emeric.tosi@univ-tlse3.fr");
        utilisateur.setMotDePasse("emericmdp");
        utilisateur.setPermissionLecture("anonyme"); /* tous les utilisateurs peuvent voir ce compte */
        utilisateur.setNom("Emeric TOSI");
        utilisateur.setTelephone("1234657890");
        utilisateur.setDateDiplome(Long.parseLong("2014"));
        utilisateur.addCompetence("Informatique");
        utilisateur.addCompetence("Base de données");
        reponse = client.inscription(utilisateur);
        utilisateur = new Utilisateur();
        utilisateur.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(reponse).toString());
       
        client = new ClientAnnuaire();
        utilisateur = new Utilisateur();
        utilisateur.setCourriel("thomas.maury@univ-tlse3.fr");
        utilisateur.setMotDePasse("thomasmdp");
        utilisateur.setPermissionLecture("utilisateur"); /* tous les utilisateurs peuvent voir ce compte */
        utilisateur.setNom("Thomas Maury");
        utilisateur.setTelephone("2345678901");
        utilisateur.setDateDiplome(Long.parseLong("2012"));
        utilisateur.addCompetence("français");
        utilisateur.addCompetence("SVT");
        utilisateur.addCompetence("sport");
        reponse = client.inscription(utilisateur);
        utilisateur = new Utilisateur();
        utilisateur.fromJSONString(ProtocoleAnnuaire.extraireJSONObject(reponse).toString());
    }
}
