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

    private Map<NLInflection, String> filterByLanguage(IWiktionaryPage page) {
        return page.getEntries().stream()
                .filter(e -> e.getWordLanguage() == Language.get("nld"))
                .collect(Collectors.toList()).get(0).getDutchVerbInflections();
    }

    private Map<NLInflection, String> filterByPartOfSpeech(IWiktionaryPage page) {
        return page.getEntries().stream()
                .filter(e -> e.getPartOfSpeech() == PartOfSpeech.VERB)
                .collect(Collectors.toList()).get(0).getDutchVerbInflections();
    }

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
        Map<NLInflection, String> ladenInfl = filterByLanguage(laden);

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
        Map<NLInflection, String> vertrouwenInfl = filterByPartOfSpeech(vertrouwen);

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

        IWiktionaryPage redden = parse("redden.txt");
        Map<NLInflection, String> reddenInfl = filterByLanguage(redden);

        assertEquals("redden", reddenInfl.get(NLInflection.INFINITIVE));
        assertEquals("redden", reddenInfl.get(NLInflection.GERUND));
        assertEquals("redden", reddenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", reddenInfl.get(NLInflection.AUXILIARY_VERB));

        assertEquals("red", reddenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("redde", reddenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("redt", reddenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("redde", reddenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("redt", reddenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("redde", reddenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("redden", reddenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("redden", reddenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("redde", reddenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("redde", reddenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("redden", reddenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("redden", reddenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("red", reddenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("redt", reddenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("reddend", reddenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gered", reddenInfl.get(NLInflection.PAST_PARTICIPLE));
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

    public void testWeakChtVerbs() throws Exception {
        IWiktionaryPage denken = parse("denken.txt");
        Map<NLInflection, String> denkenInfl = denken.getEntry(0).getDutchVerbInflections();

        assertEquals("denken", denkenInfl.get(NLInflection.INFINITIVE));
        assertEquals("denken", denkenInfl.get(NLInflection.GERUND));
        assertEquals("denken", denkenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", denkenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", denkenInfl.get(NLInflection.CLASS));

        assertEquals("denk", denkenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("dacht", denkenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("denkt", denkenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("dacht", denkenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("denkt", denkenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("dacht", denkenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("denken", denkenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("dachten", denkenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("denke", denkenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("dachte", denkenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("denken", denkenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("dachten", denkenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("denk", denkenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("denkt", denkenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("denkend", denkenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gedacht", denkenInfl.get(NLInflection.PAST_PARTICIPLE));

        // doordenken CAN be either prefixed or separable, testing both
        IWiktionaryPage doordenken = parse("doordenken.txt");
        Map<NLInflection, String> doordenkenPrefixedInfl = doordenken.getEntry(0).getDutchVerbInflections();
        Map<NLInflection, String> doordenkenSeparableInfl = doordenken.getEntry(1).getDutchVerbInflections();

        // prefixed entry
        assertEquals("doordenken", doordenkenPrefixedInfl.get(NLInflection.INFINITIVE));
        assertEquals("doordenken", doordenkenPrefixedInfl.get(NLInflection.GERUND));
        assertEquals("doordenken", doordenkenPrefixedInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", doordenkenPrefixedInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", doordenkenPrefixedInfl.get(NLInflection.CLASS));

        assertEquals("doordenk", doordenkenPrefixedInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("doordacht", doordenkenPrefixedInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("doordenkt", doordenkenPrefixedInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("doordacht", doordenkenPrefixedInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("doordenkt", doordenkenPrefixedInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("doordacht", doordenkenPrefixedInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("doordenken", doordenkenPrefixedInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("doordachten", doordenkenPrefixedInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("doordenke", doordenkenPrefixedInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("doordachte", doordenkenPrefixedInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("doordenken", doordenkenPrefixedInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("doordachten", doordenkenPrefixedInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("doordenk", doordenkenPrefixedInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("doordenkt", doordenkenPrefixedInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("doordenkend", doordenkenPrefixedInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("doordacht", doordenkenPrefixedInfl.get(NLInflection.PAST_PARTICIPLE));

        assertEquals("doordenken", doordenkenSeparableInfl.get(NLInflection.INFINITIVE));
        assertEquals("doordenken", doordenkenSeparableInfl.get(NLInflection.GERUND));
        assertEquals("doordenken", doordenkenSeparableInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", doordenkenSeparableInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", doordenkenSeparableInfl.get(NLInflection.CLASS));

        // separable entry
        assertEquals("denk door", doordenkenSeparableInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("dacht door", doordenkenSeparableInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("denkt door", doordenkenSeparableInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("dacht door", doordenkenSeparableInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("denkt door", doordenkenSeparableInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("dacht door", doordenkenSeparableInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("denken door", doordenkenSeparableInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("dachten door", doordenkenSeparableInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("denke door", doordenkenSeparableInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("dachte door", doordenkenSeparableInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("denken door", doordenkenSeparableInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("dachten door", doordenkenSeparableInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("denk door", doordenkenSeparableInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("denkt door", doordenkenSeparableInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("doordenkend", doordenkenSeparableInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("doorgedacht", doordenkenSeparableInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage kopen = parse("kopen.txt");
        Map<NLInflection, String> kopenInfl = kopen.getEntry(0).getDutchVerbInflections();

        assertEquals("kopen", kopenInfl.get(NLInflection.INFINITIVE));
        assertEquals("kopen", kopenInfl.get(NLInflection.GERUND));
        assertEquals("kopen", kopenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", kopenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", kopenInfl.get(NLInflection.CLASS));

        assertEquals("koop", kopenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("kocht", kopenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("koopt", kopenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("kocht", kopenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("koopt", kopenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("kocht", kopenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("kopen", kopenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("kochten", kopenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("kope", kopenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("kochte", kopenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("kopen", kopenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("kochten", kopenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("koop", kopenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("koopt", kopenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("kopend", kopenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gekocht", kopenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage verkopen = parse("verkopen.txt");
        Map<NLInflection, String> verkopenInfl = verkopen.getEntry(0).getDutchVerbInflections();

        assertEquals("verkopen", verkopenInfl.get(NLInflection.INFINITIVE));
        assertEquals("verkopen", verkopenInfl.get(NLInflection.GERUND));
        assertEquals("verkopen", verkopenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", verkopenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", verkopenInfl.get(NLInflection.CLASS));

        assertEquals("verkoop", verkopenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("verkocht", verkopenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("verkoopt", verkopenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("verkocht", verkopenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("verkoopt", verkopenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("verkocht", verkopenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("verkopen", verkopenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("verkochten", verkopenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("verkope", verkopenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("verkochte", verkopenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("verkopen", verkopenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("verkochten", verkopenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("verkoop", verkopenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("verkoopt", verkopenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("verkopend", verkopenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("verkocht", verkopenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage aankopen = parse("aankopen.txt");
        Map<NLInflection, String> aankopenInfl = aankopen.getEntry(0).getDutchVerbInflections();

        assertEquals("aankopen", aankopenInfl.get(NLInflection.INFINITIVE));
        assertEquals("aankopen", aankopenInfl.get(NLInflection.GERUND));
        assertEquals("aankopen", aankopenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", aankopenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", aankopenInfl.get(NLInflection.CLASS));

        assertEquals("koop aan", aankopenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("kocht aan", aankopenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("koopt aan", aankopenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("kocht aan", aankopenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("koopt aan", aankopenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("kocht aan", aankopenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("kopen aan", aankopenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("kochten aan", aankopenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("kope aan", aankopenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("kochte aan", aankopenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("kopen aan", aankopenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("kochten aan", aankopenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("koop aan", aankopenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("koopt aan", aankopenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("aankopend", aankopenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("aangekocht", aankopenInfl.get(NLInflection.PAST_PARTICIPLE));
    }

    public void testIrregularVerbs() throws Exception {
        // hebben
        IWiktionaryPage hebben = parse("hebben.txt");
        Map<NLInflection, String> hebbenInfl = hebben.getEntry(0).getDutchVerbInflections();

        assertEquals("hebben", hebbenInfl.get(NLInflection.INFINITIVE));
        assertEquals("hebben", hebbenInfl.get(NLInflection.GERUND));
        assertEquals("hebben", hebbenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", hebbenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", hebbenInfl.get(NLInflection.CLASS));

        assertEquals("heb", hebbenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("had", hebbenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("hebt", hebbenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("had", hebbenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("heeft", hebbenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("had", hebbenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("hebben", hebbenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("hadden", hebbenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("hebbe", hebbenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("hadde", hebbenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("hebben", hebbenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("hadden", hebbenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("heb", hebbenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("hebt", hebbenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("hebbend", hebbenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gehad", hebbenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage doorhebben = parse("doorhebben.txt");
        Map<NLInflection, String> doorhebbenInfl = doorhebben.getEntry(0).getDutchVerbInflections();

        assertEquals("doorhebben", doorhebbenInfl.get(NLInflection.INFINITIVE));
        assertEquals("doorhebben", doorhebbenInfl.get(NLInflection.GERUND));
        assertEquals("doorhebben", doorhebbenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", doorhebbenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", doorhebbenInfl.get(NLInflection.CLASS));

        assertEquals("heb door", doorhebbenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("had door", doorhebbenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("hebt door", doorhebbenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("had door", doorhebbenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("heeft door", doorhebbenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("had door", doorhebbenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("hebben door", doorhebbenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("hadden door", doorhebbenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("hebbe door", doorhebbenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("hadde door", doorhebbenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("hebben door", doorhebbenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("hadden door", doorhebbenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("heb door", doorhebbenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("hebt door", doorhebbenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("doorhebbend", doorhebbenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("doorgehad", doorhebbenInfl.get(NLInflection.PAST_PARTICIPLE));

        // kunnen
        IWiktionaryPage kunnen = parse("kunnen.txt");
        Map<NLInflection, String> kunnenInfl = kunnen.getEntry(0).getDutchVerbInflections();

        assertEquals("kunnen", kunnenInfl.get(NLInflection.INFINITIVE));
        assertEquals("kunnen", kunnenInfl.get(NLInflection.GERUND));
        assertEquals("kunnen", kunnenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", kunnenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", kunnenInfl.get(NLInflection.CLASS));

        assertEquals("kan", kunnenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("kon", kunnenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("kunt", kunnenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("kon", kunnenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("kan", kunnenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("kon", kunnenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("kunnen", kunnenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("konden", kunnenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("kunne", kunnenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("konde", kunnenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("kunnen", kunnenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("konden", kunnenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("kan", kunnenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("kunt", kunnenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("kunnend", kunnenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gekund", kunnenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage aankunnen = parse("aankunnen.txt");
        Map<NLInflection, String> aankunnenInfl = aankunnen.getEntry(0).getDutchVerbInflections();

        assertEquals("aankunnen", aankunnenInfl.get(NLInflection.INFINITIVE));
        assertEquals("aankunnen", aankunnenInfl.get(NLInflection.GERUND));
        assertEquals("aankunnen", aankunnenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", aankunnenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", aankunnenInfl.get(NLInflection.CLASS));

        assertEquals("kan aan", aankunnenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("kon aan", aankunnenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("kunt aan", aankunnenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("kon aan", aankunnenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("kan aan", aankunnenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("kon aan", aankunnenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("kunnen aan", aankunnenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("konden aan", aankunnenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("kunne aan", aankunnenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("konde aan", aankunnenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("kunnen aan", aankunnenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("konden aan", aankunnenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("kan aan", aankunnenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("kunt aan", aankunnenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("aankunnend", aankunnenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("aangekund", aankunnenInfl.get(NLInflection.PAST_PARTICIPLE));

        // moeten
        IWiktionaryPage moeten = parse("moeten.txt");
        Map<NLInflection, String> moetenInfl = moeten.getEntry(0).getDutchVerbInflections();

        assertEquals("moeten", moetenInfl.get(NLInflection.INFINITIVE));
        assertEquals("moeten", moetenInfl.get(NLInflection.GERUND));
        assertEquals("moeten", moetenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", moetenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", moetenInfl.get(NLInflection.CLASS));

        assertEquals("moet", moetenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("moest", moetenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("moet", moetenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("moest", moetenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("moet", moetenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("moest", moetenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("moeten", moetenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("moesten", moetenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("moete", moetenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("moeste", moetenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("moeten", moetenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("moesten", moetenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("moet", moetenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("moet", moetenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("moetend", moetenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gemoeten", moetenInfl.get(NLInflection.PAST_PARTICIPLE));
        // couldn't find a prefixed/separated verb with moeten

        // mogen
        IWiktionaryPage mogen = parse("mogen.txt");
        Map<NLInflection, String> mogenInfl = mogen.getEntry(0).getDutchVerbInflections();

        assertEquals("mogen", mogenInfl.get(NLInflection.INFINITIVE));
        assertEquals("mogen", mogenInfl.get(NLInflection.GERUND));
        assertEquals("mogen", mogenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", mogenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", mogenInfl.get(NLInflection.CLASS));

        assertEquals("mag", mogenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("mocht", mogenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("mag", mogenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("mocht", mogenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("mag", mogenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("mocht", mogenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("mogen", mogenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("mochten", mogenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("moge", mogenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("mochte", mogenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("mogen", mogenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("mochten", mogenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("mag", mogenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("moogt", mogenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("mogend", mogenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gemogen", mogenInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage vermogen = parse("vermogen.txt");
        Map<NLInflection, String> vermogenInfl = filterByPartOfSpeech(vermogen);

        assertEquals("vermogen", vermogenInfl.get(NLInflection.INFINITIVE));
        assertEquals("vermogen", vermogenInfl.get(NLInflection.GERUND));
        assertEquals("vermogen", vermogenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", vermogenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", vermogenInfl.get(NLInflection.CLASS));

        assertEquals("vermag", vermogenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("vermocht", vermogenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("vermag", vermogenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("vermocht", vermogenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("vermag", vermogenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("vermocht", vermogenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("vermogen", vermogenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("vermochten", vermogenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("vermoge", vermogenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("vermochte", vermogenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("vermogen", vermogenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("vermochten", vermogenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("vermag", vermogenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("vermoogt", vermogenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("vermogend", vermogenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("vermogen", vermogenInfl.get(NLInflection.PAST_PARTICIPLE));

        // weten
        IWiktionaryPage weten = parse("weten.txt");
        Map<NLInflection, String> wetenInfl = weten.getEntry(0).getDutchVerbInflections();

        assertEquals("weten", wetenInfl.get(NLInflection.INFINITIVE));
        assertEquals("weten", wetenInfl.get(NLInflection.GERUND));
        assertEquals("weten", wetenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", wetenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", wetenInfl.get(NLInflection.CLASS));

        assertEquals("weet", wetenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("wist", wetenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("weet", wetenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("wist", wetenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("weet", wetenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("wist", wetenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("weten", wetenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("wisten", wetenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("wete", wetenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("wiste", wetenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("weten", wetenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("wisten", wetenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("weet", wetenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("weet", wetenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("wetend", wetenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("geweten", wetenInfl.get(NLInflection.PAST_PARTICIPLE));
        // couldn't find a prefixed/separated verb with weten too

        // willen
        IWiktionaryPage willen = parse("willen.txt");
        Map<NLInflection, String> willenInfl = willen.getEntry(0).getDutchVerbInflections();

        assertEquals("willen", willenInfl.get(NLInflection.INFINITIVE));
        assertEquals("willen", willenInfl.get(NLInflection.GERUND));
        assertEquals("willen", willenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", willenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", willenInfl.get(NLInflection.CLASS));

        assertEquals("wil", willenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("wilde", willenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("wilt", willenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("wilde", willenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("wil", willenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("wilde", willenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("willen", willenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("wilden", willenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("wille", willenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("wilde", willenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("willen", willenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("wilden", willenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("wil", willenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("wilt", willenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("willend", willenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("gewild", willenInfl.get(NLInflection.PAST_PARTICIPLE));
        // couldn't find a prefixed/separated verb with weten too

        // zijn
        IWiktionaryPage zijn = parse("zijn.txt");
        Map<NLInflection, String> zijnInfl = zijn.getEntry(0).getDutchVerbInflections();

        assertEquals("zijn", zijnInfl.get(NLInflection.INFINITIVE));
        assertEquals("zijn", zijnInfl.get(NLInflection.GERUND));
        assertEquals("zijn", zijnInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("zijn", zijnInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", zijnInfl.get(NLInflection.CLASS));

        assertEquals("ben", zijnInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("was", zijnInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("bent", zijnInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("was", zijnInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("is", zijnInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("was", zijnInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("zijn", zijnInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("waren", zijnInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("zij", zijnInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("ware", zijnInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("zijn", zijnInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("waren", zijnInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("wees", zijnInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("weest", zijnInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("zijnd", zijnInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("geweest", zijnInfl.get(NLInflection.PAST_PARTICIPLE));

        IWiktionaryPage voorzijn = parse("voorzijn.txt");
        Map<NLInflection, String> voorzijnInfl = voorzijn.getEntry(0).getDutchVerbInflections();

        assertEquals("voorzijn", voorzijnInfl.get(NLInflection.INFINITIVE));
        assertEquals("voorzijn", voorzijnInfl.get(NLInflection.GERUND));
        assertEquals("voorzijn", voorzijnInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("zijn", voorzijnInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", voorzijnInfl.get(NLInflection.CLASS));

        assertEquals("ben voor", voorzijnInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("was voor", voorzijnInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("bent voor", voorzijnInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("was voor", voorzijnInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("is voor", voorzijnInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("was voor", voorzijnInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("zijn voor", voorzijnInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("waren voor", voorzijnInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("zij voor", voorzijnInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("ware voor", voorzijnInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("zijn voor", voorzijnInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("waren voor", voorzijnInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("wees voor", voorzijnInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("weest voor", voorzijnInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("voorzijnd", voorzijnInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("voorgeweest", voorzijnInfl.get(NLInflection.PAST_PARTICIPLE));

        // zullen
        IWiktionaryPage zullen = parse("zullen.txt");
        Map<NLInflection, String> zullenInfl = zullen.getEntry(0).getDutchVerbInflections();

        assertEquals("zullen", zullenInfl.get(NLInflection.INFINITIVE));
        assertEquals("zullen", zullenInfl.get(NLInflection.GERUND));
        assertEquals("zullen", zullenInfl.get(NLInflection.VERBAL_NOUN));
        assertEquals("hebben", zullenInfl.get(NLInflection.AUXILIARY_VERB));
        assertEquals("", zullenInfl.get(NLInflection.CLASS));

        assertEquals("zal", zullenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PRESENT));
        assertEquals("zou", zullenInfl.get(NLInflection.FIRST_PERSON_SINGULAR_PAST));

        assertEquals("zult", zullenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PRESENT));
        assertEquals("zou", zullenInfl.get(NLInflection.SECOND_PERSON_SINGULAR_PAST));

        assertEquals("zal", zullenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PRESENT));
        assertEquals("zou", zullenInfl.get(NLInflection.THIRD_PERSON_SINGULAR_PAST));

        assertEquals("zullen", zullenInfl.get(NLInflection.PLURAL_PRESENT));
        assertEquals("zouden", zullenInfl.get(NLInflection.PLURAL_PAST));

        assertEquals("zulle", zullenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT));
        assertEquals("zoude", zullenInfl.get(NLInflection.SUBJUNCTIVE_SINGULAR_PAST));

        assertEquals("zullen", zullenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT));
        assertEquals("zouden", zullenInfl.get(NLInflection.SUBJUNCTIVE_PLURAL_PAST));

        assertEquals("zal", zullenInfl.get(NLInflection.IMPERATIVE_SINGULAR));
        assertEquals("zult", zullenInfl.get(NLInflection.IMPERATIVE_PLURAL));

        assertEquals("zullend", zullenInfl.get(NLInflection.PRESENT_PARTICIPLE));
        assertEquals("", zullenInfl.get(NLInflection.PAST_PARTICIPLE));
    }

}
