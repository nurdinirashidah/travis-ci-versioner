package com.bnc.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class VersionPlugin implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    VersionTask versionTask = project.getTasks().create("showVersion", VersionTask.class);
    versionTask.setGroup("Help");
    versionTask.setDescription("Show the project version");

    VersionPluginExtension versionConfiguration = project.getExtensions().create("travisVersioner", VersionPluginExtension.class);
    project.afterEvaluate(p -> p.getAllprojects().forEach(subProject -> subProject.setVersion(versionConfiguration.toString())));
  }
}
