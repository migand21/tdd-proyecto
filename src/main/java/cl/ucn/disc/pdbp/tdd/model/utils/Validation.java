package cl.ucn.disc.pdbp.tdd.model.utils;

/**
 * Validation Class
 *
 * @author Miguel León Garrido
 */
public class Validation {

  /**
   * Validation of the rut
   * @param rut
   * @return boolean
   */
  public static boolean isRutValid(String rut) {
    if(rut == null){
      return false;
    }

    int rutLength = rut.length();
    String validationNumber = rut.substring(rutLength-1);
    if(rutLength != 8 && rutLength != 9)
      return false;

    int totalSum = 0;
    for(int i = rutLength - 2;i>= 0;i--){
      int digitNumber = 0;
      try{
        digitNumber = Integer.parseInt(rut.substring(i,i+1));
      }catch (Exception e){
        return false;
      }

      int toMultiply = rutLength - i;
      if(toMultiply > 7){
        toMultiply -=6;
      }
      int toSum = digitNumber * toMultiply;
      totalSum+=toSum;
    }

    int number = 11 - (totalSum % 11);
    String correctValidationNumber;
    if(number == 10){
      correctValidationNumber = "K";
    }else{
      correctValidationNumber = Integer.toString(number);
    }

    if(correctValidationNumber.equalsIgnoreCase(validationNumber)){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Validation of the name
   * @param name
   * @return boolean
   */
  public static boolean isNameValid(String name) {
    if(name.length()<2) return false;
    return true;
  }

  /**
   * Validation of the last name
   * @param lastName
   * @return boolean
   */
  public static boolean isLastNameValid(String lastName) {
    if(lastName.length()<3) return false;
    return true;
  }

  /**
   * Validation for Persona constructor
   * @param nombre,apellido,rutOk
   */
  public void isPersonaValid(String nombre, String apellido, String rutOk) {

    if(nombre == null || apellido == null || rutOk == null){
      throw new NullPointerException("Null parameter!");
    }

    if(!isRutValid(rutOk)){
      throw new RuntimeException("Invalid Rut!");
    }

    if(!isNameValid(nombre)){
      throw new RuntimeException("Invalid Size for Name!");
    }

    if(!isLastNameValid(apellido)){
      throw new RuntimeException("Invalid Size for Last Name!");
    }
  }
}
