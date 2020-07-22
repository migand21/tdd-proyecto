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

import cl.ucn.disc.pdbp.tdd.model.main.*;
import cl.ucn.disc.pdbp.tdd.model.utils.Entity;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * ApiRestEndpoint class.
 *
 * @author Miguel Leon Garrido.
 */
public class ApiRestEndpoints {

  /**
   * The Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(ApiRestEndpoints.class);

  /**
   * The contratos instance.
   */
  private static final ContratosImpl CONTRATOS = new ContratosImpl("jdbc:sqlite:fivet.db");

  private ApiRestEndpoints() {
    //nothing
  }

  /**
   * Getting all the fichas.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void getAllFichas(Context ctx) {

    log.debug("Getting all the fichas...");

    // Getting all the fichas
    List<Ficha> fichas = CONTRATOS.getAllFichas();

    //fichas.forEach(ficha -> log.debug("Ficha: {}.",Entity.toString(ficha)));

    ctx.json(fichas);
  }

  /**
   * Finding a ficha with a query.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void findFichas(Context ctx) {

    // Getting the query sended
    String query = ctx.pathParam("query");
    log.debug("finding fichas with query <{}> ..",query);

    // Finding the fichas with the query
    List<Ficha> fichas = CONTRATOS.buscarFicha(query);

    ctx.json(fichas);
  }

  /**
   * Inserting a ficha in the DB.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void insertFicha( Context ctx) {

    // Obtaining all the data
    Long numero = Long.parseLong(ctx.queryParam("numero"));
    String nombrePaciente = ctx.queryParam("nombrePaciente");
    String especie = ctx.queryParam("especie");
    ZonedDateTime fechaNacimiento = ZonedDateTime.parse(ctx.queryParam("fechaNacimiento"));
    String raza = ctx.queryParam("raza");
    Sexo sexo;

    if(ctx.queryParam("sexo").equalsIgnoreCase("hembra")) {
      sexo = Sexo.HEMBRA;
    } else {
      sexo = Sexo.MACHO;
    }
    String color = ctx.queryParam("color");

    Tipo tipo;
    if(ctx.queryParam("tipo").equalsIgnoreCase("interno")) {
      tipo = Tipo.INTERNO;
    } else {
      tipo = Tipo.EXTERNO;
    }

    // We need the duenio as a Persona to create a ficha
    // TODO: aqui asumi que viene con el id del duenio como dato, y que las fechas vienen en el formato necesario
    Long idDuenio = Long.parseLong(ctx.queryParam("duenio"));
    Persona duenio = CONTRATOS.getPersona(idDuenio);

    // Creating and inserting the ficha
    Ficha ficha = new Ficha(numero,nombrePaciente,especie,fechaNacimiento,raza,sexo,color,tipo,duenio);
    CONTRATOS.registrarPaciente(ficha);

  }

  /**
   * finding the controles of the ficha in the DB.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void getAllTheControlesFromFicha(Context ctx) {

    // Getting the numeroFicha of control
    Long numero = Long.parseLong(ctx.pathParam("numeroFicha"));

    // Finding the controles
    List<Control> controles = CONTRATOS.getAllControlesFromFicha(numero);

    //controles.forEach(control -> log.debug("Control: {}.",Entity.toString(control)));

    ctx.json(controles);

  }

  /**
   * Inserting a control in the DB.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void insertControl(Context ctx) {

    // Obtaining the data
    ZonedDateTime fecha= ZonedDateTime.parse(ctx.queryParam("fecha"));
    ZonedDateTime fechaProximoControl = ZonedDateTime.parse(ctx.queryParam("fechaProximoControl"));
    float temperatura = Float.parseFloat(ctx.queryParam("temperatura"));
    float peso = Float.parseFloat(ctx.queryParam("peso"));
    float altura = Float.parseFloat(ctx.queryParam("altura"));

    String diagnostico = ctx.queryParam("diagnostico");
    String nombreVeterinario = ctx.queryParam("nombreVeterinario");

    // We need the ficha to create a control
    Long numeroFicha = Long.parseLong(ctx.pathParam("numeroFicha"));
    Ficha ficha = CONTRATOS.getFicha(numeroFicha);

    // creating and inserting the control in the DB.
    Control control = new Control(fecha,fechaProximoControl,temperatura,peso,altura,diagnostico,nombreVeterinario,ficha);

    CONTRATOS.registrarControl(control);

  }

  /**
   * Getting all the personas in the DB.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void getAllPersonas(Context ctx) {

    log.debug("Getting all the personas...");

    List<Persona> personas = CONTRATOS.getAllPersonas();
    personas.forEach(persona -> log.debug("Persona: {}.", Entity.toString(persona)));
    ctx.json(personas);
  }

  /**
   * Inserting a persona in the DB.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void insertPersona( Context ctx) {

    // Obtaining the data
    String nombre = ctx.queryParam("nombre");
    String apellido = ctx.queryParam("apellido");
    String rut = ctx.queryParam("rut");
    String direccion = ctx.queryParam("direccion");
    Integer telefonoFijo = Integer.parseInt(ctx.queryParam("telefonoFijo"));
    Integer telefonoMovil = Integer.parseInt(ctx.queryParam("telefonoMovil"));
    String email = ctx.queryParam("email");

    // Creating and inserting the persona
    Persona persona = new Persona(nombre,apellido,rut,direccion,telefonoFijo,telefonoMovil,email);

    CONTRATOS.registrarPersona(persona);

  }

  /**
   * getting a duenio from the DB.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void getPersonaFromFicha(Context ctx) {

    // Getting the numeroFicha of control
    Long numero = Long.parseLong(ctx.pathParam("numeroFicha"));

    // Finding the duenio
    Persona duenio = CONTRATOS.getPersonaFromFicha(numero);

    //log.debug("Persona: {}.", Entity.toString(duenio));

    ctx.json(duenio);

  }

  /**
   * getting a list of personas with some filter.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void getAllPersonasWithQuery(Context ctx) {

    // Obtaining the data of the query
    Integer pageSize = ctx.queryParam("pageSize",Integer.class).get();
    Integer page = ctx.queryParam("page",Integer.class).get();

    // Finding the personas
    List<Persona> personas = CONTRATOS.getPersonas(pageSize,page);

    ctx.json(personas);

  }
}
