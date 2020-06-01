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
  private static final Contratos CONTRATOS = new ContratosImpl("jdbc:sqlite:fivet.db");

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
    Long numero = Long.parseLong(ctx.formParam("numero"));
    String nombrePaciente = ctx.formParam("nombrePaciente");
    String especie = ctx.formParam("especie");
    ZonedDateTime fechaNacimiento = ZonedDateTime.parse(ctx.formParam("fechaNacimiento"));
    String raza = ctx.formParam("raza");
    Sexo sexo;

    if(ctx.formParam("sexo").equalsIgnoreCase("hembra")) {
      sexo = Sexo.HEMBRA;
    } else {
      sexo = Sexo.MACHO;
    }
    String color = ctx.formParam("color");

    Tipo tipo;
    if(ctx.formParam("tipo").equalsIgnoreCase("interno")) {
      tipo = Tipo.INTERNO;
    } else {
      tipo = Tipo.EXTERNO;
    }

    // We need the duenio as a Persona to create a ficha
    // TODO: aqui asumi que viene con el id del duenio como dato, y que las fechas vienen en el formato necesario
    Long idDuenio = Long.parseLong(ctx.formParam("duenio"));
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

    ctx.json(controles);

  }

  /**
   * Inserting a control in the DB.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void insertControl(Context ctx) {

    // Obtaining the data
    ZonedDateTime fecha= ZonedDateTime.parse(ctx.formParam("fecha"));
    ZonedDateTime fechaProximoControl = ZonedDateTime.parse(ctx.formParam("fechaProximoControl"));
    float temperatura = Float.parseFloat(ctx.formParam("temperatura"));
    float peso = Float.parseFloat(ctx.formParam("peso"));
    float altura = Float.parseFloat(ctx.formParam("altura"));

    String diagnostico = ctx.formParam("diagnostico");
    String nombreVeterinario = ctx.formParam("nombreVeterinario");

    // We need the ficha to create a control
    // TODO: aqui asumi que viene con el id de la ficha como dato, y que las fechas vienen en el formato necesario
    Long idFicha = Long.parseLong(ctx.formParam("idFicha"));
    Ficha ficha = CONTRATOS.getFicha(idFicha);

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
    ctx.json(personas);
  }

  /**
   * Inserting a persona in the DB.
   *
   * @param ctx the Javalin {@link Context}
   */
  public static void insertPersona( Context ctx) {

    // Obtaining the data
    String nombre = ctx.formParam("nombre");
    String apellido = ctx.formParam("nombre");
    String rut = ctx.formParam("nombre");
    String direccion = ctx.formParam("nombre");
    Integer telefonoFijo = Integer.parseInt(ctx.formParam("telefonoFijo"));
    Integer telefonoMovil = Integer.parseInt(ctx.formParam("telefonoMovil"));
    String email = ctx.formParam("nombre");

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
