
package project1.xquery.value;


public class ElementFilter extends MyQueryElement{
    private static ElementFilter trueVal = new ElementFilter();
    private static ElementFilter falseVal = new ElementFilter();

    private ElementFilter() {
    }

    public static ElementFilter trueValue() {
        return trueVal;
    }

    public static ElementFilter falseValue() {
        return falseVal;
    }

    public ElementFilter not() {
        if(this == trueVal)
            return falseVal;
        return trueVal;
    }

    public ElementFilter and(ElementFilter other) {
        if(this == trueVal && other == trueVal)
            return trueVal;
        return falseVal;
    }

    public ElementFilter or(ElementFilter other) {
        if(this == trueVal || other == trueVal)
            return trueVal;
        return falseVal;
    }

    @Override
    public String toString() {
        if(this == trueVal)
            return "ElementFilter True";
        else
            return "ElementFilter False";
    }
}
