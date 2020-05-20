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

package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.main.Persona;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Model
 *
 * @author Miguel León Garrido
 */
public final class TestContratosImpl {

  /**
   * The Logger (console)
   */
  private static final Logger log = LoggerFactory.getLogger(TestContratosImpl.class);

  /**
   * Testing the contrato 01
   */
  @Test
  public void testContrato1(){

    ContratosImpl testContratos = new ContratosImpl("jdbc:h2:mem:account");



  }

  /**
   * Testing the contrato 02
   */
  @Test
  public void testContrato2(){

    ContratosImpl testContratos = new ContratosImpl("jdbc:h2:mem:account");

    Persona persona1 = new Persona("Andrea", "Contreras", "152532873", "michimalongo 1826",
      2244397, 63887303, "asd123@gmail.com");

    //Inserting a null person
    Assertions.assertThrows(IllegalArgumentException.class, () -> testContratos.registrarPersona(null));

    //Inserting a person
    testContratos.registrarPersona(persona1);

    //Inserting the same person
    Assertions.assertThrows(RuntimeException.class, () -> testContratos.registrarPersona(persona1));

  }

  /**
   * Testing the contrato 03
   */
  @Test
  public void testContrato3(){

    ContratosImpl testContratos = new ContratosImpl("jdbc:h2:mem:account");

  }

}
