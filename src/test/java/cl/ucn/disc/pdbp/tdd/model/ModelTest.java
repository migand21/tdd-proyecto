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

package cl.ucn.disc.pdbp.tdd.model;

import cl.ucn.disc.pdbp.tdd.model.main.Persona;
import cl.ucn.disc.pdbp.tdd.model.utils.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Model
 *
 * @author Miguel León Garrido
 */
public final class ModelTest {

    /**
     * The Logger (console)
     */
    private static final Logger log = LoggerFactory.getLogger(ModelTest.class);

    /**
     * Test the Persona.
     * - El nombre no puede ser null.
     * - El nombre debe tener al menos 2 letras.
     * - El apellido no puede ser null.
     * - El apellido debe tener al menos 3 letras.
     * - El rut no puede ser null
     * - El rut debe ser valido.
     */
    @Test
    public void testPersona() {

        log.debug("Testing Persona ..");
        // The data!
        log.debug(".. valid ..");
        String nombre = "Andrea";
        String nombreError = "a";
        String apellido = "Contreras";
        String apellidoError = "ab";
        String rutOk = "152532873";
        String rutError = "15253287K";
        String direccion = "michimalongo 1826";
        String direccionError = "a";
        Integer telefonoFijo = 2244397;
        Integer telefonoFijoError = 1122;
        Integer telefonoMovil = 63227303;
        Integer telefonoMovilError = 1122;
        String email = "probando@alumnos.ucn.cl";
        String emailError = "probando@asd";
        String nombreApellido = nombre + " " + apellido;

        // Test constructor and getters
        Persona persona = new Persona(nombre, apellido, rutOk,direccion,telefonoFijo,telefonoMovil,email);
        Assertions.assertEquals(persona.getNombre(), nombre);
        Assertions.assertEquals(persona.getApellido(), apellido);
        Assertions.assertEquals(persona.getNombreApellido(), nombreApellido);
        Assertions.assertEquals(persona.getRut(), rutOk);
        Assertions.assertEquals(persona.getDireccion(), direccion);
        Assertions.assertEquals(persona.getTelefonoFijo(), telefonoFijo);
        Assertions.assertEquals(persona.getTelefonoMovil(), telefonoMovil);
        Assertions.assertEquals(persona.getEmail(), email);
        // Testing nullity
        log.debug(".. nullity ..");
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, rutOk,direccion,telefonoFijo,telefonoMovil,email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, rutOk,direccion,telefonoFijo,telefonoMovil,email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, null,direccion,telefonoFijo,telefonoMovil,email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, rutOk,null,telefonoFijo,telefonoMovil,email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, rutOk,direccion,null,telefonoMovil,email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, rutOk,direccion,telefonoFijo,null,email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, rutOk,direccion,telefonoFijo,telefonoMovil,null));

        // Testing invalid rut
        log.debug(".. rut ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutError,direccion,telefonoFijo,telefonoMovil,email));

        // Testing invalid name
        log.debug(".. name ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombreError, apellido, rutOk,direccion,telefonoFijo,telefonoMovil,email));

        // Testing invalid last name
        log.debug(".. last name ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellidoError, rutOk,direccion,telefonoFijo,telefonoMovil,email));

        // Testing invalid direccion
        log.debug(".. direccion ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutOk,direccionError,telefonoFijo,telefonoMovil,email));

        // Testing invalid telefonoFijo
        log.debug(".. telefonoFijo ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutOk,direccion,telefonoFijoError,telefonoMovil,email));

        // Testing invalid telefonoMovil
        log.debug(".. telefonoMovil ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutOk,direccion,telefonoFijo,telefonoMovilError,email));

        // Testing invalid email
        log.debug(".. email ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutOk,direccion,telefonoFijo,telefonoMovil,emailError));
        log.debug("Done.");
    }

    /**
     * Test the digito verificador.
     */
    @Test
    //@Disabled // remove to run
    public void testDigitoVerificador() {

        Assertions.assertFalse(Validation.isRutValid(null));

        Assertions.assertTrue(Validation.isRutValid("152532873"));
        Assertions.assertTrue(Validation.isRutValid("21195194K"));
        Assertions.assertTrue(Validation.isRutValid("121244071"));
        Assertions.assertTrue(Validation.isRutValid("198127949"));
        Assertions.assertTrue(Validation.isRutValid("202294316"));

        Assertions.assertFalse(Validation.isRutValid("1525A2873"));
        Assertions.assertFalse(Validation.isRutValid("15253287K"));
        Assertions.assertFalse(Validation.isRutValid("15253287-"));

    }

}
