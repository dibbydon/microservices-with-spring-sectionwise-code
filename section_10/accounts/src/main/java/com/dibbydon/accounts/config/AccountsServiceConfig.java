/**
 * 
 */
package com.dibbydon.accounts.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Eazy Bytes
 *
 */
@Configuration
@ConfigurationProperties(prefix = "accounts")
@Getter @Setter @ToString
public class AccountsServiceConfig {

	 private String msg;
	 private String buildVersion;
	 private Map<String, String> mailDetails;
	 private List<String> activeBranches;

}
