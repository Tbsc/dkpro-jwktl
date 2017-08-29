package de.tudarmstadt.ukp.jwktl.api.util;

import com.sleepycat.persist.model.Persistent;

/**
 * Created on 25/08/2017
 * @author tbsc
 */
@Persistent
public class NLInflection {

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

    // pre-made inflection types

    public static final NLInflection INFINITIVE = new NLInflection(Type.INFINITIVE);
    public static final NLInflection GERUND = new NLInflection(Type.GERUND);
    public static final NLInflection VERBAL_NOUN = new NLInflection(Type.VERBAL_NOUN);
    public static final NLInflection AUXILIARY_VERB = new NLInflection(Type.AUXILIARY_VERB);
    public static final NLInflection CLASS = new NLInflection(Type.CLASS);
    public static final NLInflection FIRST_PERSON_SINGULAR_PRESENT = new NLInflection(Type.FIRST_PERSON, Number.SINGULAR, Tense.PRESENT);
    public static final NLInflection FIRST_PERSON_SINGULAR_PAST = new NLInflection(Type.FIRST_PERSON, Number.SINGULAR, Tense.PAST);
    public static final NLInflection SECOND_PERSON_SINGULAR_PRESENT = new NLInflection(Type.SECOND_PERSON, Number.SINGULAR, Tense.PRESENT);
    public static final NLInflection SECOND_PERSON_SINGULAR_PAST = new NLInflection(Type.SECOND_PERSON, Number.SINGULAR, Tense.PAST);
    public static final NLInflection THIRD_PERSON_SINGULAR_PRESENT = new NLInflection(Type.THIRD_PERSON, Number.SINGULAR, Tense.PRESENT);
    public static final NLInflection THIRD_PERSON_SINGULAR_PAST = new NLInflection(Type.THIRD_PERSON, Number.SINGULAR, Tense.PAST);
    public static final NLInflection PLURAL_PRESENT = new NLInflection(Number.PLURAL, Tense.PRESENT);
    public static final NLInflection PLURAL_PAST = new NLInflection(Number.PLURAL, Tense.PAST);
    public static final NLInflection SUBJUNCTIVE_SINGULAR_PRESENT = new NLInflection(Type.SUBJUNCTIVE, Number.SINGULAR, Tense.PRESENT);
    public static final NLInflection SUBJUNCTIVE_SINGULAR_PAST = new NLInflection(Type.SUBJUNCTIVE, Number.SINGULAR, Tense.PAST);
    public static final NLInflection SUBJUNCTIVE_PLURAL_PRESENT = new NLInflection(Type.SUBJUNCTIVE, Number.PLURAL, Tense.PRESENT);
    public static final NLInflection SUBJUNCTIVE_PLURAL_PAST = new NLInflection(Type.SUBJUNCTIVE, Number.PLURAL, Tense.PAST);
    public static final NLInflection IMPERATIVE_SINGULAR = new NLInflection(Type.IMPERATIVE, Number.SINGULAR, Tense.PRESENT);
    public static final NLInflection IMPERATIVE_PLURAL = new NLInflection(Type.IMPERATIVE, Number.PLURAL, Tense.PRESENT);
    public static final NLInflection PRESENT_PARTICIPLE = new NLInflection(Type.PARTICIPLE, Tense.PRESENT);
    public static final NLInflection PAST_PARTICIPLE = new NLInflection(Type.PARTICIPLE, Tense.PAST);

    public Type type;
    public Number number;
    public Tense tense;

    public NLInflection() {
        this.type = Type.NONE;
        this.number = Number.NONE;
        this.tense = Tense.NONE;
    }

    public NLInflection(Type type) {
        this.type = type;
        this.number = Number.NONE;
        this.tense = Tense.NONE;
    }

    public NLInflection(Number number) {
        this.type = Type.NONE;
        this.number = number;
        this.tense = Tense.NONE;
    }

    public NLInflection(Tense tense) {
        this.type = Type.NONE;
        this.number = Number.NONE;
        this.tense = tense;
    }

    public NLInflection(Type type, Number number) {
        this.type = type;
        this.number = number;
        this.tense = Tense.NONE;
    }

    public NLInflection(Type type, Tense tense) {
        this.type = type;
        this.number = Number.NONE;
        this.tense = tense;
    }

    public NLInflection(Number number, Tense tense) {
        this.type = Type.NONE;
        this.number = number;
        this.tense = tense;
    }

    public NLInflection(Type type, Number number, Tense tense) {
        this.type = type;
        this.number = number;
        this.tense = tense;
    }

}
