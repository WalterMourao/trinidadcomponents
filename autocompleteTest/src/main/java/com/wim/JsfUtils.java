package com.wim;

import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;


/**
 * JSF Utilities
 */
public class JsfUtils
{

    /**
     * Returns the converter identified by converterId
     * @converterId the id of the converter to be used
     * @return the Converter instance
     */
    public static Converter getConverter(
            final String converterId)
    {
        if(StringUtils.isEmpty(converterId))
        {
            return null;
        }
        else
        {
            final FacesContext facesContext=FacesContext.getCurrentInstance();
            return facesContext.getApplication().createConverter(converterId);
        }
    }

    /**
     * Uses the converter identified by converterId to convert the value to a String.
     * @value the value to be converted
     * @converterId the id of the converter to be used
     * @componentId the id of the component being rendered
     * @return the String representation of the value.
     */
    public static String valueFromConverter(
            final Object value,
            final String converterId,
            final String componentId)
    {
        final javax.faces.context.FacesContext facesContext=javax.faces.context.FacesContext.getCurrentInstance();
        final javax.faces.convert.Converter converter = facesContext.getApplication().createConverter(converterId);
        return converter.getAsString(facesContext,
                org.apache.commons.lang.StringUtils.isEmpty(componentId)?null:facesContext.getViewRoot().findComponent(componentId),
                value);
    }

    /**
     * Uses the converter identified by converterId to convert the value to a String.
     * @value the value to be converted
     * @converterId the id of the converter to be used
     * @return the String representation of the value.
     */
    public static String valueFromConverter(
            final Object value,
            final String converterId)
    {
        final javax.faces.context.FacesContext facesContext=javax.faces.context.FacesContext.getCurrentInstance();
        final javax.faces.convert.Converter converter = facesContext.getApplication().createConverter(converterId);
        return converter.getAsString(facesContext,null,value);
    }

    /**
     * Returns an javax.faces.event.ActionEvent parameter value, from its name
     * @parameterName the parameter name
     * @event ActionEvent containing the parameter
     * @return the parameter value.
     */
    public static Object getParameterValue(String parameterName, javax.faces.event.ActionEvent event)
    {
        for(Object uiObject : event.getComponent().getChildren())
        {
            if(uiObject instanceof javax.faces.component.UIParameter)
            {
                final javax.faces.component.UIParameter param = (javax.faces.component.UIParameter)uiObject;
                if(param.getName().equals(parameterName))
                {
                    return param.getValue();
                }
            }
        }
        throw new RuntimeException("Parameter "+parameterName+" not found");
    }
    
    /**
     * Guarantees the partial triggers is a String[].
     * @param partialTriggers the partialTriggers attribute.
     * @return the original partialTriggers if it is a String[] or the partialTriggers splitted if it was a String.
     */
    public static String[] splitPartialTriggers(Object partialTriggers)
    {
        if(partialTriggers instanceof String)
        {
            final String thePartialTriggers=partialTriggers.toString().trim();
            if(thePartialTriggers.length() > 0){
                return thePartialTriggers.split(" ");
            }
            else
            {
                return null;
            }
        }
        else if(partialTriggers instanceof String[])
        {
            return (String[])partialTriggers;
        } 
        else 
        {
            return null;
        }
    }
}