package com.prevdent.syst;

import datadog.trace.api.DDTags;
import datadog.trace.api.Trace;
import datadog.trace.api.interceptor.MutableSpan;
import datadog.trace.bootstrap.instrumentation.api.AgentTracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SystApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystApplication.class, args);
	}

	@Trace(operationName = "application.start")
	private static void startWithDatadogTrace() {
		// cria o span manualmente
		MutableSpan span = AgentTracer.activeSpan();
		if (span != null) {
			span.setTag(DDTags.RESOURCE_NAME, "Startup da aplicação");
			span.setTag("custom.tag", "iniciando");
		}
		SpringApplication.run(SystApplication.class, new String[]{});
	}

}
