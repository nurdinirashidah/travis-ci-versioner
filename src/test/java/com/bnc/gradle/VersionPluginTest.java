package com.bnc.gradle;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class VersionPluginTest {

  @TempDir
  static Path tempDir;

  private Path buildFile;

  @BeforeEach
  private void setup() throws IOException {
    buildFile = Files.createFile(tempDir.resolve("build.gradle"));
    String buildConfig = "plugins {\n"
        + "                id 'com.bnc.gradle.travis-ci-versioner'\n"
        + "            }\n";
    Files.writeString(buildFile, buildConfig);
  }

  @Test
  void testDefaultConfiguration() {
    BuildResult actual = GradleRunner.create().withProjectDir(buildFile.getParent().toFile()).withArguments("showVersion").withPluginClasspath()
        .build();
    actual.getOutput().contains("0.0-SNAPSHOT");
    assertThat(actual.task(":showVersion").getOutcome()).isEqualTo(TaskOutcome.SUCCESS);
  }
}