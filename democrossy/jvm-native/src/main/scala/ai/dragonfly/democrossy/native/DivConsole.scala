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

package ai.dragonfly.democrossy.native

import ai.dragonfly.democrossy.*

object DivConsole {
  private val dc:DivConsole = new DivConsole()
  def apply(id:String = "console", fg:String = "#eeeeee", bg:String = "#2b2b2b", style:String = ""):DivConsole = dc
  def dark(i:String = "console", s:String = ""):DivConsole = dc
  def light(id:String = "console", style:String = ""):DivConsole = dc
}
