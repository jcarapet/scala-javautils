/**
 * Copyright 2009 Jorge Ortiz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *
 **/
package org.scala_tools.javautils.j2s

import java.util.Collection
import scala.{Iterable => SIterable, Collection => SCollection}
import org.scala_tools.javautils.j2s.wrappers.SCollectionWrapper
import org.scala_tools.javautils.s2j.wrappers.JCollectionWrapper

class RichJCollection[T, C[U] <: Collection[U]](collection: C[T]) {
  // def map[U](fn: T => U)(implicit build: Build[C[U]]): C[U] = {
  //   val rv = build()
  //   Implicits.richJIterable(collection).foreach { e =>
  //     rv.add(fn(e))
  //   }
  //   rv
  // }
  
  def asScala: SCollection[T] = collection match {
    case cw: JCollectionWrapper[_] =>
      cw.asScala.asInstanceOf[SCollection[T]]
    case _ => new SCollectionWrapper[T] {
      type Wrapped = Collection[T]
      val underlying = collection
    }
  }
}
