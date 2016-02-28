package stri.java_connect.ihm;

import java.net.URL;

import org.apache.pivot.beans.*;
import org.apache.pivot.collections.*;
import org.apache.pivot.util.*;
import org.apache.pivot.wtk.*;

public class CreationProfil extends Window implements Application, Bindable
{
	private Window window;

	private BoxPane idConnexionBoxPane;
    private TextInput mailTextInput;
    private TextInput mdpTextInput;
    
	private BoxPane nameBoxPane;
    private TextInput prenomTextInput;
    private TextInput nomTextInput;
    
    private BoxPane addressBoxPane;
    private TextInput rueTextInput;
    private TextInput villeTextInput;
    private TextInput codePostalTextInput;
    
    private BoxPane telephoneBoxPane;
    private TextInput numTelTextInput;
    
    private BoxPane competencesBoxPane;
    private TextInput competencesTextInput;
    
    private PushButton submitButton;
    private PushButton retourMenuButton;
    private Label errorLabel;

	public CreationProfil()
	{
		//
	}

	public void startup(final Display display, final Map<String, String> properties) throws Exception
	{
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window) bxmlSerializer.readObject(CreationProfil.class, "/creationProfil.bxml");
		window.open(display);
	}

    public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources)
	{
        // Saisie id/mdp 
    	idConnexionBoxPane = (BoxPane)namespace.get("idConnexionBoxPane");
    	mailTextInput = (TextInput)namespace.get("mailTextInput");
    	mdpTextInput = (TextInput)namespace.get("mdpTextInput");
    	// Saisie nom
    	nameBoxPane = (BoxPane)namespace.get("nameBoxPane");
        prenomTextInput = (TextInput)namespace.get("prenomTextInput");
        nomTextInput = (TextInput)namespace.get("nomTextInput");
        // Saisie adresse
        addressBoxPane = (BoxPane)namespace.get("addressBoxPane");
        rueTextInput = (TextInput)namespace.get("rueTextInput");
        villeTextInput = (TextInput)namespace.get("villeTextInput");
        codePostalTextInput = (TextInput)namespace.get("codePostalTextInput");
        // Saisie numéro de téléphone
        telephoneBoxPane = (BoxPane)namespace.get("telephoneBoxPane");
        numTelTextInput = (TextInput)namespace.get("numTelTextInput");
        // Saisie des compétences
        competencesBoxPane = (BoxPane)namespace.get("competencesBoxPane");
        competencesTextInput = (TextInput)namespace.get("competencesTextInput");
        
        submitButton = (PushButton)namespace.get("submitButton");
        retourMenuButton = (PushButton)namespace.get("retourMenuButton");
        errorLabel = (Label)namespace.get("errorLabel");
        
        retourMenuButton.getButtonPressListeners().add(new ButtonPressListener()
        {
        	public void buttonPressed(Button button)
        	{
        		DesktopApplicationContext.replaceSplashScreen(getDisplay());
        	}
        });
        
        submitButton.getButtonPressListeners().add(new ButtonPressListener()
        {
        	public void buttonPressed(Button button)
        	{
                String mail = mailTextInput.getText();
                String mdp = mdpTextInput.getText();
        		String prenom = prenomTextInput.getText();
                String nom = nomTextInput.getText();
                String rue = rueTextInput.getText();
                String ville = villeTextInput.getText();
                String codePostal = codePostalTextInput.getText();
                String numeroTel = numTelTextInput.getText();
                
                Form.Flag flag = null;
                // Vérification que tous les champs sont remplis
                if ( mail.length() == 0 || mdp.length() == 0 || prenom.length() == 0  || nom.length() == 0 || rue.length() == 0 || ville.length() == 0 || codePostal.length() == 0 || numeroTel.length() == 0)
                {
                    flag = new Form.Flag(MessageType.ERROR, "Obligatoire");
                }

                Form.setFlag(nameBoxPane, flag);

                if (flag == null)
                {
                    errorLabel.setText("");
                    //Prompt.prompt("Pretending to submit...", Forms.this);
                }
                else
                {
                    errorLabel.setText("Some required information is missing.");
                }
            }
        });
    }

    
    
    
	public boolean shutdown(final boolean optional) throws Exception
	{
		this.close();
		return false;
	}
	
	/*
	private final ButtonPressListener messagerieP2P = new ButtonPressListener()
	{
		public void buttonPressed(final Button button)
		{
			Alert.alert(MessageType.INFO, "Messagerie peer to peerl", Menu.this);
		}
	};
	*/
	
	public void suspend() throws Exception
	{
		//
	}
 
	public void resume() throws Exception
	{
		//
	}
}
