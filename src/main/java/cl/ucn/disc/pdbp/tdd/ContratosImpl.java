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

import cl.ucn.disc.pdbp.tdd.model.dao.Repository;
import cl.ucn.disc.pdbp.tdd.model.dao.RepositoryOrmLite;
import cl.ucn.disc.pdbp.tdd.model.main.Control;
import cl.ucn.disc.pdbp.tdd.model.main.Ficha;
import cl.ucn.disc.pdbp.tdd.model.main.Persona;
import cl.ucn.disc.pdbp.tdd.model.utils.Entity;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of Contratos
 *
 * @author Miguel León Garrido
 */
public class ContratosImpl implements Contratos {

  /**
   * The Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(ContratosImpl.class);

  /**
   * Connection to DataBase.
   */
  private ConnectionSource connectionSource;

  /**
   * The {@link Repository} of {@link Ficha}.
   */
  private Repository<Ficha, Long> repoFicha;

  /**
   * The {@link Repository} of {@link Ficha}.
   */
  private Repository<Persona, Long> repoPersona;

  /**
   * The {@link Repository} of {@link Ficha}.
   */
  private Repository<Control, Long> repoControl;

  /**
   * The Constructor.
   *
   * @param databaseUrl jdbc string to connect to backend
   */
  public ContratosImpl(String databaseUrl) {

    // Verifying Nullity.
    if(databaseUrl == null) {
      throw new IllegalArgumentException("Database URL is Null!");
    }

    try {
      // The connection to database.
      this.connectionSource = new JdbcConnectionSource(databaseUrl);

      // Creating tables.
      TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
      TableUtils.createTableIfNotExists(connectionSource, Persona.class);
      TableUtils.createTableIfNotExists(connectionSource, Control.class);

      // The repositories.
      this.repoFicha = new RepositoryOrmLite<>(this.connectionSource, Ficha.class);
      this.repoPersona = new RepositoryOrmLite<>(this.connectionSource, Persona.class);
      this.repoControl = new RepositoryOrmLite<>(this.connectionSource, Control.class);

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  /**
   * Contrato 01.
   *
   * @param ficha to save.
   * @return the {@link Ficha} saved.
   */
  @Override
  public Ficha registrarPaciente(Ficha ficha) {
    // Nullity
    if(ficha == null) {
      throw new IllegalArgumentException("ficha is null!!");
    }

    // Validate if the ficha already exists
    try {

      // Make a query to check if a ficha with the same numero exists
      QueryBuilder<Ficha, Long> queryFichaNumero = this.repoFicha.getQuery();
      queryFichaNumero.where()
        .like("numero",ficha.getNumero());

      // if exists, throws an error
      if(queryFichaNumero.countOf() > 0)
        throw new RuntimeException("The ficha already exists");

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    // Inserting the ficha in the DataBase
    if(this.repoFicha.create(ficha)) {
      return ficha;
    }

    //The ficha wasn't inserted
    throw new RuntimeException("The ficha couldn't be inserted");

  }

  /**
   * Contrato 02.
   *
   * @param persona to save.
   * @return the {@link Persona} saved.
   */
  @Override
  public Persona registrarPersona(Persona persona) {

    // Nullity
    if(persona == null) {
      throw new IllegalArgumentException("persona is null!!");
    }

    // Validate if the person already exists
    try {

      // Make a query to check if a person with the same rut exists
      QueryBuilder<Persona, Long> queryPersonaRut = this.repoPersona.getQuery();
      queryPersonaRut.where()
                      .like("rut",persona.getRut());

      // if exists, throws an error
      if(queryPersonaRut.countOf() > 0)
        throw new RuntimeException("The person already exists");

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    // Inserting the person in the DataBase
    if(this.repoPersona.create(persona)) {
      return persona;
    }

    //The person wasn't inserted
    throw new RuntimeException("The person couldn't be inserted");
  }

  /**
   * Contrato 03.
   *
   * @param query to search.
   * @return the {@link List} of {@link Ficha}
   */
  @Override
  public List<Ficha> buscarFicha(String query) {

    // Nullity
    if(query == null) {
      throw new IllegalArgumentException("Query is null!!");
    }

    List<Ficha> fichas = new ArrayList<>();

    try{
      // Find by Number
      if (StringUtils.isNumeric(query)) {

        // 1. The fichas with the given number
        fichas.addAll(this.repoFicha.findAll("numero",query));
      }

      // 3. Fing by rut of Duenio
      QueryBuilder<Persona, Long> queryPersonaRut = this.repoPersona.getQuery();
      queryPersonaRut.where().like("rut","%"+query+"%");

      fichas.addAll(this.repoFicha
        .getQuery()
        .join(queryPersonaRut)
        .query());

      // 2. Find by name of Paciente
      fichas.addAll(this.repoFicha
                    .getQuery()
                    .where()
                    .like("nombrePaciente","%"+ query +"%")
                    .query());

      // 4. Find by name of duenio
      QueryBuilder<Persona, Long> queryPersonaNombre = this.repoPersona.getQuery();
      queryPersonaNombre.where().like("nombre","%"+query+"%");

      // run the join
      fichas.addAll(this.repoFicha
                    .getQuery()
                    .join(queryPersonaNombre)
                    .query());


    }catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    // Eliminacion de fichas repetidas.
    List<Ficha> fichasNoRepeat = new ArrayList<>(
      fichas.stream().collect(
        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Ficha::getId)))
      )
    );

    return fichasNoRepeat;
  }
}
