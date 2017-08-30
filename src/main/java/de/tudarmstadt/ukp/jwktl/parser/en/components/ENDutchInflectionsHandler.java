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
            suffix = tKofschip(stem);
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
        String pastParticiple = separable.trim() + pastParticiplePrefix + pastParticipleStem;
        // if the past participle stem already ends with the suffix, you don't add it
        if (!pastParticiple.endsWith(pastParticipleSuffix)) {
            pastParticiple += pastParticipleSuffix;
        }

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

    private void handleChtWeakVerbTemplate(TemplateParser.Template template) {
        // required param, null checking isn't needed
        String stem = template.getNumberedParam(0);
        String pastWithoutCht = template.getNumberedParam(2);
        String pastWithCht = pastWithoutCht + "cht";

        String presentSubjunctiveStem;
        String presentSubjunctiveSuffix = "e";
        // <rant>
        // someone decided it'd be a good idea to have a required param, then an OPTIONAL param, then another required one.
        // I don't know what made them think that's a good idea, because now I need to make different checks
        // </rant>
        if (!"".equals(template.getNumberedParam(1))) {
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

        // got all needed data, start inflecting verbs

        // infinitive is also the gerund and verbal noun (in weak verbs)
        String infinitive = separable.trim() + prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n";

        String firstPersonSingularPresent = prefix + stem + separable;

        // 2nd and 3rd singular present for weak verbs is the same
        String secondThirdPersonSingularPresent = prefix + stem + "t" + separable;

        // singular past is the same for all PoVs
        String singularPast = prefix + pastWithCht + separable;

        // plural is the same for all PoVs
        String pluralPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n" + separable;
        String pluralPast = prefix + pastWithCht + "en" + separable;

        String subjunctiveSingularPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + separable;
        String subjunctiveSingularPast = prefix + pastWithCht + "e" + separable;
        String subjunctivePluralPresent = prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "n" + separable;
        String subjunctivePluralPast = prefix + pastWithCht + "en" + separable;

        String imperativeSingular = prefix + stem + separable;
        String imperativePlural = prefix + stem + "t" + separable;

        String presentParticiple = separable.trim() + prefix + presentSubjunctiveStem + presentSubjunctiveSuffix + "nd";
        String pastParticiple = separable.trim() + pastParticiplePrefix + pastWithCht;

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

    private void handleIrregularVerbTemplate(TemplateParser.Template template) {
        // irregular verbs are rarely used, and sadly require hardcoding it
        // the irregular verbs are: hebben, kunnen, moeten, mogen, weten, willen, zijn, zullen
        // any derivatives (these irregular verbs with a separable adverb or a prefix) are also handled here
        // even though values are hard coded, tests are still needed due to sub-verbs (doorhebben, vermogen, etc.)

        String prefix = "";
        String pastParticiplePrefix = "ge";
        if (template.getNamedParam("pref") != null) {
            prefix = template.getNamedParam("pref");
            pastParticiplePrefix = prefix;
        }

        String separable = "";
        if (template.getNamedParam("sep") != null) {
            separable = " " + template.getNamedParam("sep");
        }

        String auxiliaryVerb = "hebben";
        if (template.getNamedParam("aux") != null) {
            auxiliaryVerb = template.getNamedParam("aux");
        }

        switch (template.getNumberedParam(0).toLowerCase()) {
            case "hebben":
                addIrregularHebben(prefix, pastParticiplePrefix, separable, auxiliaryVerb);
                break;
            case "kunnen":
                addIrregularKunnen(prefix, pastParticiplePrefix, separable, auxiliaryVerb);
                break;
            case "moeten":
                addIrregularMoeten(prefix, pastParticiplePrefix, separable, auxiliaryVerb);
                break;
            case "mogen":
                addIrregularMogen(prefix, pastParticiplePrefix, separable, auxiliaryVerb);
                break;
            case "weten":
                addIrregularWeten(prefix, pastParticiplePrefix, separable, auxiliaryVerb);
                break;
            case "willen":
                addIrregularWillen(prefix, pastParticiplePrefix, separable, auxiliaryVerb);
                break;
            case "zijn":
                addIrregularZijn(prefix, pastParticiplePrefix, separable, auxiliaryVerb);
                break;
            case "zullen":
                addIrregularZullen(prefix, pastParticiplePrefix, separable, auxiliaryVerb);
                break;
        }
    }

    /**
     * A lot of parameters, but this is the best way to do this.
     * pastParticiple can be null, if the end value should be an empty string (specifically zullen)
     */
    private void addIrregularVerb(String prefix, String pastParticiplePrefix, String separable, String auxiliaryVerb,
                                  String infinitive,
                                  String firstPersonSingularPresent, String firstPersonSingularPast,
                                  String secondPersonSingularPresent, String secondPersonSingularPast,
                                  String thirdPersonSingularPresent, String thirdPersonSingularPast,
                                  String pluralPresent, String pluralPast,
                                  String subjunctiveSingularPresent, String subjunctiveSingularPast,
                                  String subjunctivePluralPresent, String subjunctivePluralPast,
                                  String imperativeSingular, String imperativePlural,
                                  String presentParticiple, String pastParticiple) {
        String separableTrimmed = separable.trim();

        // general
        addInfl(NLInflection.INFINITIVE, separableTrimmed + prefix + infinitive);
        addInfl(NLInflection.GERUND, separableTrimmed + prefix + infinitive);
        addInfl(NLInflection.VERBAL_NOUN, separableTrimmed + prefix + infinitive);
        // I know this isn't an inflection, but it should still be added
        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
        addInfl(NLInflection.CLASS, "");

        // 1st person
        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, prefix + firstPersonSingularPresent + separable);
        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, prefix + firstPersonSingularPast + separable);
        // 2nd person
        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, prefix + secondPersonSingularPresent + separable);
        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, prefix + secondPersonSingularPast + separable);
        // 3rd person
        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, prefix + thirdPersonSingularPresent + separable);
        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, prefix + thirdPersonSingularPast + separable);

        // plural
        addInfl(NLInflection.PLURAL_PRESENT, prefix + pluralPresent + separable);
        addInfl(NLInflection.PLURAL_PAST, prefix + pluralPast + separable);

        // subjunctive
        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, prefix + subjunctiveSingularPresent + separable);
        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, prefix + subjunctiveSingularPast + separable);
        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, prefix + subjunctivePluralPresent + separable);
        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, prefix + subjunctivePluralPast + separable);

        // imperative
        addInfl(NLInflection.IMPERATIVE_SINGULAR, prefix + imperativeSingular + separable);
        addInfl(NLInflection.IMPERATIVE_PLURAL, prefix + imperativePlural + separable);

        // participle
        addInfl(NLInflection.PRESENT_PARTICIPLE, separableTrimmed + prefix + presentParticiple);
        addInfl(NLInflection.PAST_PARTICIPLE, pastParticiple != null ? separableTrimmed + pastParticiplePrefix + pastParticiple : "");
    }

    private void addIrregularHebben(String prefix, String pastParticiplePrefix, String separable, String auxiliaryVerb) {
        addIrregularVerb(prefix, pastParticiplePrefix, separable, auxiliaryVerb,
                "hebben",
                "heb", "had",
                "hebt", "had",
                "heeft", "had",
                "hebben", "hadden",
                "hebbe", "hadde",
                "hebben", "hadden",
                "heb", "hebt",
                "hebbend", "had");
//        String separableTrimmed = separable.trim();
//
//        // general
//        addInfl(NLInflection.INFINITIVE, separableTrimmed + prefix + "hebben");
//        addInfl(NLInflection.GERUND, separableTrimmed + prefix + "hebben");
//        addInfl(NLInflection.VERBAL_NOUN, separableTrimmed + prefix + "hebben");
//        // I know this isn't an inflection, but it should still be added
//        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
//        addInfl(NLInflection.CLASS, "");
//
//        // 1st person
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, prefix + "heb" + separable);
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, prefix + "had" + separable);
//        // 2nd person
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, prefix + "hebt" + separable);
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, prefix + "had" + separable);
//        // 3rd person
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, prefix + "heeft" + separable);
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, prefix + "had" + separable);
//
//        // plural
//        addInfl(NLInflection.PLURAL_PRESENT, prefix + "hebben" + separable);
//        addInfl(NLInflection.PLURAL_PAST, prefix + "hadden" + separable);
//
//        // subjunctive
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, prefix + "hebbe" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, prefix + "hadde" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, prefix + "hebben" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, prefix + "hadden" + separable);
//
//        // imperative
//        addInfl(NLInflection.IMPERATIVE_SINGULAR, prefix + "heb" + separable);
//        addInfl(NLInflection.IMPERATIVE_PLURAL, prefix + "hebt" + separable);
//
//        // participle
//        addInfl(NLInflection.PRESENT_PARTICIPLE, separableTrimmed + prefix + "hebbend");
//        addInfl(NLInflection.PAST_PARTICIPLE, separableTrimmed + prefix + "had");
    }

    private void addIrregularKunnen(String prefix, String pastParticiplePrefix, String separable, String auxiliaryVerb) {
        addIrregularVerb(prefix, pastParticiplePrefix, separable, auxiliaryVerb,
                "kunnen",
                "kan", "kon",
                "kunt", "kon",
                "kan", "kon",
                "kunnen", "konden",
                "kunne", "konde",
                "kunnen", "konden",
                "kan", "kunt",
                "kunnend", "kund");

//        String separableTrimmed = separable.trim();
//
//        // general
//        addInfl(NLInflection.INFINITIVE, separableTrimmed + prefix + "kunnen");
//        addInfl(NLInflection.GERUND, separableTrimmed + prefix + "kunnen");
//        addInfl(NLInflection.VERBAL_NOUN, separableTrimmed + prefix + "kunnen");
//        // I know this isn't an inflection, but it should still be added
//        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
//        addInfl(NLInflection.CLASS, "");
//
//        // 1st person
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, prefix + "kan" + separable);
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, prefix + "kon" + separable);
//        // 2nd person
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, prefix + "kunt" + separable);
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, prefix + "kon" + separable);
//        // 3rd person
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, prefix + "kan" + separable);
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, prefix + "kon" + separable);
//
//        // plural
//        addInfl(NLInflection.PLURAL_PRESENT, prefix + "kunnen" + separable);
//        addInfl(NLInflection.PLURAL_PAST, prefix + "konden" + separable);
//
//        // subjunctive
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, prefix + "kunne" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, prefix + "konde" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, prefix + "kunnen" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, prefix + "konden" + separable);
//
//        // imperative
//        addInfl(NLInflection.IMPERATIVE_SINGULAR, prefix + "kan" + separable);
//        addInfl(NLInflection.IMPERATIVE_PLURAL, prefix + "kunt" + separable);
//
//        // participle
//        addInfl(NLInflection.PRESENT_PARTICIPLE, separableTrimmed + prefix + "kunnend");
//        addInfl(NLInflection.PAST_PARTICIPLE, separableTrimmed + prefix + "kund");
    }

    private void addIrregularMoeten(String prefix, String pastParticiplePrefix, String separable, String auxiliaryVerb) {
        addIrregularVerb(prefix, pastParticiplePrefix, separable, auxiliaryVerb,
                "moeten",
                "moet", "moest",
                "moet", "moest",
                "moet", "moest",
                "moeten", "moesten",
                "moete", "moeste",
                "moeten", "moesten",
                "moet", "moet",
                "moetend", "moeten");

//        String separableTrimmed = separable.trim();
//
//        // general
//        addInfl(NLInflection.INFINITIVE, separableTrimmed + prefix + "moeten");
//        addInfl(NLInflection.GERUND, separableTrimmed + prefix + "moeten");
//        addInfl(NLInflection.VERBAL_NOUN, separableTrimmed + prefix + "moeten");
//        // I know this isn't an inflection, but it should still be added
//        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
//        addInfl(NLInflection.CLASS, "");
//
//        // 1st person
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, prefix + "moet" + separable);
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, prefix + "moest" + separable);
//        // 2nd person
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, prefix + "moet" + separable);
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, prefix + "moest" + separable);
//        // 3rd person
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, prefix + "moet" + separable);
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, prefix + "moest" + separable);
//
//        // plural
//        addInfl(NLInflection.PLURAL_PRESENT, prefix + "moeten" + separable);
//        addInfl(NLInflection.PLURAL_PAST, prefix + "moesten" + separable);
//
//        // subjunctive
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, prefix + "moete" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, prefix + "moeste" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, prefix + "moeten" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, prefix + "moesten" + separable);
//
//        // imperative
//        addInfl(NLInflection.IMPERATIVE_SINGULAR, prefix + "moet" + separable);
//        addInfl(NLInflection.IMPERATIVE_PLURAL, prefix + "moet" + separable);
//
//        // participle
//        addInfl(NLInflection.PRESENT_PARTICIPLE, separableTrimmed + prefix + "moetend");
//        addInfl(NLInflection.PAST_PARTICIPLE, separableTrimmed + prefix + "moeten");
    }

    private void addIrregularMogen(String prefix, String pastParticiplePrefix, String separable, String auxiliaryVerb) {
        addIrregularVerb(prefix, pastParticiplePrefix, separable, auxiliaryVerb,
                "mogen",
                "mag", "mocht",
                "mag", "mocht",
                "mag", "mocht",
                "mogen", "mochten",
                "moge", "mochte",
                "mogen", "mochten",
                "mag", "moogt",
                "mogend", "mogen");

//        String separableTrimmed = separable.trim();
//
//        // general
//        addInfl(NLInflection.INFINITIVE, separableTrimmed + prefix + "mogen");
//        addInfl(NLInflection.GERUND, separableTrimmed + prefix + "mogen");
//        addInfl(NLInflection.VERBAL_NOUN, separableTrimmed + prefix + "mogen");
//        // I know this isn't an inflection, but it should still be added
//        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
//        addInfl(NLInflection.CLASS, "");
//
//        // 1st person
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, prefix + "mag" + separable);
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, prefix + "mocht" + separable);
//        // 2nd person
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, prefix + "mag" + separable);
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, prefix + "mocht" + separable);
//        // 3rd person
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, prefix + "mag" + separable);
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, prefix + "mocht" + separable);
//
//        // plural
//        addInfl(NLInflection.PLURAL_PRESENT, prefix + "mogen" + separable);
//        addInfl(NLInflection.PLURAL_PAST, prefix + "mochten" + separable);
//
//        // subjunctive
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, prefix + "moge" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, prefix + "mochte" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, prefix + "mogen" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, prefix + "mochten" + separable);
//
//        // imperative
//        addInfl(NLInflection.IMPERATIVE_SINGULAR, prefix + "mag" + separable);
//        addInfl(NLInflection.IMPERATIVE_PLURAL, prefix + "moogt" + separable);
//
//        // participle
//        addInfl(NLInflection.PRESENT_PARTICIPLE, separableTrimmed + prefix + "mogend");
//        addInfl(NLInflection.PAST_PARTICIPLE, separableTrimmed + prefix + "mogen");
    }

    private void addIrregularWeten(String prefix, String pastParticiplePrefix, String separable, String auxiliaryVerb) {
        addIrregularVerb(prefix, pastParticiplePrefix, separable, auxiliaryVerb,
                "weten",
                "weet", "wist",
                "weet", "wist",
                "weet", "wist",
                "weten", "wisten",
                "wete", "wiste",
                "weten", "wisten",
                "weet", "weet",
                "wetend", "weten");

//        String separableTrimmed = separable.trim();
//
//        // general
//        addInfl(NLInflection.INFINITIVE, separableTrimmed + prefix + "weten");
//        addInfl(NLInflection.GERUND, separableTrimmed + prefix + "weten");
//        addInfl(NLInflection.VERBAL_NOUN, separableTrimmed + prefix + "weten");
//        // I know this isn't an inflection, but it should still be added
//        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
//        addInfl(NLInflection.CLASS, "");
//
//        // 1st person
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, prefix + "weet" + separable);
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, prefix + "wist" + separable);
//        // 2nd person
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, prefix + "weet" + separable);
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, prefix + "wist" + separable);
//        // 3rd person
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, prefix + "weet" + separable);
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, prefix + "wist" + separable);
//
//        // plural
//        addInfl(NLInflection.PLURAL_PRESENT, prefix + "weten" + separable);
//        addInfl(NLInflection.PLURAL_PAST, prefix + "wisten" + separable);
//
//        // subjunctive
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, prefix + "wete" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, prefix + "wiste" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, prefix + "weten" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, prefix + "wisten" + separable);
//
//        // imperative
//        addInfl(NLInflection.IMPERATIVE_SINGULAR, prefix + "weet" + separable);
//        addInfl(NLInflection.IMPERATIVE_PLURAL, prefix + "weet" + separable);
//
//        // participle
//        addInfl(NLInflection.PRESENT_PARTICIPLE, separableTrimmed + prefix + "wetend");
//        addInfl(NLInflection.PAST_PARTICIPLE, separableTrimmed + prefix + "weten");
    }

    private void addIrregularWillen(String prefix, String pastParticiplePrefix, String separable, String auxiliaryVerb) {
        addIrregularVerb(prefix, pastParticiplePrefix, separable, auxiliaryVerb,
                "willen",
                "wil", "wilde",
                "wilt", "wilde",
                "wil", "wilde",
                "willen", "wilden",
                "wille", "wilde",
                "willen", "wilden",
                "wil", "wilt",
                "willend", "wild");

//        String separableTrimmed = separable.trim();
//
//        // general
//        addInfl(NLInflection.INFINITIVE, separableTrimmed + prefix + "willen");
//        addInfl(NLInflection.GERUND, separableTrimmed + prefix + "willen");
//        addInfl(NLInflection.VERBAL_NOUN, separableTrimmed + prefix + "willen");
//        // I know this isn't an inflection, but it should still be added
//        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
//        addInfl(NLInflection.CLASS, "");
//
//        // NOTE: instead of wou I decided to use wilde
//
//        // 1st person
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, prefix + "wil" + separable);
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, prefix + "wilde" + separable);
//        // 2nd person
//        // here also, "wil" can also be used but I decided to use "wilt" because it's the formal option
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, prefix + "wilt" + separable);
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, prefix + "wilde" + separable);
//        // 3rd person
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, prefix + "wil" + separable);
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, prefix + "wilde" + separable);
//
//        // plural
//        addInfl(NLInflection.PLURAL_PRESENT, prefix + "willen" + separable);
//        addInfl(NLInflection.PLURAL_PAST, prefix + "wilden" + separable);
//
//        // subjunctive
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, prefix + "wille" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, prefix + "wilde" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, prefix + "willen" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, prefix + "wilden" + separable);
//
//        // imperative
//        addInfl(NLInflection.IMPERATIVE_SINGULAR, prefix + "wil" + separable);
//        addInfl(NLInflection.IMPERATIVE_PLURAL, prefix + "wilt" + separable);
//
//        // participle
//        addInfl(NLInflection.PRESENT_PARTICIPLE, separableTrimmed + prefix + "willend");
//        addInfl(NLInflection.PAST_PARTICIPLE, separableTrimmed + prefix + "wild");
    }

    private void addIrregularZijn(String prefix, String pastParticiplePrefix, String separable, String auxiliaryVerb) {
        // manually putting in zijn as the auxiliary verb
        // the template doesn't say it's "zijn" instead of "hebben" but it is
        addIrregularVerb(prefix, pastParticiplePrefix, separable, "zijn",
                "zijn",
                "ben", "was",
                "bent", "was",
                "is", "was",
                "zijn", "waren",
                "zij", "ware",
                "zijn", "waren",
                "wees", "weest",
                "zijnd", "weest");

//        String separableTrimmed = separable.trim();
//
//        // general
//        addInfl(NLInflection.INFINITIVE, separableTrimmed + prefix + "zijn");
//        addInfl(NLInflection.GERUND, separableTrimmed + prefix + "zijn");
//        addInfl(NLInflection.VERBAL_NOUN, separableTrimmed + prefix + "zijn");
//        // I know this isn't an inflection, but it should still be added
//        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
//        addInfl(NLInflection.CLASS, "");
//
//        // 1st person
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, prefix + "ben" + separable);
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, prefix + "was" + separable);
//        // 2nd person
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, prefix + "bent" + separable);
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, prefix + "was" + separable);
//        // 3rd person
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, prefix + "is" + separable);
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, prefix + "was" + separable);
//
//        // plural
//        addInfl(NLInflection.PLURAL_PRESENT, prefix + "zijn" + separable);
//        addInfl(NLInflection.PLURAL_PAST, prefix + "waren" + separable);
//
//        // subjunctive
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, prefix + "zij" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, prefix + "ware" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, prefix + "zijn" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, prefix + "waren" + separable);
//
//        // imperative
//        addInfl(NLInflection.IMPERATIVE_SINGULAR, prefix + "wees" + separable);
//        addInfl(NLInflection.IMPERATIVE_PLURAL, prefix + "weest" + separable);
//
//        // participle
//        addInfl(NLInflection.PRESENT_PARTICIPLE, separableTrimmed + prefix + "zijnd");
//        addInfl(NLInflection.PAST_PARTICIPLE, separableTrimmed + prefix + prefix + "weest");
    }

    private void addIrregularZullen(String prefix, String pastParticiplePrefix, String separable, String auxiliaryVerb) {
        addIrregularVerb(prefix, pastParticiplePrefix, separable, auxiliaryVerb,
                "zullen",
                "zal", "zou",
                "zult", "zou",
                "zal", "zou",
                "zullen", "zouden",
                "zulle", "zoude",
                "zullen", "zouden",
                "zal", "zult",
                "zullend", null);

//        String separableTrimmed = separable.trim();
//
//        // general
//        addInfl(NLInflection.INFINITIVE, separableTrimmed + prefix + "zullen");
//        addInfl(NLInflection.GERUND, separableTrimmed + prefix + "zullen");
//        addInfl(NLInflection.VERBAL_NOUN, separableTrimmed + prefix + "zullen");
//        // I know this isn't an inflection, but it should still be added
//        addInfl(NLInflection.AUXILIARY_VERB, auxiliaryVerb);
//        addInfl(NLInflection.CLASS, "");
//
//        // 1st person
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PRESENT, prefix + "zal" + separable);
//        addInfl(NLInflection.FIRST_PERSON_SINGULAR_PAST, prefix + "zou" + separable);
//        // 2nd person
//        // same thing here as in willen (decided to use the formal version)
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PRESENT, prefix + "zult" + separable);
//        addInfl(NLInflection.SECOND_PERSON_SINGULAR_PAST, prefix + "zou" + separable);
//        // 3rd person
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PRESENT, prefix + "zal" + separable);
//        addInfl(NLInflection.THIRD_PERSON_SINGULAR_PAST, prefix + "zou" + separable);
//
//        // plural
//        addInfl(NLInflection.PLURAL_PRESENT, prefix + "zullen" + separable);
//        addInfl(NLInflection.PLURAL_PAST, prefix + "zouden" + separable);
//
//        // subjunctive
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PRESENT, prefix + "zulle" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_SINGULAR_PAST, prefix + "zoude" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PRESENT, prefix + "zullen" + separable);
//        addInfl(NLInflection.SUBJUNCTIVE_PLURAL_PAST, prefix + "zouden" + separable);
//
//        // imperative
//        addInfl(NLInflection.IMPERATIVE_SINGULAR, prefix + "zal" + separable);
//        addInfl(NLInflection.IMPERATIVE_PLURAL, prefix + "zult" + separable);
//
//        // participle
//        addInfl(NLInflection.PRESENT_PARTICIPLE, separableTrimmed + prefix + "zullend");
//        // zullen doesn't have a past participle form
//        addInfl(NLInflection.PAST_PARTICIPLE, "");
    }

    /**
     * Checks which suffix a stem should have.
     * @param stem The verb stem to check
     * @return either "d" or "t", based on the stem and which of these should be used
     */
    private String tKofschip(String stem) {
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
