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
package com.consol.citrus.selenium.xml;

import com.consol.citrus.config.util.BeanDefinitionParserUtils;
import com.consol.citrus.model.testcase.selenium.ExtractDefinition;
import com.consol.citrus.selenium.action.ExtractAction;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 *
 * @author Tamer Erdogan
 */
public class ExtractActionParser extends WebActionParser {

	/**
	 * @param element
	 * @param parserContext
	 * @return
	 */
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		actionClass = ExtractAction.class;
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(actionClass);
		this.doParse(element, builder);

		String pageName = element.getAttribute("pageName");
		BeanDefinitionParserUtils.setPropertyValue(builder, pageName, "pageName");

		Map<By, ExtractDefinition.Element> elements = new LinkedHashMap<>();
		List<Element> webElements = DomUtils.getChildElementsByTagName(element, "element");
		for (Element webElement : webElements) {
			By by = getByFromElement(webElement);
			ExtractDefinition.Element el = new ExtractDefinition.Element();
			el.setVariable(webElement.getAttribute("variable"));
			el.setAttribute(webElement.getAttribute("attribute"));
			elements.put(by, el);
		}
		builder.addPropertyValue("elements", elements);

		Map<String, String> pageActions = new LinkedHashMap<>();
		List<Element> pageElements = DomUtils.getChildElementsByTagName(element, "page");
		for (Element pageElement : pageElements) {
			String pageAction = pageElement.getAttribute("get");
			String value = pageElement.getAttribute("variable");
			pageActions.put(pageAction, value);
		}
		builder.addPropertyValue("pageActions", pageActions);

		return builder.getBeanDefinition();
	}
}
