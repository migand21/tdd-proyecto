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

import cl.ucn.disc.pdbp.tdd.model.main.Control;
import cl.ucn.disc.pdbp.tdd.model.main.Ficha;
import cl.ucn.disc.pdbp.tdd.model.main.Persona;

import java.util.List;

/**
 * The List of Contratos
 *
 * @author Miguel Leon Garrido
 */
public interface Contratos {

  /**
   * Contrato 01 : Registrar Paciente(Mascota).
   *
   * @param ficha to save.
   * @return the {@link Ficha} saved.
   */
  Ficha registrarPaciente(Ficha ficha);

  /**
   * Contrato 02 : Registrar Persona.
   *
   * @param persona to save.
   * @return the {@link Persona} saved.
   */
  Persona registrarPersona(Persona persona);

  /**
   * Contrato 03 : Buscar Fichas con filtro.
   *
   * @param query to search.
   * @return the {@link List} of {@link Ficha}
   */
  List<Ficha> buscarFicha(String query);

  /**
   *
   * @return all the fichas
   */
  List<Ficha> getAllFichas();

  /**
   *
   * @return all the personas
   */
  List<Persona> getAllPersonas();

  /**
   *
   * @param numero de la ficha
   * @return the duenio of ficha
   */
  List<Control> getAllControlesFromFicha(Long numero);

  /**
   *
   * @param idDuenio to find
   * @return the persona
   */
  Persona getPersona(Long idDuenio);

  /**
   *
   * @param numero of ficha
   * @return the duenio of this ficha
   */
  Persona getPersonaFromFicha(Long numero);

  /**
   *
   * @param idFicha to find
   * @return the ficha
   */
  Ficha getFicha(Long idFicha);

  /**
   *
   * @param pageSize , amount of personas
   * @param page , index of the list
   * @return the {@link List} of {@link Persona}
   */
  List<Persona> getPersonas(Integer pageSize, Integer page);
}
