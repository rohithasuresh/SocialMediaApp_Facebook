package FacebookClient;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author Suresh
 */
public class FXMLDocumentController implements Initializable {
    
    

    @FXML private TableView<Messages> facebookWall;
     @FXML private TableColumn<Messages, String> myPost;
    @FXML private TableColumn<Messages, String> time;
    @FXML private Label lblName;
    @FXML private Label lblGender;
    @FXML private TextArea txtTextArea;
        ObservableList<Messages> msg = FXCollections.observableArrayList();

    String Token  = "API_Key1"; // USer access token
    String Token2  = "API_Key2";   // page access token      

    FacebookClient facebook = new com.restfb.DefaultFacebookClient(Token);

    FacebookClient facebookPost = new com.restfb.DefaultFacebookClient(Token2);
    @FXML
    private void handleButtonAction(ActionEvent event)  {

        
    User me = facebook.fetchObject("me", User.class, Parameter.with("fields", "name,email,gender")); 
        
       lblName.setText(me.getName());
       lblGender.setText(me.getGender());
        System.out.println("Full Name: " + me.getName());
        System.out.println("Gender: " + me.getGender());

        Connection<Post> result = facebook.fetchConnection("me/feed", Post.class);
        ArrayList<String> repos = new ArrayList(); 
        repos.add("data");
        String time;
        String mes;
        
        for (List<Post> page : result)
        {
        
        for (Post aPost : page){

          if (aPost.getMessage() != null)
            { 
            
        mes = aPost.getMessage();
        time = aPost.getCreatedTime().toString();
  
        repos.add(mes);
        repos.add(time);
            }
        }
        }
        
        
        
        for (int i = 1;  i < repos.size(); i = i + 2)
        {
            int a, b;
            
            a = i;
            b = i+1;
        }
        facebookWall.setItems(getMessages(repos));
    
    }
    
    @FXML
    private void publishPost(ActionEvent event)  {
        
    String message=txtTextArea.getText().toString();
    FacebookType response = facebookPost.publish("138659869554277/feed", FacebookType.class, Parameter.with("message",message));

    }
    
    
    public ObservableList<Messages> getMessages(ArrayList <String> prdata)
    {

       msg = FXCollections.observableArrayList(); 
        
           for(int j = 1; j < prdata.size(); j = j + 2)
           {
               int a, b;
               a = j;
               b = j+1;
               
               System.out.println(prdata.get(a) + "  " + prdata.get(b));
               msg.add(new Messages(prdata.get(a), prdata.get(b)));
               
           } 
    
        return msg;
    
}
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
myPost.setCellValueFactory(new PropertyValueFactory<Messages, String>("message"));
    time.setCellValueFactory(new PropertyValueFactory<Messages, String>("ttime"));
    }    
    
}
