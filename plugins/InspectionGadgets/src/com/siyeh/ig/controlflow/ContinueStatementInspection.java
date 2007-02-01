/*
 * Copyright 2003-2007 Dave Griffith, Bas Leijdekkers
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
package com.siyeh.ig.controlflow;

import com.intellij.codeInsight.daemon.GroupNames;
import com.intellij.psi.PsiContinueStatement;
import com.siyeh.InspectionGadgetsBundle;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import org.jetbrains.annotations.NotNull;

public class ContinueStatementInspection extends BaseInspection {

    @NotNull
    public String getDisplayName() {
        return InspectionGadgetsBundle.message(
                "continue.statement.display.name");
    }

    public String getGroupDisplayName() {
        return GroupNames.CONTROL_FLOW_GROUP_NAME;
    }

    @NotNull
    protected String buildErrorString(Object... infos) {
        return InspectionGadgetsBundle.message(
                "statement.problem.descriptor");
    }

    public BaseInspectionVisitor buildVisitor() {
        return new ContinueStatementVisitor();
    }

    private static class ContinueStatementVisitor
            extends BaseInspectionVisitor {

        public void visitContinueStatement(
                @NotNull PsiContinueStatement statement) {
            super.visitContinueStatement(statement);
            registerStatementError(statement);
        }
    }
}