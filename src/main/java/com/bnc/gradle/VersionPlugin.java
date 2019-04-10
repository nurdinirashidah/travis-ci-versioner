package com.bnc.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class VersionPlugin implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    project.getTasks().create("showVersion", VersionTask.class);
    VersionPluginExtension versionConfiguration = project.getExtensions().create("travisVersioner", VersionPluginExtension.class);
    project.getAllprojects().forEach(subProject -> project.setVersion(versionConfiguration.toString()));
  }
}
