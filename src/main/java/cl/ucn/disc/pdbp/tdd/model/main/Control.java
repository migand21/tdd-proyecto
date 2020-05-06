package cl.ucn.disc.pdbp.tdd.model.main;

import java.time.ZonedDateTime;

/**
 * Control Class.
 *
 * @author Miguel León Garrido
 */
public class Control {

  /**
   * fecha del control.
   */
  private final ZonedDateTime fecha;

  /**
   * fecha del proximo control.
   */
  private final ZonedDateTime fechaProximoControl;

  /**
   * temperatura del paciente en grados celcius.
   */
  private final float temperatura;

  /**
   * peso del paciente en kilos.
   */
  private final float peso;

  /**
   * altura del paciente en cm.
   */
  private final float altura;

  /**
   * diagnostico del paciente.
   */
  private final String diagnostico;

  /**
   * nombre del veterinario que realizo el control.
   */
  private final String nombreVeterinario;

  /**
   * The Constructor.
   *
   * @param fecha fecha del control
   * @param fechaProximoControl fecha del proximo control
   * @param temperatura temperatura del paciente
   * @param peso peso del paciente
   * @param altura altura del paciente
   * @param diagnostico diagnostico del paciente
   * @param nombreVeterinario nombre del veterinario que atendió al paciente
   */
  public Control(ZonedDateTime fecha, ZonedDateTime fechaProximoControl, float temperatura, float peso, float altura,
                 String diagnostico, String nombreVeterinario) {
    this.fecha = fecha;
    this.fechaProximoControl = fechaProximoControl;
    this.temperatura = temperatura;
    this.peso = peso;
    this.altura = altura;
    this.diagnostico = diagnostico;
    this.nombreVeterinario = nombreVeterinario;
  }

  /** Getter.
   * @return fecha
   */
  public ZonedDateTime getFecha() {
    return fecha;
  }

  /** Getter.
   * @return fecha del proximo control
   */
  public ZonedDateTime getFechaProximoControl() {
    return fechaProximoControl;
  }

  /** Getter.
   * @return la temperatura del paciente
   */
  public float getTemperatura() {
    return temperatura;
  }

  /** Getter.
   * @return el peso del paciente
   */
  public float getPeso() {
    return peso;
  }

  /** Getter.
   * @return la altura del paciente
   */
  public float getAltura() {
    return altura;
  }

  /** Getter.
   * @return informe con el diagnostico
   */
  public String getDiagnostico() {
    return diagnostico;
  }

  /** Getter.
   * @return nombre del veterinario
   */
  public String getNombreVeterinario() {
    return nombreVeterinario;
  }

}
