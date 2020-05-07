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

package cl.ucn.disc.pdbp.tdd.model.main;

import cl.ucn.disc.pdbp.tdd.model.utils.Validation;

/**
 * Persona Class.
 *
 * @author Miguel León Garrido
 */
public class Persona {

  /**
   * The name.
   */
  private final String nombre;

  /**
   * The last name.
   */
  private final String apellido;

  /**
   * The rut.
   */
  private final String rut;

  /**
   * The direccion.
   */
  private final String direccion;

  /**
   * The telefono fijo.
   */
  private final Integer telefonoFijo;

  /**
   * The telefono movil.
   */
  private final Integer telefonoMovil;

  /**
   * The email.
   */
  private final String email;

  /** Constructor.
   *
   * @param nombre nombre de la persona
   * @param apellido apellido de la persona
   * @param rut rut de la persona
   * @param direccion direccion de la persona
   * @param telefonoFijo telefono fijo de la persona
   * @param telefonoMovil telefono movil de la persona
   * @param email correo electronico de la persona
   */
  public Persona(String nombre, String apellido, String rut, String direccion, Integer telefonoFijo, Integer telefonoMovil, String email) {
    Validation v = new Validation();
    v.isPersonaValid(nombre,apellido,rut);

    this.nombre = nombre;
    this.apellido = apellido;
    this.rut = rut;
    this.direccion = direccion;
    this.telefonoFijo = telefonoFijo;
    this.telefonoMovil = telefonoMovil;
    this.email = email;
  }

  /** Getter.
   *
   * @return el nombre de la persona
   */
  public String getNombre() {
    return this.nombre;
  }

  /** Getter.
   *
   * @return el apellido de la persona
   */
  public String getApellido() {
    return this.apellido;
  }

  /** Getter.
   *
   * @return el nombre y apellido juntos
   */
  public String getNombreApellido() {
    return this.nombre + " " + this.apellido;
  }

  /** Getter.
   *
   * @return el rut de la persona
   */
  public String getRut() {
    return rut;
  }

  /** Getter.
   *
   * @return la direccion
   */
  public String getDireccion() {
    return direccion;
  }

  /** Getter.
   *
   * @return el telefono fijo
   */
  public Integer getTelefonoFijo() {
    return telefonoFijo;
  }

  /** Getter.
   *
   * @return el telefono movil
   */
  public Integer getTelefonoMovil() {
    return telefonoMovil;
  }

  /** Getter.
   *
   * @return el correo electronico
   */
  public String getEmail() {
    return email;
  }
}
