/*
 * Copyright 2023 dragonfly.ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.dragonfly.democrossy
import ai.dragonfly.democrossy.*
import ai.dragonfly.democrossy.given
import scala.language.implicitConversions

import Console.*

trait Demonstration {

  def name:String
  def demo():Unit

  def demonstrate: Unit = {
    println(s"$RESET/*$GREEN Begin $BOLD$name$RESET$GREEN Demonstration$RESET */")
    demo()
    println(s"$RESET/*$RED End $BOLD$name$RESET$RED Demonstration$RESET */")
  }

  def main(args:Array[String]):Unit = demonstrate

}