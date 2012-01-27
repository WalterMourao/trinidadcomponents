/**
 * 
 */
package com.wim.autocomplete;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author walter
 *
 */
public class Test {
    
    public class ValueObject {
        private String id;
        private String nome;
        public ValueObject(String id, String nome) {
            this.id = id;
            this.nome = nome;
        }
        public String getId() {
            return this.id;
        }
        public String getNome() {
            return this.nome;
        }
    }
    
    public static final String[][] testList =
    { { "1", "Brasilia" }, {"2", "Belo Horizonte"}, {"3", "Sao Paulo"}, {"4", "Salvador"},
        { "5", "Rio de Janeiro"}, {"6", "Recife"}, {"7", "Sao Luis"}, {"8", "Belem"}};

    /**
     * Returns an javax.faces.event.ActionEvent parameter value, from its name
     */        
    protected Object getParameterValue(String parameterName, javax.faces.event.ActionEvent event){
        for(Object uiObject : event.getComponent().getChildren()){
            if(uiObject instanceof javax.faces.component.UIParameter){
                final javax.faces.component.UIParameter param = (javax.faces.component.UIParameter)uiObject;
                if(param.getName().equals(parameterName)) {
                    return param.getValue();
                }
            }
        }
        return null;
    }
    
    public Collection<ValueObject> fillAutocomplete(String filter){
        
        filter=filter == null || filter.toString().trim().length() == 0 ? "" : filter.toString().trim().toLowerCase(); 

        final Collection<ValueObject> result= new ArrayList<ValueObject>();
        for(String[] item: testList){
            if(item[1].toLowerCase().startsWith(filter)){
                result.add(new ValueObject(item[0],item[1]));
            }
        }
        
        return result;
    }
    
    private String cityId;
    
    public void setCityId(String cityId){
        this.cityId = cityId;
    }
    
    public String getCityId(){
        return this.cityId;
    }

    private String[] citiesIds;

    public String[] getCitiesIds() {
        return citiesIds;
    }

    public void setCitiesIds(String[] citiesIds) {
        this.citiesIds = citiesIds;
    }
    
}
