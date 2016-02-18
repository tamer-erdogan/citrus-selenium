/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.consol.citrus.validation.matcher.selenium;

import com.consol.citrus.validation.matcher.ValidationMatcherLibrary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Tamer Erdogan
 */
@Configuration
public class ValidationMatcherConfig {

	private final EmptyValidationMatcher emptyValidationMatcher = new EmptyValidationMatcher();
	private final NotEmptyValidationMatcher notEmptyValidationMatcher = new NotEmptyValidationMatcher();
	private final NullValidationMatcher nullValidationMatcher = new NullValidationMatcher();
	private final NotNullValidationMatcher notNullValidationMatcher = new NotNullValidationMatcher();

	@Bean(name = "seleniumValidationMatcherLibrary")
	public ValidationMatcherLibrary getValidationMatcherLibrary() {
		ValidationMatcherLibrary seleniumValidationMatcherLibrary = new ValidationMatcherLibrary();

		seleniumValidationMatcherLibrary.setPrefix("web");
		seleniumValidationMatcherLibrary.setName("seleniumValidationMatcherLibrary");

		seleniumValidationMatcherLibrary.getMembers().put("empty", emptyValidationMatcher);
		seleniumValidationMatcherLibrary.getMembers().put("notEmpty", notEmptyValidationMatcher);
		seleniumValidationMatcherLibrary.getMembers().put("null", nullValidationMatcher);
		seleniumValidationMatcherLibrary.getMembers().put("notNull", notNullValidationMatcher);

		return seleniumValidationMatcherLibrary;
	}
}
