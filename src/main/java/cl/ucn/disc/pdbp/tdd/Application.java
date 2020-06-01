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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.json.JavalinJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

/**
 * Application main Class
 *
 * @author Miguel Leon Garrido
 */
public final class Application {

  /**
   * The Logger (console)
   */
  private static final Logger log = LoggerFactory.getLogger(Application.class);


  /**
   * Constructor.
   */
  public Application() {
    //nothing
  }

  public static void main(String[] args) {

    // Gson configuration
    Gson gson = new GsonBuilder()
              .setPrettyPrinting()
              .create();

    JavalinJson.setFromJsonMapper(gson::fromJson);
    JavalinJson.setToJsonMapper(gson::toJson);

    log.debug("Starting app");

    // The Javalin application
    Javalin javalin = Javalin.create(config -> {

      // enable extensive development logging for http and websocket
      config.enableDevLogging();

      // Measure the time
      config.requestLogger(((ctx, executionTimeMs) -> {
        log.info("Served {} in {} ms.", ctx.fullUrl(), executionTimeMs);
        ctx.header("Server-Timing", "total;dur=" + executionTimeMs);
      }));

      // Enable routes helper
      config.registerPlugin(new RouteOverviewPlugin("/routes"));

    }).routes(() -> {

      // Version
      ApiBuilder.path("v1", () -> {

        // fichas path
        ApiBuilder.path("fichas", () -> {

          // GET -> /fichas
          ApiBuilder.get(ApiRestEndpoints::getAllFichas);

          // POST -> /fichas
          ApiBuilder.post(ApiRestEndpoints::insertFicha);

          // GET -> /fichas/find/{query}
          ApiBuilder.path("find/:query", () -> {
            ApiBuilder.get(ApiRestEndpoints::findFichas);
          });

          // ROUTE -> /fichas/{numeroFicha}
          ApiBuilder.path(":numeroFicha", () -> {

            // ROUTE -> /fichas/{numeroFicha}/controles
            ApiBuilder.path("controles", () -> {

              // GET -> /fichas/{numeroFicha}/controles
              ApiBuilder.get(ApiRestEndpoints::getAllControles);

              // POST -> /fichas/{numeroFicha}/controles
              ApiBuilder.post(ApiRestEndpoints::insertControl);

            });

            // ROUTE -> /fichas/{numeroFicha}/persona
            ApiBuilder.path("persona", () -> {

              // GET -> /fichas/{numeroFicha}/persona
              ApiBuilder.get(ApiRestEndpoints::getPersona);

            });

          });

        });

        // personas path
        ApiBuilder.path("personas", () -> {

          // GET -> /personas
          ApiBuilder.get(ApiRestEndpoints::getAllPersonas);

          // POST -> /personas
          ApiBuilder.post(ApiRestEndpoints::insertPersona);

        });

      });

    }).start(7000);

    // A simple route to show time
    javalin.get("/", ctx -> {

      // Show the date when a user visit root route.
      ctx.result("The Date: " + ZonedDateTime.now());
    });




  }


}
