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

package cl.ucn.disc.pdbp.tdd.model.dao;

import com.j256.ormlite.stmt.QueryBuilder;

import java.util.List;

/**
 * Capa de acceso a los datos.
 * @author Miguel León Garrido.
 */
public interface Repository<T, K> {

  /**
   *
   * @return a List of T
   */
  List<T> findAll();

  /**
   *
   * @param id to search
   * @return the T with id
   */
  T findById(K id);

  /**
   *
   * @param t to save
   * @return true
   */
  boolean create(T t);

  /**
   *
   * @param t to update
   * @return true
   */
  boolean update(T t);

  /**
   *
   * @param id to search
   * @return true
   */
  boolean delete(K id);

  /**
   *
   * @param key to filter
   * @param value to search
   * @return the List of T
   */
  List<T> findAll(String key, Object value);

  /**
   *
   * @return the QueryBuilder
   */
  QueryBuilder<T, K> getQuery();
}
