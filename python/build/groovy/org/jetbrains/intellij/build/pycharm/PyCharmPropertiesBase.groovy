/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.intellij.build.pycharm

import org.jetbrains.intellij.build.ApplicationInfoProperties
import org.jetbrains.intellij.build.BuildContext
import org.jetbrains.intellij.build.BuildTasks
import org.jetbrains.intellij.build.MacDistributionCustomizer
import org.jetbrains.intellij.build.ProductProperties
import org.jetbrains.intellij.build.WindowsDistributionCustomizer

/**
 * @author nik
 */
abstract class PyCharmPropertiesBase extends ProductProperties {
  PyCharmPropertiesBase(String home) {
    baseFileName = "pycharm"
    reassignAltClickToMultipleCarets = true
    productLayout.mainJarName = "pycharm.jar"
    productLayout.additionalPlatformJars.put("pycharm-pydev.jar", "python-pydev")
    additionalDirectoriesWithLicenses = ["$home/python/license"]
  }

  @Override
  void copyAdditionalFiles(BuildContext context, String targetDirectory) {
    def tasks = BuildTasks.create(context)
    tasks.zipSourcesOfModules(["python-pydev"], "$targetDirectory/lib/src/pycharm-pydev-src.zip")
    tasks.zipSourcesOfModules(["python-openapi", "python-psi-api"], "$targetDirectory/lib/src/pycharm-openapi-src.zip")

    context.ant.copy(todir: "$targetDirectory/helpers") {
      fileset(dir: "$context.paths.projectHome/community/python/helpers")
    }
    context.ant.copy(todir: "$targetDirectory/help") {
      fileset(dir: "$context.paths.projectHome/python/help") {
        include(name: "*.pdf")
      }
    }
  }

  @Override
  String environmentVariableBaseName(ApplicationInfoProperties applicationInfo) {
    "PYCHARM"
  }
}