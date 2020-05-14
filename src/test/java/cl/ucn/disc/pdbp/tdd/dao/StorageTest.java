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

package cl.ucn.disc.pdbp.tdd.dao;

import cl.ucn.disc.pdbp.tdd.model.dao.Repository;
import cl.ucn.disc.pdbp.tdd.model.dao.RepositoryOrmLite;
import cl.ucn.disc.pdbp.tdd.model.main.Ficha;
import cl.ucn.disc.pdbp.tdd.model.main.Persona;
import cl.ucn.disc.pdbp.tdd.model.main.Sexo;
import cl.ucn.disc.pdbp.tdd.model.main.Tipo;
import cl.ucn.disc.pdbp.tdd.model.utils.Entity;
import cl.ucn.disc.pdbp.tdd.model.utils.Validation;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * The Model for DB Testing
 *
 * @author Miguel León Garrido
 */
public final class StorageTest {

  /**
   * The Logger (console)
   */
  private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

  /**
   * Test a insert for Persona in the DB.
   * - La persona debe ser ingresada a la BD
   * - Se debe poder obtener la persona desde la BD
   * - Solo puede haber una persona con el mismo rut
   * - Los datos ingresados deben ser correctos
   */
  @Test
  public void testDatabase() throws SQLException {

    // The database url to use
    String databaseUrl = "jdbc:h2:mem:account";

    // connection configuration
    try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

      // create the persona table if not existing
      TableUtils.createTableIfNotExists(connectionSource, Persona.class);

      Dao<Persona,Long> daoPersona = DaoManager.createDao(connectionSource,Persona.class);

      // creating a persona for testing
      Persona persona = new Persona("Andrea","Contreras","152532873","michimalongo 1826",
        224439,63887303,"asd123@gmail.com");
      int tuples = daoPersona.create(persona);
      log.debug("Id: {}",persona.getId());
      Assertions.assertEquals(1,tuples,"Save tuples != 1");

      Persona personaDb = daoPersona.queryForId(persona.getId());

      Assertions.assertEquals(persona.getNombre(),personaDb.getNombre(),"Error, Nombres distintos");
      Assertions.assertEquals(persona.getApellido(),personaDb.getApellido(),"Error, Apellidos distintos");
      Assertions.assertEquals(persona.getRut(),personaDb.getRut(),"Error, Ruts distintos");

      List<Persona> personaList = daoPersona.queryForEq("rut","152532873");
      Assertions.assertEquals(1,personaList.size(),"Hay mas de una persona con el mismo rut");

      Assertions.assertEquals(0,daoPersona.queryForEq("rut","123456").size(),"No deberia existir este rut");

    }catch (IOException e) {
      log.error("Error",e);
    }

  }

  /**
   * Testing the repository.
   */
  @Test
  public void testRepository() {

    String databaseUrl = "jdbc:h2:mem:account";

    try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

      // create the persona table if not existing.
      TableUtils.createTableIfNotExists(connectionSource, Persona.class);

      //The repository.
      Repository<Persona, Long> theRepo = new RepositoryOrmLite(connectionSource, Persona.class);

      // Finding All
      List<Persona> personas = theRepo.findAll();
      // The size should be zero
      Assertions.assertEquals(0, personas.size(), "Size != 0");

      // Testing create
      Persona persona = new Persona("Andrea", "Contreras", "152532873", "michimalongo 1826",
        2244397, 63887303, "asd123@gmail.com");
      if (! theRepo.create(persona)) {
        Assertions.fail("No se inserto la persona");
      }

      // Testing finding
      Persona personaRepo = theRepo.findById(persona.getId());
      if(personaRepo == null) {
        Assertions.fail("No se encontro la persona");
      }

      //Testing if the persona was insert
      List<Persona> personasInsert = theRepo.findAll();
      // The size should be one
      Assertions.assertEquals(1, personasInsert.size(), "Size != 1");

      //Testing update
      persona.setDireccion("michimalongo 1234");
      if (! theRepo.update(persona)) {
        Assertions.fail("No se actualizo la persona");
      }

      //Testing delete
      if (! theRepo.delete(persona.getId())) {
        Assertions.fail("No se elimino la persona");
      }

    }catch (IOException | SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Testing relacionamiento entre un duenio y una ficha(mascota)
   */
  @Test
  public void testRelacionamiento() {

    // The database url to use
    String databaseUrl = "jdbc:h2:mem:account";

    try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

      // create the tables if not existing
      TableUtils.createTableIfNotExists(connectionSource, Persona.class);
      TableUtils.createTableIfNotExists(connectionSource, Ficha.class);

      // create repositories for persona and ficha
      Repository<Persona, Long> personaRepo = new RepositoryOrmLite(connectionSource, Persona.class);
      Repository<Ficha, Long> fichaRepo = new RepositoryOrmLite(connectionSource, Ficha.class);

      // 1.- creating a duenio for testing
      Persona duenio = new Persona("Andrea","Contreras","152532873","michimalongo 1826",
        2244397,63887303,"asd123@gmail.com");
      personaRepo.create(duenio);

      log.debug("Ficha: {}.",Entity.toString(personaRepo.findById(duenio.getId())));

      // 2.- creating ficha for testing related to duenio
      Ficha ficha = new Ficha(123,"ita","felino", ZonedDateTime.now(),"gato montes", Sexo.HEMBRA,"blanco", Tipo.INTERNO,duenio);

      if(!fichaRepo.create(ficha)){
        Assertions.fail("No se pudo crear la ficha");
      }

      // Testing finding the ficha
      Ficha fichaFinding = fichaRepo.findById(ficha.getId());

      // Testing nullity
      if(fichaFinding == null) {
        Assertions.fail("No se encontro la persona");
      }

      // Testing duenio data received
      Assertions.assertNotNull(ficha.getDuenio(),"dueño null");
      Assertions.assertNotNull(ficha.getDuenio().getRut(),"rut dueño null");
      // print ficha data
      log.debug("Ficha: {}.",Entity.toString(fichaFinding));

    }catch (IOException | SQLException e) {
      log.error("Error",e);
    }
  }

}
