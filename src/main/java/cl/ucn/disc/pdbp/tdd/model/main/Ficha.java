package cl.ucn.disc.pdbp.tdd.model.main;

import java.time.ZonedDateTime;

/**
 * Ficha Veterinaria
 *
 * @author Miguel León Garrido
 */
public final class Ficha {

  /**
   * Numero de ficha
   */
  private final long numero;

  /**
   * Nombre del paciente
   */
  private final String nombrePaciente;

  /**
   * Especie: ej. canino
   */
  private final String especie;

  /**
   * fecha de nacimiento del animal
   */
  private final ZonedDateTime fechaNacimiento;

  /**
   * raza del animal
   */
  private final String raza;

  /**
   * sexo del animal
   */
  private final Sexo sexo;

  /**
   * color del animal
   */
  private final String color;

  /**
   * tipo de paciente
   */
  private final Tipo tipo;

  /**
   * The Constructor.
   *
   * @param numero
   * @param nombrePaciente
   * @param especie
   * @param fechaNacimiento
   * @param raza
   * @param sexo
   * @param color
   * @param tipo
   */
  public Ficha(long numero, String nombrePaciente, String especie, ZonedDateTime fechaNacimiento, String raza,
               Sexo sexo, String color, Tipo tipo) {
    this.numero = numero;
    this.nombrePaciente = nombrePaciente;
    this.especie = especie;
    this.fechaNacimiento = fechaNacimiento;
    this.raza = raza;
    this.sexo = sexo;
    this.color = color;
    this.tipo = tipo;
  }

  /**
   *
   * @return la especie del animal
   */
  public String getEspecie() {
    return especie;
  }

  /**
   *
   * @return la fecha de nacimiento del animal
   */
  public ZonedDateTime getFechaNacimiento() {
    return fechaNacimiento;
  }

  /**
   *
   * @return la raza del animal
   */
  public String getRaza() {
    return raza;
  }

  /**
   *
   * @return el sexo del animal
   */
  public Sexo getSexo() {
    return sexo;
  }

  /**
   *
   * @return el color del animal
   */
  public String getColor() {
    return color;
  }

  /**
   *
   * @return el tipo de paciente
   */
  public Tipo getTipo() {
    return tipo;
  }

  /**
   *
   * @return el numero de la ficha
   */
  public long getNumero() {
    return numero;
  }

  /**
   *
   * @return el nombre del paciente
   */
  public String getNombrePaciente() {
    return nombrePaciente;
  }
}
