package de.tudarmstadt.ukp.jwktl.api.util;

import com.sleepycat.persist.model.Persistent;

/**
 * Created on 25/08/2017
 * @author tbsc
 */
@Persistent
public enum NLInflection {

    INFINITIVE(Type.INFINITIVE, Number.NONE, Tense.NONE),
    GERUND(Type.GERUND, Number.NONE, Tense.NONE),
    VERBAL_NOUN(Type.VERBAL_NOUN, Number.NONE, Tense.NONE),
    AUXILIARY_VERB(Type.AUXILIARY_VERB, Number.NONE, Tense.NONE),
    CLASS(Type.CLASS, Number.NONE, Tense.NONE),
    FIRST_PERSON_SINGULAR_PRESENT(Type.FIRST_PERSON, Number.SINGULAR, Tense.PRESENT),
    FIRST_PERSON_SINGULAR_PAST(Type.FIRST_PERSON, Number.SINGULAR, Tense.PAST),
    SECOND_PERSON_SINGULAR_PRESENT(Type.SECOND_PERSON, Number.SINGULAR, Tense.PRESENT),
    SECOND_PERSON_SINGULAR_PAST(Type.SECOND_PERSON, Number.SINGULAR, Tense.PAST),
    THIRD_PERSON_SINGULAR_PRESENT(Type.THIRD_PERSON, Number.SINGULAR, Tense.PRESENT),
    THIRD_PERSON_SINGULAR_PAST(Type.THIRD_PERSON, Number.SINGULAR, Tense.PAST),
    PLURAL_PRESENT(Type.NONE, Number.PLURAL, Tense.PRESENT),
    PLURAL_PAST(Type.NONE, Number.PLURAL, Tense.PAST),
    SUBJUNCTIVE_SINGULAR_PRESENT(Type.SUBJUNCTIVE, Number.SINGULAR, Tense.PRESENT),
    SUBJUNCTIVE_SINGULAR_PAST(Type.SUBJUNCTIVE, Number.SINGULAR, Tense.PAST),
    SUBJUNCTIVE_PLURAL_PRESENT(Type.SUBJUNCTIVE, Number.PLURAL, Tense.PRESENT),
    SUBJUNCTIVE_PLURAL_PAST(Type.SUBJUNCTIVE, Number.PLURAL, Tense.PAST),
    IMPERATIVE_SINGULAR(Type.IMPERATIVE, Number.SINGULAR, Tense.NONE),
    IMPERATIVE_PLURAL(Type.IMPERATIVE, Number.PLURAL, Tense.NONE),
    PRESENT_PARTICIPLE(Type.PARTICIPLE, Number.NONE, Tense.PRESENT),
    PAST_PARTICIPLE(Type.PARTICIPLE, Number.NONE, Tense.PAST);

//    // pre-made inflection types
//    public static final NLInflection INFINITIVE = new NLInflection(Type.INFINITIVE);
//    public static final NLInflection GERUND = new NLInflection(Type.GERUND);
//    public static final NLInflection VERBAL_NOUN = new NLInflection(Type.VERBAL_NOUN);
//    public static final NLInflection AUXILIARY_VERB = new NLInflection(Type.AUXILIARY_VERB);
//    public static final NLInflection CLASS = new NLInflection(Type.CLASS);
//    public static final NLInflection FIRST_PERSON_SINGULAR_PRESENT = new NLInflection(Type.FIRST_PERSON, Number.SINGULAR, Tense.PRESENT);
//    public static final NLInflection FIRST_PERSON_SINGULAR_PAST = new NLInflection(Type.FIRST_PERSON, Number.SINGULAR, Tense.PAST);
//    public static final NLInflection SECOND_PERSON_SINGULAR_PRESENT = new NLInflection(Type.SECOND_PERSON, Number.SINGULAR, Tense.PRESENT);
//    public static final NLInflection SECOND_PERSON_SINGULAR_PAST = new NLInflection(Type.SECOND_PERSON, Number.SINGULAR, Tense.PAST);
//    public static final NLInflection THIRD_PERSON_SINGULAR_PRESENT = new NLInflection(Type.THIRD_PERSON, Number.SINGULAR, Tense.PRESENT);
//    public static final NLInflection THIRD_PERSON_SINGULAR_PAST = new NLInflection(Type.THIRD_PERSON, Number.SINGULAR, Tense.PAST);
//    public static final NLInflection PLURAL_PRESENT = new NLInflection(Number.PLURAL, Tense.PRESENT);
//    public static final NLInflection PLURAL_PAST = new NLInflection(Number.PLURAL, Tense.PAST);
//    public static final NLInflection SUBJUNCTIVE_SINGULAR_PRESENT = new NLInflection(Type.SUBJUNCTIVE, Number.SINGULAR, Tense.PRESENT);
//    public static final NLInflection SUBJUNCTIVE_SINGULAR_PAST = new NLInflection(Type.SUBJUNCTIVE, Number.SINGULAR, Tense.PAST);
//    public static final NLInflection SUBJUNCTIVE_PLURAL_PRESENT = new NLInflection(Type.SUBJUNCTIVE, Number.PLURAL, Tense.PRESENT);
//    public static final NLInflection SUBJUNCTIVE_PLURAL_PAST = new NLInflection(Type.SUBJUNCTIVE, Number.PLURAL, Tense.PAST);
//    public static final NLInflection IMPERATIVE_SINGULAR = new NLInflection(Type.IMPERATIVE, Number.SINGULAR, Tense.PRESENT);
//    public static final NLInflection IMPERATIVE_PLURAL = new NLInflection(Type.IMPERATIVE, Number.PLURAL, Tense.PRESENT);
//    public static final NLInflection PRESENT_PARTICIPLE = new NLInflection(Type.PARTICIPLE, Tense.PRESENT);
//    public static final NLInflection PAST_PARTICIPLE = new NLInflection(Type.PARTICIPLE, Tense.PAST);

    public Type type;
    public Number number;
    public Tense tense;

    NLInflection(Type type, Number number, Tense tense) {
        this.type = type;
        this.number = number;
        this.tense = tense;
    }

    public enum Type {
        INFINITIVE, GERUND, VERBAL_NOUN,
        FIRST_PERSON, SECOND_PERSON, THIRD_PERSON,
        SUBJUNCTIVE,
        IMPERATIVE,
        PARTICIPLE,
        AUXILIARY_VERB, // used to note which auxiliary verb should be used with this verb
        CLASS, // used to note the verb class (1-7)
        NONE
    }

    public enum Number {
        SINGULAR, PLURAL, NONE
    }

    public enum Tense {
        PRESENT, PAST, NONE
    }

}
