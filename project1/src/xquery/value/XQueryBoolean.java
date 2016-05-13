
package project1.xquery.value;

public class XQueryBoolean extends MyQueryElement {

    public boolean booleanFlag;

    public XQueryBoolean(boolean v) {
        this.booleanFlag = v;

    }

    public static XQueryBoolean XQueryBooleanFactory(boolean v) {
        return new XQueryBoolean(v);

    }

    public XQueryBoolean not() {
        if (this.booleanFlag)
            return new XQueryBoolean(false);
        return new XQueryBoolean(true);
    }

    public XQueryBoolean and(XQueryBoolean other) {
        if (this.booleanFlag && other.booleanFlag)
            return new XQueryBoolean(true);
        return new XQueryBoolean(false);
    }

    public XQueryBoolean or(XQueryBoolean other) {
        if (this.booleanFlag || other.booleanFlag)
            return new XQueryBoolean(true);
        return new XQueryBoolean(false);
    }

    @Override
    public String toString() {
        if (this.booleanFlag)
            return "XQueryBoolean True";
        else
            return "XQueryBoolean False";
    }
}
