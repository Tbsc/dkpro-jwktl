package de.tudarmstadt.ukp.jwktl.parser.en.components;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import de.tudarmstadt.ukp.jwktl.api.PartOfSpeech;
import de.tudarmstadt.ukp.jwktl.api.util.Language;
import de.tudarmstadt.ukp.jwktl.api.util.NLInflection;
import de.tudarmstadt.ukp.jwktl.parser.en.ENWiktionaryEntryParserTest;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Test case for {@link ENDutchInflectionsHandler}
 * Created on 26/08/2017
 * @author tbsc
 */
public class ENDutchInflectionsHandlerTest extends ENWiktionaryEntryParserTest {

    public void testWeakVerbs() throws Exception {
        IWiktionaryPage doorvertellen = parse("doorvertellen.txt");
        Map<NLInflection, String> doorvertellenInfl = doorvertellen.getEntry(0).getDutchVerbInflections();

        assertEquals("doorvertellen", doorvertellenInfl.get(NLInflection.INFINITIVE));
        assertEquals("doorvertellen", doorvertellenInfl.get(NLInflection.GERUND));
        assertEquals("doorvertellen", doorvertellenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", doorvertellenInfl.get(NLInflection.AUXILIARY_VERB));

        assertEquals("vertel door", doorvertellenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("vertelde door", doorvertellenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("vertelt door", doorvertellenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("vertelde door", doorvertellenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("vertelt door", doorvertellenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("vertelde door", doorvertellenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("vertellen door", doorvertellenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("vertelden door", doorvertellenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("vertelle door", doorvertellenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("vertelde door", doorvertellenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("vertellen door", doorvertellenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("vertelden door", doorvertellenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("vertel door", doorvertellenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("vertelt door", doorvertellenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("doorvertellend", doorvertellenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("doorverteld", doorvertellenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage wandelen = parse("wandelen.txt");
        Map<NLInflection, String> wandelenInfl = wandelen.getEntry(0).getDutchVerbInflections();

        assertEquals("wandelen", wandelenInfl.get(NLInflection.INFINITIVE));
        assertEquals("wandelen", wandelenInfl.get(NLInflection.GERUND));
        assertEquals("wandelen", wandelenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", wandelenInfl.get(NLInflection.AUXILIARY_VERB));

        assertEquals("wandel", wandelenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("wandelde", wandelenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("wandelt", wandelenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("wandelde", wandelenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("wandelt", wandelenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("wandelde", wandelenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("wandelen", wandelenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("wandelden", wandelenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("wandele", wandelenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("wandelde", wandelenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("wandelen", wandelenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("wandelden", wandelenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("wandel", wandelenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("wandelt", wandelenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("wandelend", wandelenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gewandeld", wandelenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage laden = parse("laden.txt");
        Map<NLInflection, String> ladenInfl = laden.getEntries().stream()
                .filter(e -> e.getWordLanguage() == Language.get("nld"))
                .collect(Collectors.toList()).get(0).getDutchVerbInflections();

        assertEquals("laden", ladenInfl.get(NLInflection.INFINITIVE));
        assertEquals("laden", ladenInfl.get(NLInflection.GERUND));
        assertEquals("laden", ladenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", ladenInfl.get(NLInflection.AUXILIARY_VERB));

        assertEquals("laad", ladenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("laadde", ladenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("laadt", ladenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("laadde", ladenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("laadt", ladenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("laadde", ladenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("laden", ladenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("laadden", ladenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("lade", ladenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("laadde", ladenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("laden", ladenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("laadden", ladenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("laad", ladenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("laadt", ladenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("ladend", ladenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("geladen", ladenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage vertrouwen = parse("vertrouwen.txt");
        Map<NLInflection, String> vertrouwenInfl = vertrouwen.getEntries().stream()
                .filter(e -> e.getPartOfSpeech() == PartOfSpeech.VERB)
                .collect(Collectors.toList()).get(0).getDutchVerbInflections();

        assertEquals("vertrouwen", vertrouwenInfl.get(NLInflection.INFINITIVE));
        assertEquals("vertrouwen", vertrouwenInfl.get(NLInflection.GERUND));
        assertEquals("vertrouwen", vertrouwenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", vertrouwenInfl.get(NLInflection.AUXILIARY_VERB));

        assertEquals("vertrouw", vertrouwenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("vertrouwde", vertrouwenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("vertrouwt", vertrouwenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("vertrouwde", vertrouwenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("vertrouwt", vertrouwenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("vertrouwde", vertrouwenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("vertrouwen", vertrouwenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("vertrouwden", vertrouwenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("vertrouwe", vertrouwenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("vertrouwde", vertrouwenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("vertrouwen", vertrouwenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("vertrouwden", vertrouwenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("vertrouw", vertrouwenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("vertrouwt", vertrouwenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("vertrouwend", vertrouwenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("vertrouwd", vertrouwenInfl.get(NLInflection.PAST_PARTICIPLE));
    }

    public void testStrongVerbs() throws Exception {
        IWiktionaryPage binden = parse("binden.txt");
        Map<NLInflection, String> bindenInfl = binden.getEntry(0).getDutchVerbInflections();

        assertEquals("binden", bindenInfl.get(NLInflection.INFINITIVE));
        assertEquals("binden", bindenInfl.get(NLInflection.GERUND));
        assertEquals("binden", bindenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", bindenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("3", bindenInfl.get(NLInflection.CLASS));

        assertEquals("bind", bindenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("bond", bindenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("bindt", bindenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("bond", bindenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("bindt", bindenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("bond", bindenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("binden", bindenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("bonden", bindenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("binde", bindenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("bonde", bindenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("binden", bindenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("bonden", bindenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("bind", bindenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("bindt", bindenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("bindend", bindenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gebonden", bindenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage uitkijken = parse("uitkijken.txt");
        Map<NLInflection, String> uitkijkenInfl = uitkijken.getEntry(0).getDutchVerbInflections();

        assertEquals("uitkijken", uitkijkenInfl.get(NLInflection.INFINITIVE));
        assertEquals("uitkijken", uitkijkenInfl.get(NLInflection.GERUND));
        assertEquals("uitkijken", uitkijkenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", uitkijkenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("1", uitkijkenInfl.get(NLInflection.CLASS));

        assertEquals("kijk uit", uitkijkenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("keek uit", uitkijkenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("kijkt uit", uitkijkenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("keek uit", uitkijkenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("kijkt uit", uitkijkenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("keek uit", uitkijkenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("kijken uit", uitkijkenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("keken uit", uitkijkenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("kijke uit", uitkijkenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("keke uit", uitkijkenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("kijken uit", uitkijkenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("keken uit", uitkijkenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("kijk uit", uitkijkenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("kijkt uit", uitkijkenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("uitkijkend", uitkijkenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("uitgekeken", uitkijkenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage kijken = parse("kijken.txt");
        Map<NLInflection, String> kijkenInfl = kijken.getEntry(0).getDutchVerbInflections();

        assertEquals("kijken", kijkenInfl.get(NLInflection.INFINITIVE));
        assertEquals("kijken", kijkenInfl.get(NLInflection.GERUND));
        assertEquals("kijken", kijkenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", kijkenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("1", kijkenInfl.get(NLInflection.CLASS));

        assertEquals("kijk", kijkenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("keek", kijkenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("kijkt", kijkenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("keek", kijkenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("kijkt", kijkenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("keek", kijkenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("kijken", kijkenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("keken", kijkenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("kijke", kijkenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("keke", kijkenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("kijken", kijkenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("keken", kijkenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("kijk", kijkenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("kijkt", kijkenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("kijkend", kijkenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gekeken", kijkenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage geven = parse("geven.txt");
        Map<NLInflection, String> gevenInfl = geven.getEntry(0).getDutchVerbInflections();

        assertEquals("geven", gevenInfl.get(NLInflection.INFINITIVE));
        assertEquals("geven", gevenInfl.get(NLInflection.GERUND));
        assertEquals("geven", gevenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", gevenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("5", gevenInfl.get(NLInflection.CLASS));

        assertEquals("geef", gevenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("gaf", gevenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("geeft", gevenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("gaf", gevenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("geeft", gevenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("gaf", gevenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("geven", gevenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("gaven", gevenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("geve", gevenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("gave", gevenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("geven", gevenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("gaven", gevenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("geef", gevenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("geeft", gevenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("gevend", gevenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gegeven", gevenInfl.get(NLInflection.PAST_PARTICIPLE));
    }

}
