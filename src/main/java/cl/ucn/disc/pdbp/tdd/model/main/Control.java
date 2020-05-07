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
   * @return fecha del control
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
