package de.tudarmstadt.ukp.jwktl.parser.en.components;

import de.tudarmstadt.ukp.jwktl.api.entry.WiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.util.NLInflection;
import de.tudarmstadt.ukp.jwktl.api.util.TemplateParser;
import de.tudarmstadt.ukp.jwktl.parser.util.ParsingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * For Dutch verbs, finds the inflections block and parses it.
 * This follows Wiktionary documentation, and *does* make assumptions based on what it permits.
 *
 * Created on 25/08/2017
 * @author tbsc
 */
public class ENDutchInflectionsHandler extends ENBlockHandler implements TemplateParser.ITemplateHandler {

    private Pattern VERB_PATTERN = Pattern.compile("\\A\\{\\{nl-conj-");
    private Map<NLInflection, String> inflections;

    public ENDutchInflectionsHandler() {
        super("Inflection");
    }

    @Override
    public boolean processHead(String text, ParsingContext context) {
        inflections = new HashMap<>();
        return super.processHead(text, context);
    }

    @Override
    public boolean processBody(String line, ParsingContext context) {
        if (VERB_PATTERN.matcher(line).find()) {
            TemplateParser.parse(line, this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String handle(TemplateParser.Template template) {
        if (template.getName().equals("nl-conj-wk")) {
            handleWeakVerbTemplate(template);
        } else if (template.getName().equals("nl-conj-st")) {
            handleStrongVerbTemplate(template);
        } else if (template.getName().equals("nl-conj-irr")) {
            handleIrregularVerbTemplate(template);
        } else if (template.getName().equals("nl-conj-wk-cht")) {
            handleChtWeakVerbTemplate(template);
        }
        return null;
    }

    // TODO: separable verb based on clause (look at doorvertellen)

    private void handleWeakVerbTemplate(TemplateParser.Template template) {
        // required param, null checking isn't needed
        String stem = template.getNumberedParam(0);

        String presentSubjunctiveStem;
        String presentSubjunctiveSuffix = "e";
        if (template.getNumberedParam(1) != null) {
            presentSubjunctiveStem = template.getNumberedParam(1);
            presentSubjunctiveSuffix = "";
        } else {
            presentSubjunctiveStem = stem;
        }

        // default is hebben
        String auxiliaryVerb = "hebben";
        if (template.getNamedParam("aux") != null) {
            auxiliaryVerb = template.getNamedParam("aux");
        }

        // default is nothing
        String prefix = "";
        if (template.getNamedParam("pref") != null) {
            prefix = template.getNamedParam("pref");
        }

        // default is nothing
        String separable = "";
        if (template.getNamedParam("sep") != null) {
            separable = " " + template.getNamedParam("sep");
        }

        // stem means this doesn't include a prefix or a suffix
        // this is NOT the fully inflected verb, and should not be directly added to the map!
        String pastParticipleStem;
        if (template.getNumberedParam(2) != null) {
            pastParticipleStem = template.getNumberedParam(2);
        } else {
            pastParticipleStem = stem;
        }

        // either "d" or "t"
        String suffix;
        if (template.getNamedParam("dt") != null) {
            suffix = template.getNamedParam("dt");
        } else {
            suffix = applytKofschip(stem);
        }
        String pastSimpleSuffix = suffix + "e";

        // got all needed data, start inflecting verbs

        // also the gerund and verbal noun (in weak verbs)
        String infinitive = separable + prefix + stem + "en";

        String firstPersonSingularPresent = prefix + stem + separable;
        String firstPersonSingularPast = prefix + stem + pastSimpleSuffix + separable;
        // 2nd and 3rd singular present for weak verbs is the same
        String secondThirdPersonSingularPresent = prefix + stem + "t" + separable;
        String secondThirdPersonSingularPast = prefix + stem + pastSimpleSuffix + separable;

        String pluralPresent = prefix + presentSubjunctiveStem + "n" + separable;
        String pluralPast = prefix + stem + pastSimpleSuffix + "n" + separable;

        String subjunctiveSingularPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + separable;
        String subjunctiveSingularPast = prefix + stem + suffix + "e" + separable;
        String subjunctivePluralPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n" + separable;
        String subjunctivePluralPast = prefix + stem + suffix + "en" + separable;

        String imperativeSingular = prefix + stem + separable;
        String imperativePlural = prefix + stem + "t" + separable;

        String pastParticiple = separable.trim() + prefix + stem + suffix;
        String presentParticiple = separable.trim() + prefix + pastParticipleStem + "n" + suffix;

        // after creating all needed inflections, add them to map

        // general
        addInfl(NLInflection.INFINITIVE, infinitive);
        addInfl(new NLInflection(NLInflection.Type.GERUND), infinitive);
        addInfl(new NLInflection(NLInflection.Type.VERBAL_NOUN), infinitive);
        // I know this isn't an inflection, but it should still be added
        addInfl(new NLInflection(NLInflection.Type.AUXILIARY_VERB), auxiliaryVerb);

        // 1st person
        addInfl(NLInflection.STEM, firstPersonSingularPresent);
        addInfl(new NLInflection(NLInflection.Type.FIRST_PERSON, NLInflection.Number.SINGULAR, NLInflection.Tense.PAST),
                firstPersonSingularPast);
        // 2nd person
        addInfl(new NLInflection(NLInflection.Type.SECOND_PERSON, NLInflection.Number.SINGULAR, NLInflection.Tense.PRESENT),
                secondThirdPersonSingularPresent);
        addInfl(new NLInflection(NLInflection.Type.SECOND_PERSON, NLInflection.Number.SINGULAR, NLInflection.Tense.PAST),
                secondThirdPersonSingularPast);
        // 3rd person
        addInfl(new NLInflection(NLInflection.Type.THIRD_PERSON, NLInflection.Number.SINGULAR, NLInflection.Tense.PRESENT),
                secondThirdPersonSingularPresent);
        addInfl(new NLInflection(NLInflection.Type.THIRD_PERSON, NLInflection.Number.SINGULAR, NLInflection.Tense.PAST),
                secondThirdPersonSingularPast);

        // plural
        addInfl(new NLInflection(NLInflection.Number.PLURAL, NLInflection.Tense.PRESENT), pluralPresent);
        addInfl(new NLInflection(NLInflection.Number.PLURAL, NLInflection.Tense.PAST), pluralPast);

        // subjunctive
        addInfl(new NLInflection(NLInflection.Type.SUBJUNCTIVE, NLInflection.Number.SINGULAR, NLInflection.Tense.PRESENT),
                subjunctiveSingularPresent);
        addInfl(new NLInflection(NLInflection.Type.SUBJUNCTIVE, NLInflection.Number.SINGULAR, NLInflection.Tense.PAST),
                subjunctiveSingularPast);
        addInfl(new NLInflection(NLInflection.Type.SUBJUNCTIVE, NLInflection.Number.PLURAL, NLInflection.Tense.PRESENT),
                subjunctivePluralPresent);
        addInfl(new NLInflection(NLInflection.Type.SUBJUNCTIVE, NLInflection.Number.PLURAL, NLInflection.Tense.PAST),
                subjunctivePluralPast);

        // imperative
        addInfl(new NLInflection(NLInflection.Type.IMPERATIVE, NLInflection.Number.SINGULAR, NLInflection.Tense.PRESENT),
                imperativeSingular);
        addInfl(new NLInflection(NLInflection.Type.IMPERATIVE, NLInflection.Number.PLURAL, NLInflection.Tense.PRESENT),
                imperativePlural);

        // participle
        addInfl(new NLInflection(NLInflection.Type.PARTICIPLE, NLInflection.Tense.PRESENT), presentParticiple);
        addInfl(NLInflection.PAST_PARTICIPLE, pastParticiple);
    }

    private void handleStrongVerbTemplate(TemplateParser.Template template) {

    }

    private void handleIrregularVerbTemplate(TemplateParser.Template template) {

    }

    private void handleChtWeakVerbTemplate(TemplateParser.Template template) {

    }

    /**
     * Checks which suffix a stem should have.
     * @param stem The verb stem to check
     * @return either "d" or "t", based on the stem and which of these should be used
     */
    private String applytKofschip(String stem) {
        if (stem.endsWith("t")
                || stem.endsWith("k")
                || stem.endsWith("f")
                || stem.endsWith("s")
                || stem.endsWith("ch")
                || stem.endsWith("p")) {
            return "t";
        } else {
            return "d";
        }
    }

    /**
     * For having shorter statements.
     * @param infl Key
     * @param value The value (inflected verb)
     */
    private void addInfl(NLInflection infl, String value) {
        inflections.put(infl, value);
    }

    @Override
    public void fillContent(ParsingContext context) {
        WiktionaryEntry entry = context.findEntry();
        if (entry == null) {
            throw new RuntimeException("entry is null");
        }
        entry.setDutchVerbInflections(inflections);
        super.fillContent(context);
    }

}
