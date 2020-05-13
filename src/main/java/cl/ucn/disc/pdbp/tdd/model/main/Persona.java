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
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Persona Class.
 *
 * @author Miguel León Garrido
 */
@DatabaseTable(tableName = "persona")
public class Persona {

  /**
   * The id.
   */
  @DatabaseField(generatedId = true)
  private Long id;
  /**
   * The name.
   */
  @DatabaseField(canBeNull = false)
  private String nombre;

  /**
   * The last name.
   */
  @DatabaseField(canBeNull = false)
  private String apellido;

  /**
   * The rut.
   */
  @DatabaseField(canBeNull = false, index = true)
  private String rut;

  /**
   * The direccion.
   */
  @DatabaseField(canBeNull = false)
  private String direccion;

  /**
   * The telefono fijo.
   */
  @DatabaseField(canBeNull = false)
  private Integer telefonoFijo;

  /**
   * The telefono movil.
   */
  @DatabaseField(canBeNull = false)
  private Integer telefonoMovil;


  /**
   * The email.
   */
  @DatabaseField(canBeNull = false)
  private String email;

  /**
   * Empty Constructor.
   */
  Persona() {
    //nothing
  }

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
  public Persona(String nombre, String apellido, String rut, String direccion, Integer telefonoFijo,
                 Integer telefonoMovil, String email) throws RuntimeException {

    Validation v = new Validation();
    if (nombre == null || apellido == null || rut == null || direccion == null || telefonoMovil == null || telefonoFijo == null ||  email == null) {
      throw new NullPointerException("Null parameter!");
    }

    if (!v.isRutValid(rut)) {
      throw new RuntimeException("Invalid Rut!");
    }

    if (!v.isNameValid(nombre)) {
      throw new RuntimeException("Invalid Name!");
    }

    if (!v.isLastNameValid(apellido)) {
      throw new RuntimeException("Invalid Last Name!");
    }

    if (!v.isDireccionValid(direccion)) {
      throw new RuntimeException("Invalid direccion!");
    }

    if (!v.isTelefonoFijoValid(telefonoFijo)) {
      throw new RuntimeException("Invalid telefonoFijo!");
    }

    if (!v.isTelefonoMovilValid(telefonoMovil)) {
      throw new RuntimeException("Invalid telefonoMovil!");
    }

    if (!v.isEmailValid(email)) {
      throw new RuntimeException("Invalid Size for Last Name!");
    }

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
   * @return el id de la persona
   */
  public Long getId() {
    return id;
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

  /**
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

  /**
   *
   * @param direccion new direccion
   */
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  /**
   *
   * @param telefonoFijo new telefonoFijo
   */
  public void setTelefonoFijo(Integer telefonoFijo) {
    this.telefonoFijo = telefonoFijo;
  }

  /**
   *
   * @param telefonoMovil new telefonoMovil
   */
  public void setTelefonoMovil(Integer telefonoMovil) {
    this.telefonoMovil = telefonoMovil;
  }

  /**
   *
   * @param email new email
   */
  public void setEmail(String email) {
    this.email = email;
  }
}

