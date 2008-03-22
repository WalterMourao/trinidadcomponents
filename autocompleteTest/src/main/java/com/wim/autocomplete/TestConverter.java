/**
 * 
 */
package com.wim.autocomplete;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * @author walter
 *
 */
public class TestConverter implements Converter {

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
            throws ConverterException {
        return null;
    }

    /* (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext arg0, UIComponent arg1, Object value)
            throws ConverterException {
        if(value == null)
            return "";
        for(String[] li:Test.testList){
            if(li[0].equals(value))
                return li[1];
        }
        return "";
    }

}
