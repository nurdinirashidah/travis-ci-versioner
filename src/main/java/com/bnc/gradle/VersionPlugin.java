package com.bnc.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VersionPlugin implements Plugin<Project> {

  private static final Logger logger = LoggerFactory.getLogger(VersionPlugin.class);

  @Override
  public void apply(Project project) {
    logger.debug("plugin apply");
    VersionTask versionTask = project.getTasks().create("showVersion", VersionTask.class);
    versionTask.setGroup("Help");
    versionTask.setDescription("Show the project version");

    VersionPluginExtension versionConfiguration = project.getExtensions().create("travisVersioner", VersionPluginExtension.class);
    project.afterEvaluate(p -> p.getAllprojects().forEach(sp -> sp.setVersion(versionConfiguration.toString())));
  }
}
