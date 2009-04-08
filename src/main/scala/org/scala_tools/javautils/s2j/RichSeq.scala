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
package org.scala_tools.javautils.s2j

import java.util.{List => JList}
import scala.collection.jcl.BufferWrapper
import wrappers._

class RichSeq[T](seq: Seq[T]) {
  def toJava: JList[T] = seq match {
    case bw: BufferWrapper[_] =>
      bw.underlying.asInstanceOf[JList[T]]
    case _ => new SeqWrapper[T] {
      type Wrapped = Seq[T]
      protected val underlying = seq
    }
  }
}
