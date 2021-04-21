package com.acme.store.persist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to ACME Persist.
 *
 * <p>Properties are configured in the {@code application.yml} file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {}
