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

import cl.ucn.disc.pdbp.tdd.model.dao.ZonedDateTimeType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.ZonedDateTime;

/**
 * Ficha Veterinaria.
 *
 * @author Miguel León Garrido
 */
@DatabaseTable(tableName = "ficha")
public final class Ficha {

  /**
   * ID.
   */
  @DatabaseField(generatedId = true)
  private Long id;

  /**
   * Numero de ficha.
   */
  @DatabaseField(canBeNull = false, unique = true)
  private long numero;

  /**
   * Nombre del paciente.
   */
  @DatabaseField(canBeNull = false)
  private String nombrePaciente;

  /**
   * Especie: ej. canino.
   */
  @DatabaseField(canBeNull = false)
  private String especie;

  /**
   * fecha de nacimiento del animal.
   */
  @DatabaseField(persisterClass = ZonedDateTimeType.class)
  private ZonedDateTime fechaNacimiento;

  /**
   * raza del animal.
   */
  @DatabaseField(canBeNull = false)
  private String raza;

  /**
   * sexo del animal.
   */
  @DatabaseField(canBeNull = false)
  private Sexo sexo;

  /**
   * color del animal.
   */
  @DatabaseField(canBeNull = false)
  private String color;

  /**
   * tipo de paciente.
   */
  @DatabaseField(canBeNull = false)
  private Tipo tipo;

  /**
   * The duenio.
   */
  @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
  private Persona duenio;

  /**
   * Empty Constructor.
   */
  Ficha() {
    // nothing here
  }

  /**
   * The Constructor.
   *
   * @param numero del paciente
   * @param nombrePaciente del paciente
   * @param especie del paciente
   * @param fechaNacimiento del paciente
   * @param raza raza del paciente
   * @param sexo sexo del paciente
   * @param color color del paciente
   * @param tipo tipo del paciente
   * @param duenio del paciente
   */
  public Ficha(long numero, String nombrePaciente, String especie, ZonedDateTime fechaNacimiento, String raza,
               Sexo sexo, String color, Tipo tipo, Persona duenio) {
    this.numero = numero;
    this.nombrePaciente = nombrePaciente;
    this.especie = especie;
    this.fechaNacimiento = fechaNacimiento;
    this.raza = raza;
    this.sexo = sexo;
    this.color = color;
    this.tipo = tipo;
    this.duenio = duenio;
  }

  /** Getter.
   *
   * @return la especie del animal.
   */
  public String getEspecie() {
    return especie;
  }

  /** Getter.
   *
   * @return la fecha de nacimiento del animal.
   */
  public ZonedDateTime getFechaNacimiento() {
    return fechaNacimiento;
  }

  /** Getter.
   *
   * @return la raza del animal.
   */
  public String getRaza() {
    return raza;
  }

  /** Getter.
   *
   * @return el sexo del animal.
   */
  public Sexo getSexo() {
    return sexo;
  }

  /** Getter.
   *
   * @return el color del animal.
   */
  public String getColor() {
    return color;
  }

  /** Getter.
   *
   * @return el tipo de paciente.
   */
  public Tipo getTipo() {
    return tipo;
  }

  /** Getter.
   *
   * @return el numero de la ficha.
   */
  public long getNumero() {
    return numero;
  }

  /** Getter.
   *
   * @return el nombre del paciente.
   */
  public String getNombrePaciente() {
    return nombrePaciente;
  }

  /**
   *
   * @return the duenio of this animal.
   */
  public Persona getDuenio() {
    return this.duenio;
  }

  /**
   *
   * @return the Id.
   */
  public Long getId() {
    return this.id;
  }
}
