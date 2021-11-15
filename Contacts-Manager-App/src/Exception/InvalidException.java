package Exception;

public class InvalidException extends Exception{
    public InvalidException(String fields) {
        if (fields.equals("")) {
            System.out.println(fields+ " invalid");
        }
    }


}
