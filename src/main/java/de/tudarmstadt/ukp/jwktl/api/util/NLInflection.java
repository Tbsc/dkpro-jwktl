package de.tudarmstadt.ukp.jwktl.api.util;

/**
 * Created on 25/08/2017
 * @author tbsc
 */
public class NLInflection {

    public enum Type {
        INFINITIVE, GERUND, VERBAL_NOUN,
        FIRST_PERSON, SECOND_PERSON, THIRD_PERSON,
        SUBJUNCTIVE,
        IMPERATIVE,
        PARTICIPLE,
        AUXILIARY_VERB,
        NONE
    }

    public enum Number {
        SINGULAR, PLURAL, NONE
    }

    public enum Tense {
        PRESENT, PAST, NONE
    }

    // various pre-made inflection types

    // 1st-person singular present
    public static final NLInflection STEM = new NLInflection(Type.FIRST_PERSON, Number.SINGULAR, Tense.PRESENT);
    public static final NLInflection INFINITIVE = new NLInflection(Type.INFINITIVE);
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
