
package FacebookClient;


public class Messages {
    
    private String Message;
    
    private String Ttime;
    
    public Messages(String msg, String tm)    
    {
    this.Message  = msg;
    this.Ttime = tm;
    }

public String getMessage()
{
return Message;
}

public String getTtime()
{
return Ttime;
}
        
        

}
