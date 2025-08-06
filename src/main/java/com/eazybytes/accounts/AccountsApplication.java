package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
   info = @Info(
		   		title = "Accounts Service API",
		version = "1.0",
		description = "API for managing customer accounts in the banking system"
		,contact = @Contact(
				       name = "Usman",
				       email = "usman@gmail.com",
		   				url = "https://www.eazybytes.com/contacts"
   ),
		   license = @License(
				   name = "Apache 2.0",
				   url = "https://www.apache.org/licenses/LICENSE-2.0"
		   )


        ),
		externalDocs = @ExternalDocumentation(
			description = "Accounts Service Documentation",
			url = "https://www.eazybytes.com/accounts-service-docs"
        )
   )


@SpringBootApplication
public class AccountsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountsApplication.class, args);

	}

}
