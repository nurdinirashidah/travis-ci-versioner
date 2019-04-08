package com.bnc.gradle;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class VersionPlugin implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    project.getExtensions().create("travis-versioner", VersionPluginExtension.class);
    project.getTasks().create("version", VersionTask.class);

    project.afterEvaluate(new Action<Project>() {
      @Override
      public void execute(Project evaluatedProject) {
        VersionPluginExtension versionConfiguration = project.getExtensions().findByType(VersionPluginExtension.class);
        project.setVersion(versionConfiguration.toString());
      }
    });
  }
}
