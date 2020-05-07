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

import cl.ucn.disc.pdbp.tdd.model.main.Persona;
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

/**
 * The Model for DB Testing
 *
 * @author Miguel León Garrido
 */
public final class StorageTest {

  private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

  @Test
  public void testDatabase() throws SQLException, IOException {
    String databaseUrl = "jdbc:h2:mem:account";

    try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

      TableUtils.createTableIfNotExists(connectionSource, Persona.class);

      Dao<Persona,Long> daoPersona = DaoManager.createDao(connectionSource,Persona.class);

      Persona persona = new Persona("Andrea","Contreras","152532873","michimalongo 1826",
        224439,63887303,"asd123@gmail.com");
      int tuples = daoPersona.create(persona);
      log.debug("Id: {}",persona.getId());
      Assertions.assertEquals(1,tuples,"Save tuples != 1");

    }

  }

}
