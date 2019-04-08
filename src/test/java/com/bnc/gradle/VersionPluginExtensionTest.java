package com.bnc.gradle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VersionPluginExtensionTest {

  private VersionPluginExtension versionPluginExtension;

  @BeforeEach
  private void setup() {
    versionPluginExtension = new VersionPluginExtension();
  }

  @AfterEach
  private void teardown() {
    System.setProperty("CI", "");
    System.setProperty("TRAVIS_PULL_REQUEST", "");
    System.setProperty("TRAVIS_BUILD_NUMBER", "");
  }

  @Test
  void testDefaultVersion() {
    assertThat(versionPluginExtension.toString()).isEqualTo("0.0-SNAPSHOT");

  }

  @Test
  void testMajorVersionChanged() {
    versionPluginExtension.setMajor(1);
    assertThat(versionPluginExtension.toString()).isEqualTo("1.0-SNAPSHOT");
  }

  @Test
  void testMinorVersionChanged() {
    versionPluginExtension.setMinor(5);
    assertThat(versionPluginExtension.toString()).isEqualTo("0.5-SNAPSHOT");
  }

  @Test
  void testPostfixChanged() {
    versionPluginExtension.setPostfix("JUNIT");
    assertThat(versionPluginExtension.toString()).isEqualTo("0.0-JUNIT");
  }
}
