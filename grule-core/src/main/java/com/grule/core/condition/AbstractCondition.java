package com.grule.core.condition;

/**
 * Created on 2018/03/05.
 */
public abstract class AbstractCondition implements Condition {

    protected Unit trueUnit = null;

    protected Unit falseUnit = null;

    public final void registerTrueUnit(Unit unit) {
        this.trueUnit = unit;
    }

    public final void registerFalseUnit(Unit unit) {
        this.falseUnit = unit;
    }

    public final Unit next() {
        if (this.getResult()) {
            return this.trueUnit;
        } else {
            return this.falseUnit;
        }
    }
}
