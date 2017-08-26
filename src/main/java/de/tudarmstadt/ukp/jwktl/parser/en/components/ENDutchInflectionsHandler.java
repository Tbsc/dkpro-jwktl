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

    // TODO: add support for showing separable verbs in both of their forms (main/subordniate clause)

    private void handleWeakVerbTemplate(TemplateParser.Template template) {
        // required param, null checking isn't needed
        String stem = template.getNumberedParam(0);

        String presentSubjunctiveStem;
        String presentSubjunctiveSuffix = "e";
        if (template.getNumberedParamsCount() > 1) {
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
        // past participle prefix should be ge-, or any other provided prefix
        String pastParticiplePrefix;
        if ("".equals(prefix)) {
            pastParticiplePrefix = "ge";
        } else {
            pastParticiplePrefix = prefix;
        }

        // default is nothing
        String separable = "";
        if (template.getNamedParam("sep") != null) {
            separable = " " + template.getNamedParam("sep");
        }

        // either "d" or "t"
        String suffix;
        if (template.getNamedParam("dt") != null) {
            suffix = template.getNamedParam("dt");
        } else {
            suffix = applytKofschip(stem);
        }
        String pastSimpleSuffix = suffix + "e";
        String pastParticipleSuffix = suffix;

        // stem means this doesn't include a prefix or a suffix
        // this is NOT the fully inflected verb, and should not be directly added to the map!
        String pastParticipleStem;
        if (template.getNumberedParamsCount() > 2) {
            pastParticipleStem = template.getNumberedParam(2);
            pastParticipleSuffix = "";
        } else {
            pastParticipleStem = stem;
        }

        // got all needed data, start inflecting verbs

        // infinitive is also the gerund and verbal noun (in weak verbs)
        String infinitive = separable.trim() + prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n";

        String firstPersonSingularPresent = prefix + stem + separable;

        // 2nd and 3rd singular present for weak verbs is the same
        String secondThirdPersonSingularPresent = prefix + stem + "t" + separable;

        // singular past is the same for all PoVs
        String singularPast = prefix + stem + pastSimpleSuffix + separable;

        // plural is the same for all PoVs
        String pluralPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n" + separable;
        String pluralPast = prefix + stem + pastSimpleSuffix + "n" + separable;

        String subjunctiveSingularPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + separable;
        String subjunctiveSingularPast = prefix + stem + suffix + "e" + separable;
        String subjunctivePluralPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n" + separable;
        String subjunctivePluralPast = prefix + stem + suffix + "en" + separable;

        String imperativeSingular = prefix + stem + separable;
        String imperativePlural = prefix + stem + "t" + separable;

        String presentParticiple = separable.trim() + prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n" + suffix;
        String pastParticiple = separable.trim() + pastParticiplePrefix + pastParticipleStem + pastParticipleSuffix;

        // after creating all needed inflections, add them to map

        // general
        addInfl(NLInflection.INFINITIVE, infinitive);
        addInfl(NLInflection.GERUND, infinitive);
        addInfl(NLInflection.VERBAL_NOUN, infinitive);
        // I know this isn't an inflection, but it should still be added
        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
        // weak verbs never have a class
        addInfl(NLInflection.CLASS, "");

        // 1st person
        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, firstPersonSingularPresent);
        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, singularPast);
        // 2nd person
        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, secondThirdPersonSingularPresent);
        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, singularPast);
        // 3rd person
        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, secondThirdPersonSingularPresent);
        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, singularPast);

        // plural
        addInfl(NLInflection.PLURAL_PRESENT, pluralPresent);
        addInfl(NLInflection.PLURAL_PAST, pluralPast);

        // subjunctive
        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, subjunctiveSingularPresent);
        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, subjunctiveSingularPast);
        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, subjunctivePluralPresent);
        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, subjunctivePluralPast);

        // imperative
        addInfl(NLInflection.IMPERATIVE_SINGULAR, imperativeSingular);
        addInfl(NLInflection.IMPERATIVE_PLURAL, imperativePlural);

        // participle
        addInfl(NLInflection.PRESENT_PARTICIPLE, presentParticiple);
        addInfl(NLInflection.PAST_PARTICIPLE, pastParticiple);
    }

    private void handleStrongVerbTemplate(TemplateParser.Template template) {
        // all these are required params, so no null-checking is needed
        String stem = template.getNumberedParam(0);
        String pastStem = template.getNumberedParam(1);
        String pastParticipleStem = template.getNumberedParam(2);

        // get verb class, if exists (sometimes there just isn't!)
        String verbClass = "";
        if (template.getNamedParam("class") != null) {
            verbClass = template.getNamedParam("class");
        }

        String presentSubjunctiveStem = stem;
        String presentSubjunctiveSuffix = "e";
        if (template.getNumberedParamsCount() > 3) {
            presentSubjunctiveStem = template.getNumberedParam(3);
            presentSubjunctiveSuffix = "";

            // sometimes the param count IS bigger than 3 but it's empty
            // that's mostly done for entering the next numbered param without this
            // when this happens this invalid param should be ignored
            if ("".equals(presentSubjunctiveStem)) {
                presentSubjunctiveStem = stem;
                presentSubjunctiveSuffix = "e";
            }
        }

        String pastSubjunctiveStem = pastStem;
        String pastSubjunctiveSuffix = "e";
        if (template.getNumberedParamsCount() > 4) {
            pastSubjunctiveStem = template.getNumberedParam(4);
            pastSubjunctiveSuffix = "";
        }

        String auxiliaryVerb = "hebben";
        if (template.getNamedParam("aux") != null) {
            auxiliaryVerb = template.getNamedParam("aux");
        }

        String prefix = "";
        if (template.getNamedParam("pref") != null) {
            prefix = template.getNamedParam("pref");
        }
        // past participle prefix should be ge-, or any other provided prefix
        String pastParticiplePrefix = prefix;
        if ("".equals(prefix)) {
            pastParticiplePrefix = "ge";
        }

        String separable = "";
        if (template.getNamedParam("sep") != null) {
            separable = " " + template.getNamedParam("sep");
        }

        // got all needed data, start inflecting verbs

        // infinitive is also the gerund and verbal noun (in weak verbs)
        String infinitive = separable.trim() + prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n";

        String firstPersonSingularPresent = prefix + stem + separable;

        // 2nd and 3rd singular present for weak verbs is the same
        String secondThirdPersonSingularPresent = prefix + stem + "t" + separable;

        // singular past is the same for all PoVs
        String singularPast = prefix + pastStem + separable;

        // plural is the same for all PoVs
        String pluralPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n" + separable;
        String pluralPast = prefix + pastSubjunctiveStem + pastSubjunctiveSuffix + "n" + separable;

        String subjunctiveSingularPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + separable;
        String subjunctiveSingularPast = prefix + pastSubjunctiveStem + pastSubjunctiveSuffix + separable;
        String subjunctivePluralPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n" + separable;
        String subjunctivePluralPast = prefix + pastSubjunctiveStem + pastSubjunctiveSuffix + "n" + separable;

        String imperativeSingular = prefix + stem + separable;
        String imperativePlural = prefix + stem + "t" + separable;

        String presentParticiple = separable.trim() + prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "nd";
        String pastParticiple = separable.trim() + pastParticiplePrefix + pastParticipleStem;

        // after creating all needed inflections, add them to map

        // general
        addInfl(NLInflection.INFINITIVE, infinitive);
        addInfl(NLInflection.GERUND, infinitive);
        addInfl(NLInflection.VERBAL_NOUN, infinitive);
        // I know this isn't an inflection, but it should still be added
        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
        addInfl(NLInflection.CLASS, verbClass);

        // 1st person
        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, firstPersonSingularPresent);
        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, singularPast);
        // 2nd person
        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, secondThirdPersonSingularPresent);
        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, singularPast);
        // 3rd person
        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, secondThirdPersonSingularPresent);
        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, singularPast);

        // plural
        addInfl(NLInflection.PLURAL_PRESENT, pluralPresent);
        addInfl(NLInflection.PLURAL_PAST, pluralPast);

        // subjunctive
        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, subjunctiveSingularPresent);
        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, subjunctiveSingularPast);
        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, subjunctivePluralPresent);
        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, subjunctivePluralPast);

        // imperative
        addInfl(NLInflection.IMPERATIVE_SINGULAR, imperativeSingular);
        addInfl(NLInflection.IMPERATIVE_PLURAL, imperativePlural);

        // participle
        addInfl(NLInflection.PRESENT_PARTICIPLE, presentParticiple);
        addInfl(NLInflection.PAST_PARTICIPLE, pastParticiple);
    }

    private void handleIrregularVerbTemplate(TemplateParser.Template template) {
        // irregular verbs are rarely used, and sadly require hardcoding it
        // the irregular verbs are: hebben, kunnen, moeten, mogen, weten, willen, zijn, zullen
        // any derivatives (these irregular verbs with a separable adverb or a prefix) are also handled here
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
