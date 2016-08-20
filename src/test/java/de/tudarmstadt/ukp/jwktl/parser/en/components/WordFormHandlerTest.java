/*******************************************************************************
 * Copyright 2013
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.tudarmstadt.ukp.jwktl.parser.en.components;

import junit.framework.TestCase;

public abstract class WordFormHandlerTest extends TestCase {
	protected IWordFormHandler handler;

	public void testIgnoreWikipedia() {
		assertFalse(handler.parse("{{wikipedia}}"));
		assertFalse(handler.parse("{{wikipedia|lang=de}}"));
		assertFalse(handler.parse("{{Wikipedia}}"));
	}

	public void testIgnoreSlimWikipedia() {
		assertFalse(handler.parse("{{slim-wikipedia}}"));
		assertFalse(handler.parse("{{slim-wikipedia|lang=de}}"));
	}

	// https://en.wiktionary.org/wiki/Template:wikispecies
	public void testIgnoreWikispecies() {
		assertFalse(handler.parse("{{wikispecies}}"));
	}

	// https://en.wiktionary.org/wiki/Template:examples
	// https://en.wiktionary.org/wiki/Template:examples-right
	public void testIgnoreExamples() {
		assertFalse(handler.parse("{{examples|Foo}}"));
		assertFalse(handler.parse("{{examples-right|Foo}}"));
	}

	// https://en.wiktionary.org/wiki/Template:enum
	public void testIgnoreEnum() {
		assertFalse(handler.parse("{{enum|Foo}}"));
	}

	// https://en.wiktionary.org/wiki/Template:no_entry
	public void testIgnoreNoEntry() {
		assertFalse(handler.parse("{{no entry|}}"));
	}

	public void testIgnoreImageLinks() {
		assertFalse(handler.parse("[[File:Foo.jpg|thumb|right|Bar]]"));
	}

	public void testIgnoreRfcHeader() {
		assertFalse(handler.parse("{{rfc-header|Abbreviation}}"));
	}

	public void testIgnoreAttentionHeader() {
		assertFalse(handler.parse("{{attention|de|needs a headword}}"));
	}

	public void testGetRawHeadwordLineLegacyPattern() throws Exception {
		assertTrue(handler.parse("'''Atheismus''' {{g|m}}"));
		assertEquals("'''Atheismus''' {{g|m}}", handler.getRawHeadwordLine());
	}

	public void testGetRawHeadwordLineTemplatePattern() throws Exception {
		assertTrue(handler.parse("{{some-template}}"));
		assertEquals("{{some-template}}", handler.getRawHeadwordLine());
	}

	public void testGetRawHeadwordLineTemplatePatternOnlyParseOnce() throws Exception {
		assertTrue(handler.parse("{{some-template}}"));
		assertFalse(handler.parse("{{some-other-template}}"));
		assertEquals("{{some-template}}", handler.getRawHeadwordLine());
	}
}
