package com.soliduslabs.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/*
 * demonstrates how service-specific properties can be injected
 */

@ConfigurationProperties(prefix = "hotel.service", ignoreUnknownFields = false)
@Component
public class ServiceProperties {

	@Getter
	@Setter
    @NotNull // you can also create configurationPropertiesValidator
	private String name = "Empty";

}
