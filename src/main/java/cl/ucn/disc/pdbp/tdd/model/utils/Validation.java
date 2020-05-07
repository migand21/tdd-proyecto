/*
 * MIT License
 *
 * Copyright (c) 2020 Miguel Leon Garrido
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cl.ucn.disc.pdbp.tdd.model.utils;

/**
 * Validation Class.
 *
 * @author Miguel León Garrido.
 */
public class Validation {

  /**
   * Validation of the rut.
   * @param rut rut de la persona
   * @return boolean
   */
  public static boolean isRutValid(String rut) {
    if (rut == null) {
      return false;
    }

    int rutLength = rut.length();
    if (rutLength != 8 && rutLength != 9) {
      return false;
    }

    int totalSum = 0;
    for (int i = rutLength - 2;i >= 0;i--) {
      int digitNumber = 0;
      try {
        digitNumber = Integer.parseInt(rut.substring(i,i + 1));
      } catch (Exception e) {
        return false;
      }

      int toMultiply = rutLength - i;
      if (toMultiply > 7) {
        toMultiply -= 6;
      }
      int toSum = digitNumber * toMultiply;
      totalSum += toSum;
    }

    int number = 11 - (totalSum % 11);
    String correctValidationNumber;
    if (number == 10) {
      correctValidationNumber = "K";
    } else {
      correctValidationNumber = Integer.toString(number);
    }

    String validationNumber = rut.substring(rutLength - 1);
    return correctValidationNumber.equalsIgnoreCase(validationNumber);
  }

  /**
   * Validation of the name.
   * @param name nombre de la persona.
   * @return boolean.
   */
  public static boolean isNameValid(String name) {
    return name.length() >= 2;
  }

  /**
   * Validation of the last name.
   * @param lastName apellido de la persona.
   * @return boolean.
   */
  public static boolean isLastNameValid(String lastName) {
    return lastName.length() >= 3;
  }

}
