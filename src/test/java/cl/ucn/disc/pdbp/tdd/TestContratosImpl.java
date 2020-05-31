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

import cl.ucn.disc.pdbp.tdd.model.main.Ficha;
import cl.ucn.disc.pdbp.tdd.model.main.Persona;
import cl.ucn.disc.pdbp.tdd.model.main.Sexo;
import cl.ucn.disc.pdbp.tdd.model.main.Tipo;
import cl.ucn.disc.pdbp.tdd.model.utils.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Class for testing Contratos
 *
 * @author Miguel León Garrido
 */
public final class TestContratosImpl {

  /**
   * The Logger (console)
   */
  private static final Logger log = LoggerFactory.getLogger(TestContratosImpl.class);

  /**
   * Testing the contrato 01 : insertar ficha(paciente)
   */
  @Test
  public void testContrato1(){

    ContratosImpl testContratos = new ContratosImpl("jdbc:h2:mem:account");

    //Creating the duenio
    Persona duenio = new Persona("Andrea", "Contreras", "187910366", "michimalongo 1826",
      2244397, 63887303, "asd123@gmail.com");
    testContratos.registrarPersona(duenio);

    //Creating the ficha
    Ficha ficha = new Ficha(123456L,"ita","felino", ZonedDateTime.now(),"gato siames",
      Sexo.HEMBRA,"blanco", Tipo.INTERNO,duenio);

    //Inserting a null ficha
    Assertions.assertThrows(IllegalArgumentException.class, () -> testContratos.registrarPaciente(null));

    //Inserting a ficha
    testContratos.registrarPaciente(ficha);

    //Inserting the same ficha
    Assertions.assertThrows(RuntimeException.class, () -> testContratos.registrarPaciente(ficha));

  }

  /**
   * Testing the contrato 02 : insertar duenio(persona)
   */
  @Test
  public void testContrato2(){

    ContratosImpl testContratos = new ContratosImpl("jdbc:h2:mem:account");

    //Creating a person instance
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
   * Testing the contrato 03 : obtener fichas con filtro
   */
  @Test
  public void testContrato3(){

    ContratosImpl testContratos = new ContratosImpl("jdbc:h2:mem:account");

    //Creating the duenio
    Persona duenio = new Persona("Andrea", "Contreras", "187910366", "michimalongo 1826",
      2244397, 63887303, "asd123@gmail.com");
    testContratos.registrarPersona(duenio);

    //Creating fichas
    Ficha ficha1 = new Ficha(123L,"ita","felino", ZonedDateTime.now(),"gato siames",
      Sexo.HEMBRA,"blanco", Tipo.INTERNO,duenio);

    Ficha ficha2 = new Ficha(212345L,"Andrea","canino", ZonedDateTime.now(),"san bernardo",
      Sexo.HEMBRA,"cafe", Tipo.INTERNO,duenio);

    //Inserting the fichas
    testContratos.registrarPaciente(ficha1);
    testContratos.registrarPaciente(ficha2);

    //Finding fichas
    List<Ficha> fichas = testContratos.buscarFicha("Andrea");

    //Should be 2 if deleting duplicates works
    Assertions.assertEquals(2,fichas.size(), "Fichas was null");

    fichas.forEach(ficha -> log.debug("Ficha: {}.",Entity.toString(ficha)));

  }

}
