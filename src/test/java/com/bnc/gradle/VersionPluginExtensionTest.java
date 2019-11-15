package com.bnc.gradle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VersionPluginExtensionTest {

  private VersionPluginExtension versionPluginExtension;

  @BeforeEach
  private void setup() {
    versionPluginExtension = new VersionPluginExtension();
  }

  @Test
  void testDefaultVersion() {
    assertThat(versionPluginExtension.toString()).isNotBlank();
    assertThat(versionPluginExtension.toString()).isNotEmpty();
  }
}
