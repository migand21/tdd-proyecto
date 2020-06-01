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

import cl.ucn.disc.pdbp.tdd.model.main.Ficha;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
   * @param ctx the Javalin {@link Context}
   */
  public static void getAllFichas(Context ctx) {

    log.debug("Getting all the fichas...");
    List<Ficha> fichas = CONTRATOS.getAllFichas();
    ctx.json(fichas);

  }


}
