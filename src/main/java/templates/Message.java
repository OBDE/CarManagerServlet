package templates;

public class Message
{
	private String message;
    private AlertType alert;

    public static enum AlertType
    {
        Danger, Warning, Info, Success
    }

    public Message(String message, AlertType alert)
    {
        this.message = message;
        this.alert = alert;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getAlert()
    {
        switch (alert)
        {
            case Danger:
                return "alert alert-danger";
            case Warning:
                return "alert alert-warning";
            case Info:
                return "alert alert-info";
            case Success:
                return "alert alert-success";
            default:
                return null;
        }
    }

    public void setAlert(AlertType alert)
    {
        this.alert = alert;
    }
}
