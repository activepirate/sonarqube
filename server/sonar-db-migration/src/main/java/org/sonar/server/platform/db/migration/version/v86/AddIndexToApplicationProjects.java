/*
 * SonarQube
 * Copyright (C) 2009-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.platform.db.migration.version.v86;

import java.sql.SQLException;
import org.sonar.db.Database;
import org.sonar.server.platform.db.migration.sql.CreateIndexBuilder;
import org.sonar.server.platform.db.migration.step.DdlChange;

public class AddIndexToApplicationProjects extends DdlChange {
  private static final String TABLE = "app_projects";

  public AddIndexToApplicationProjects(Database db) {
    super(db);
  }

  @Override
  public void execute(Context context) throws SQLException {
    context.execute(new CreateIndexBuilder()
      .setTable(TABLE)
      .setName("uniq_app_projects")
      .addColumn("application_uuid")
      .addColumn("project_uuid")
      .setUnique(true)
      .build());

    context.execute(new CreateIndexBuilder()
      .setTable(TABLE)
      .setName("idx_app_proj_application_uuid")
      .addColumn("application_uuid")
      .build());

    context.execute(new CreateIndexBuilder()
      .setTable(TABLE)
      .setName("idx_app_proj_project_uuid")
      .addColumn("project_uuid")
      .build());
  }
}
